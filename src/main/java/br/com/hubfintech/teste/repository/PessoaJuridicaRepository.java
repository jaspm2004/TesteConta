package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface para operações genéricas de CRUD com Pessoa Jurídica
 * 
 * @author Jose San Pedro
 */
@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, String> {
 
    PessoaJuridica findByCnpj(String cnpj);
    
    boolean existsByCnpj(String cnpj);
}
