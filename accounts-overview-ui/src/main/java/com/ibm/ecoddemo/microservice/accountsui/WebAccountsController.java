package com.ibm.ecoddemo.microservice.accountsui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebAccountsController {
	
	/* Spring MVC based controller to interact with UIs.
	 * */

	/* Use of Thymeleaf as a full-featured substitute for JSP.
	 * View resolver managing templates for html & xhtml, Allow Spring EL etc.
	 * */


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("accountNumber", "searchText");
	}

	@RequestMapping("/accounts/{accountNumber}")
	public String byNumber(Model model, @PathVariable("accountNumber") String accountNumber) {
		AccountDTO account = accountsService.findByNumber(accountNumber);
		model.addAttribute("account", account);
		return "account";
	}

	@RequestMapping("/accounts/owner/{name}")
	public String ownerSearch(Model model, @PathVariable("name") String name) {
		List<AccountDTO> accounts = accountsService.byOwnerContains(name);
		model.addAttribute("search", name);
		if (accounts != null)
			model.addAttribute("accounts", accounts);
		return "accounts";
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
