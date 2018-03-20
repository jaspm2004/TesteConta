package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.Transferencia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface para operações genéricas de CRUD com Transferências
 * 
 * @author Jose San Pedro
 */
@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    
    List<Transferencia> findByContaOrigemIdOrContaDestinoId(Long conta1id, Long conta2id);
    
    List<Transferencia> findByContaOrigemId(Long contaId);
    
    List<Transferencia> findByContaDestinoId(Long contaId);
}
