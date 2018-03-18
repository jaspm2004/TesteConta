package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose San Pedro
 */
@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, String> {
    
}
