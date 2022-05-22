package com.example.compte.dao;
import com.example.compte.entity.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OperationRepository extends JpaRepository<Operation, String> {

    @Query("select op from Operation op where op.compte.numCompte=:num order by op.dateCreation desc")
    public Page<Operation> listeOperation(@Param("num")String codeCpte, Pageable pageable);

}