package com.example.compte.entity;

import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
public abstract class Compte implements Serializable {
    @Id
    private String numCompte;
    private Date dateCreate;
    private double solde;
    @ManyToOne
    private Client client;
    @OneToMany
    private Collection<Operation> operations;
    public Compte() {
        super();
    }
    public Compte(String numCompte, Date dateCreate, double solde, Client client) {
        super();
        this.numCompte = numCompte;
        this.dateCreate = dateCreate;
        this.solde = solde;
        this.client = client;
    }
    public String getNumCompte() {
        return numCompte;
    }
    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }
    public Date getDateCreate() {
        return dateCreate;
    }
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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