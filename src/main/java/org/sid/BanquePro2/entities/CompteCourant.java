package org.sid.BanquePro2.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

	private double decouverte;

	public CompteCourant(String numComp, Date dateCreatation, double solde, Client client,double decouverte) {
		super(numComp, dateCreatation, solde, client);
		this.decouverte = decouverte;
	}
	

	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}



	public double getDecouverte() {
		return decouverte;
	}

	public void setDecouverte(double decouverte) {
		this.decouverte = decouverte;
	}
	
	
	
	
}
