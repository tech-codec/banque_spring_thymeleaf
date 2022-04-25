package org.sid.BanquePro2.secure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityControlleur {

	@RequestMapping(value = "/login")
	public String connexion() {
		return "login";
	}
	
	@RequestMapping(value="/")
	public String deffaul() {
		return "redirect:/operations";
	}
	
	@RequestMapping(value="/403")
	public String accessDenied() {
		return "403";
	}
}
