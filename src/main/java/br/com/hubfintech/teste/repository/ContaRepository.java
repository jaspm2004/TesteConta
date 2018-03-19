package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface para operações genéricas de CRUD com Contas
 * 
 * @author Jose San Pedro
 */
@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    
}
