package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose San Pedro
 */
@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, String> {
 
    boolean existsByCnpj(String cnpj);
}
