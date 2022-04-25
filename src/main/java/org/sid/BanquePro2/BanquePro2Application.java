package org.sid.BanquePro2;


import java.util.Date;


import org.sid.BanquePro2.dao.ClientRepository;
import org.sid.BanquePro2.dao.CompteRepository;
import org.sid.BanquePro2.entities.Client;
import org.sid.BanquePro2.entities.Compte;
import org.sid.BanquePro2.entities.CompteCourant;
import org.sid.BanquePro2.entities.CompteEpargne;
import org.sid.BanquePro2.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BanquePro2Application  implements CommandLineRunner{
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	OperationService operationService;
	public static void main(String[] args) {
		SpringApplication.run(BanquePro2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Client c1 = clientRepository.save(new Client("varus", "varus@gmail.com"));
		
		Client c2 = clientRepository.save(new Client("borel", "borel@gmqil.com"));
		
		Compte cc1 = compteRepository.save(new CompteCourant("c1",new Date(),90000,c1,6000));
		
		Compte cc2 = compteRepository.save(new CompteCourant("c2",new Date(),100000,c1,6000));
		
		Compte ce1 = compteRepository.save(new CompteEpargne("c3",new Date(),6000,c2,5.5));
		
		Compte ce2 = compteRepository.save(new CompteEpargne("c4", new Date(), 7000, c1, 5.5));
		
		operationService.retrait("c2", 10000);
		operationService.retrait("c2", 2000);
		
		operationService.versement("c2", 7000);
		
		operationService.virement("c1", "c2", 15000);
		
	}

}
