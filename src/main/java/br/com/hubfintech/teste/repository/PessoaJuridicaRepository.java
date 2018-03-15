package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.PessoaJuridica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose San Pedro
 */
@Repository
public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridica, Long> {
    
}
