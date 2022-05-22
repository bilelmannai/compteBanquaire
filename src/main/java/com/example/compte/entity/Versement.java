package com.example.compte.entity;

import org.hibernate.annotations.Entity;

import java.util.Date;

@Entity
public class Versement extends Operation {

    public Versement() {
        super();
    }

    public Versement(Date dateCreation, double montant, Compte compte) {
        super(dateCreation, montant, compte);
    }
}