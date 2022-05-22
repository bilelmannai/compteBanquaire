package com.example.compte.controller;

import com.example.compte.entity.Compte;
import com.example.compte.entity.Operation;
import com.example.compte.metier.CompteMetieImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CompteController {
    static Log log = LogFactory.getLog(CompteController.class.getName());
    @Autowired
    private CompteMetieImpl banqueMetieImpl;

    @GetMapping(value = "/consulterCompter")
    public String consuleter(Model model,String codeCpte,@RequestParam(name="page",defaultValue ="0")int p,@RequestParam(name="size",defaultValue ="6")int s) {
        model.addAttribute("codeCompte",codeCpte);

        try {
            Compte cp=banqueMetieImpl.consulterCompte(codeCpte);
            Page<Operation> pageOperation=banqueMetieImpl.listOperation(codeCpte, p, s);
            model.addAttribute("compte",cp);
            model.addAttribute("listeOperation",pageOperation.getContent());

            int[] pages=new int[pageOperation.getTotalPages()];
            model.addAttribute("page", pages);
            model.addAttribute("size", s);
            model.addAttribute("pageCourante", p);

        } catch (Exception e) {
            String message=e.getMessage();
            if (message.equals("No value present")) {
                message="Compte introuvable";
            }
            model.addAttribute("exception",message);

        }

        return "comptes";
    }

    @PostMapping(value = "/saveOperation")
    public void saveOperation(Model model,String typeOperation,String codeCpte2,double montant,String codeCpte) {


        try {
            if (typeOperation.equals("VERS")) {
                banqueMetieImpl.verser(codeCpte, montant);
            }
            if (typeOperation.equals("RETR")) {
                banqueMetieImpl.retirer(codeCpte, montant);
            }

            if (typeOperation.equals("VIR")) {
                banqueMetieImpl.virement(codeCpte, codeCpte2, montant);
            }
        } catch (Exception e) {
            String message=e.getMessage();
            }
        }

}
