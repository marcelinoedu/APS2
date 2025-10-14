package com.aps2ArqObj.APS2.Repositories;

import com.aps2ArqObj.APS2.Models.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {
    List<Cartao> findByContaNumero(String numeroConta);
}
