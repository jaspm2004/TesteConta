package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.Conta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose San Pedro
 */
@Repository
public interface ContaRepository extends CrudRepository<Conta, Long> {
    
}
