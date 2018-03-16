package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose San Pedro
 */
@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, String> {
    
    boolean existsByCpf(String cpf);
    
    PessoaFisica findByCpf(String cpf);
}
