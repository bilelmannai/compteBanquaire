package com.example.compte.dao;

import com.example.compte.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompteRepository extends JpaRepository<Compte, String>{

    @Query("SELECT cl FROM Compte cl WHERE cl.numCompte = :num")
    public Compte getCompte(@Param("num") String code);
}