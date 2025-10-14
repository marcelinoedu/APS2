package com.aps2ArqObj.APS2.Repositories;

import com.aps2ArqObj.APS2.Models.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
}
