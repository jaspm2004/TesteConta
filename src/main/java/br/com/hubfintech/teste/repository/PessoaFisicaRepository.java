package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.PessoaFisica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose San Pedro
 */
@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Long> {
    
    boolean existsByCpf(String cpf);
}
