package com.ibm.ecoddemo.microservice.accountsui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author danielcho
 *
 */
@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
