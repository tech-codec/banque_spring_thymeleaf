package org.sid.BanquePro2.web;


import org.sid.BanquePro2.entities.Compte;
import org.sid.BanquePro2.entities.Operation;
import org.sid.BanquePro2.services.OperationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueControleur {
	@Autowired
	OperationServiceImp operationserviceI;
	
	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}
	
	@RequestMapping("/bonjour")
	public String bonjour() {
		return "comptes";
	}
	
	@RequestMapping("/consulterCompte")
	public String consulter(Model model, String codeCompte,@RequestParam(name = "page", defaultValue = "0")int page, @RequestParam(name = "size", defaultValue = "2")int size) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			 Compte compte = operationserviceI.consulterCompte(codeCompte);
			 model.addAttribute("compte", compte);
			 Page<Operation> pageOperations =operationserviceI.listOperation(codeCompte, page, size);
			 model.addAttribute("listOperations", pageOperations.getContent());
			 int[] pages= new int[pageOperations.getTotalPages()];
			 model.addAttribute("pages",pages);
		}catch(Exception e) {
			model.addAttribute("exception", e);
			
		}
		return "comptes";
		
	}
	
	@RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
	public String saveOperation(Model model,String typeOperation,String codeCompte, double montant, String codeCompte2) {
		model.addAttribute("montant",montant);
		model.addAttribute("codeCompte2", codeCompte2);
		try {
			
			if(typeOperation.equals("ret")) {
				operationserviceI.retrait(codeCompte, montant);
			}
			else if(typeOperation.equals("ver")) {
				operationserviceI.versement(codeCompte, montant);
			}
			if(typeOperation.equals("vir")) {
				operationserviceI.virement(codeCompte, codeCompte2, montant);
			}
			
		}catch(Exception e) { 
			model.addAttribute("exception2", e);
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
		
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
	
}
