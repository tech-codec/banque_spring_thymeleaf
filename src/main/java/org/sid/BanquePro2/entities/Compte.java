package org.sid.BanquePro2.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.core.sym.Name;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_COPTE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Compte implements Serializable {

	@Id 
	private String numComp;
	private Date dateCreatation;
	private double solde;
	@ManyToOne
	@JoinColumn(name = "CODE_CLT")
	private Client client;
	@OneToMany(mappedBy = "compte",fetch = FetchType.LAZY)
	private Collection<Operation> operations;
	
	public Compte(String numComp, Date dateCreatation, double solde, Client client) {
		super();
		this.numComp = numComp;
		this.dateCreatation = dateCreatation;
		this.solde = solde;
		this.client = client;
	}
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNumComp() {
		return numComp;
	}
	public void setNumComp(String numComp) {
		this.numComp = numComp;
	}
	public Date getDateCreatation() {
		return dateCreatation;
	}
	public void setDateCreatation(Date dateCreatation) {
		this.dateCreatation = dateCreatation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Collection<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	
	
	
}
