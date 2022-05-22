package com.example.compte.entity;

import org.hibernate.annotations.Entity;

import java.util.Date;

@Entity
public class Retrait extends Operation{

    public Retrait() {
        super();
    }

    public Retrait(Date dateCreation, double montant, Compte compte) {
        super(dateCreation, montant, compte);
    }

}
