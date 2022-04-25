package org.sid.BanquePro2.services;


import org.sid.BanquePro2.entities.Compte;
import org.sid.BanquePro2.entities.Operation;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;

public interface OperationService {
	public Compte consulterCompte(String codeCpte) throws NotFoundException;
	public void versement(String codeCompte, double mt)throws NotFoundException;
	public void retrait(String codeCompte, double mt)throws NotFoundException;
	public void virement(String codeCompte1,String codeCompte2, double mt)throws NotFoundException;
	public Page<Operation> listOperation(String codeCompte, int page, int size)throws NotFoundException;

}
