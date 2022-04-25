package org.sid.BanquePro2.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {

	public Versement(Date dataOperation, double montant, Compte compte) {
		super(dataOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}

	public Versement() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
}
