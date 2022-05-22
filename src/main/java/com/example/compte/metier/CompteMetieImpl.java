package com.example.compte.metier;


import com.example.compte.dao.CompteRepository;
import com.example.compte.dao.OperationRepository;
import com.example.compte.entity.Compte;
import com.example.compte.entity.Operation;
import com.example.compte.entity.Retrait;
import com.example.compte.entity.Versement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class CompteMetieImpl implements compteMetie {
    static Log log = LogFactory.getLog(CompteMetieImpl.class.getName());

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Override
    public Compte consulterCompte(String codeCpte) {

        Compte cp=compteRepository.findById(codeCpte).orElseThrow();
        //log.info(codeCpte);
        if(cp==null)
            throw new RuntimeException("compte introuvable");

        return cp;
    }


    @Override
    public void verser(String codeCpte, double montant) {
        Compte cp=consulterCompte(codeCpte);
        Versement v=new Versement(new Date(), montant, cp);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde()+montant);
        compteRepository.save(cp);

    }


    @Override
    public void retirer(String codeCpte, double montant) {
        Compte cp=consulterCompte(codeCpte);
        double faciliteCaise=0;
        if(cp.getSolde()+faciliteCaise<montant)
            throw new RuntimeException("Votre sold est insuffisant");
        Retrait v=new Retrait(new Date(), montant, cp);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde()-montant);
        compteRepository.save(cp);

    }


    @Override
    public void virement(String codeCpte1, String codeCpte2, double montant) {
        if (codeCpte1.equals(codeCpte2))
            throw new RuntimeException("Virement impossible");

        Compte cp=consulterCompte(codeCpte2);
        retirer(codeCpte1, montant);
        verser(codeCpte2, montant);

    }


    @Override
    public Page<Operation> listOperation(String codeCpte, int page, int size) {

        return operationRepository.listeOperation(codeCpte, new PageRequest(page, size));
    }



}