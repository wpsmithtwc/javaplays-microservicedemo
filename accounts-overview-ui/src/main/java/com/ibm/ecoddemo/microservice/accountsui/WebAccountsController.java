package com.ibm.ecoddemo.microservice.accountsui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author danielcho
 *
 */
@Controller
@RequestMapping("/accounts")
public class WebAccountsController {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;
	
	/* 1.  logical name for api gateway service is used to resolve its endpoint via Ribbon and Discovery server 
	 * 2.  all resources from different endpoints are consumed via api gateway for backend processes routing,
	 *     security checks and  services orchestration using api services choreographer.  This will allow 
	 *     independent evolution between consumers and service providers, to avoid Cross Origin Resource Sharing
	 *     issue from the conventional browsers, and to avoid hard coded backend processes' end points that change
	 *     constantly in cloud.
	 **/
	private @Value("${api.gateway.service.url.logical}") String apiGatewayServiceUrl;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("accountNumber", "searchText");
	}

	@RequestMapping("/lookupby/number/{accountNumber}")
	public String byNumber(Model model, @PathVariable("accountNumber") String accountNumber) {
		/* 1.  get account lookup result from api gateway communicating to accounts and customers services respectively.
		 * 2.  in reality, this segments of code fits better in service (business delegate) layer in web application in
		 *     order to separate flow code from external services communication and building data for UI to consume for 
		 *     easier maintenance (separation of concerns).
		 * */
		AccountDTO account = 
				restTemplate.getForObject(apiGatewayServiceUrl + 
						"/api/choreographer/accounts/findby/number/{accountNumber}", 
						AccountDTO.class, accountNumber);
		
		CustomerDTO customer = 
				restTemplate.getForObject(apiGatewayServiceUrl + 
						"/api/choreographer/customers/findby/number/{" + account.getCustomerNo() + "}",
						CustomerDTO.class, account.getCustomerNo());
		
		model.addAttribute("account", account).addAttribute("customer", customer);
		
		/* refer to account.html to render using account and customer data passed in */
		return "account";
	}

	@RequestMapping("/lookupby/owner/{name}")
	public String ownerSearch(Model model, @PathVariable("name") String name) {
		
		return "account";
	}

	@RequestMapping(value = "/accounts/search", method = RequestMethod.GET)
	public String searchForm(Model model) {
		model.addAttribute("searchCriteria", new SearchCriteria());
		return "accountSearch";
	}

	@RequestMapping(value = "/accounts/dosearch")
	public String doSearch(Model model, SearchCriteria criteria, BindingResult result) {
		criteria.validate(result);

		if (result.hasErrors())
			return "accountSearch";

		String accountNumber = criteria.getAccountNumber();
		if (StringUtils.hasText(accountNumber)) {
			return byNumber(model, accountNumber);
		} else {
			String searchText = criteria.getSearchText();
			return ownerSearch(model, searchText);
		}
	}
}
