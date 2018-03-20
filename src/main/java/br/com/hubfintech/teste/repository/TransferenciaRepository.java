package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface para operações genéricas de CRUD com Transferências
 * 
 * @author Jose San Pedro
 */
@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    
}
