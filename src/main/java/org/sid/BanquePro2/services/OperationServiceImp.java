package org.sid.BanquePro2.services;

import java.util.Date;
import java.util.Optional;

import org.sid.BanquePro2.dao.CompteRepository;
import org.sid.BanquePro2.dao.OperationRepository;
import org.sid.BanquePro2.entities.Compte;
import org.sid.BanquePro2.entities.CompteCourant;
import org.sid.BanquePro2.entities.Operation;
import org.sid.BanquePro2.entities.Retrait;
import org.sid.BanquePro2.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperationServiceImp  implements OperationService{
	
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	OperationRepository operationRepository ;

	@Override
	public Compte consulterCompte(String codeCpte) throws NotFoundException {
		Optional<Compte> cp = compteRepository.findById(codeCpte);
		if(codeCpte == null) throw new RuntimeException("code du compte est introuvable");
		
		return cp.orElseThrow(() -> new NotFoundException());
	}

	@Override
	public void versement(String codeCompte, double mt) throws NotFoundException {
		
		Compte cp = consulterCompte(codeCompte);
		Versement v = new Versement(new Date(), mt, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+mt);
		compteRepository.save(cp);
		
	}

	@Override
	public void retrait(String codeCompte, double mt) throws NotFoundException {
		
		Compte cp = consulterCompte(codeCompte);
		double decouvert =0;
		if(cp instanceof CompteCourant) {
			decouvert = ((CompteCourant) cp).getDecouverte();
		}
		if(cp.getSolde()+decouvert<mt) { 
			throw new RuntimeException("le solde est insuffisant");
		}
		
		Retrait r = new Retrait(new Date(), mt, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-mt);
		compteRepository.save(cp);
		
		
	}

	@Override
	public void virement(String codeCompte1, String codeCompte2, double mt) throws NotFoundException {
		if(codeCompte1.equals(codeCompte2)) {
			throw new RuntimeException("Impossible d'éffectue le virement sur le même compte");
		}
		retrait(codeCompte1, mt);
		versement(codeCompte2, mt);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCompte, int page, int size) throws NotFoundException {
		Pageable pageable = PageRequest.of(page, size);
		return operationRepository.listOperation(codeCompte, pageable );
	}
	
	
	
}
