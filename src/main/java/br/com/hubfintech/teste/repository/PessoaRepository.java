package br.com.hubfintech.teste.repository;

import br.com.hubfintech.teste.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface para operações genéricas de CRUD com Pessoa, tabela de ligação com PF/PJ
 * 
 * @author Jose San Pedro
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
}