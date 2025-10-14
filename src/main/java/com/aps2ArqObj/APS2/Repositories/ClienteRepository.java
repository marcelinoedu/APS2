package com.aps2ArqObj.APS2.Repositories;

import com.aps2ArqObj.APS2.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    
}
