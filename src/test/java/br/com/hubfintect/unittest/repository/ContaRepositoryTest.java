package br.com.hubfintect.unittest.repository;

import br.com.hubfintech.teste.domain.Conta;
import br.com.hubfintech.teste.domain.PessoaFisica;
import br.com.hubfintech.teste.domain.types.StatusConta;
import br.com.hubfintech.teste.repository.ContaRepository;
import br.com.hubfintect.unittest.AbstractTest;
import java.util.Date;
import org.apache.log4j.Logger;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * Teste do repositório de Conta
 * 
 * @author Jose San Pedro
 */
@DataJpaTest
public class ContaRepositoryTest extends AbstractTest {
    
    private final Logger LOGGER = Logger.getLogger(ContaRepositoryTest.class);
    
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private ContaRepository repository;
    
    @Test
    public void whenFindById_thenReturnConta() throws Exception {
        // given
        Conta given = new Conta();
        given.setNome("Conta Teste");
        given.setSaldo(100L);
        given.setStatus(StatusConta.ATIVA);
        
        long now = new Date().getTime();
        given.setDataCriacao(new Date(now));
        
        PessoaFisica pf = new PessoaFisica();
        pf.setCpf("978.678");
        entityManager.persist(pf);
        
        given.setPessoa(pf.getPessoa());
        
        given = entityManager.persist(given);
        LOGGER.info("given.getId = " + given.getId());
        entityManager.flush();
        
        // when
        Conta found = repository.findOne(given.getId());
        
        // then
        assertThat(found.getId()).isEqualTo(given.getId());
        assertThat(found.getNome()).isEqualTo(given.getNome());
        assertThat(found.getSaldo()).isEqualTo(given.getSaldo());
        assertThat(found.getStatus()).isEqualTo(given.getStatus());
        assertThat(found.getDataCriacao()).isEqualTo(given.getDataCriacao());
        assertThat(found.getPessoa()).isEqualTo(given.getPessoa());
    }
}
