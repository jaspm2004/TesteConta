package br.com.hubfintect.unittest.repository;

import br.com.hubfintech.teste.domain.Aporte;
import br.com.hubfintech.teste.domain.types.StatusTransacao;
import br.com.hubfintech.teste.repository.AporteRepository;
import br.com.hubfintech.teste.repository.ContaRepository;
import br.com.hubfintect.unittest.AbstractTest;
import org.apache.log4j.Logger;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * Teste do repositório de Aporte
 * 
 * @author Jose San Pedro
 */
@DataJpaTest
public class AporteRepositoryTest extends AbstractTest {
    
    private final Logger LOGGER = Logger.getLogger(AporteRepositoryTest.class);
    
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private AporteRepository repository;
    
    @Autowired
    private ContaRepository repositoryC;
    
    @Test
    public void whenFindById_thenReturnAporte() throws Exception {
        // given
        Aporte given = new Aporte();
        given.setValor(100L);
        given.setConta(repositoryC.findOne(1L));
        given.setStatus(StatusTransacao.PROCESSADA);
        
        given = entityManager.persist(given);
        LOGGER.info("given.getId = " + given.getId());
        entityManager.flush();
        
        // when
        Aporte found = repository.findOne(given.getId());
        
        // then
        assertThat(found.getId()).isEqualTo(given.getId());
        assertThat(found.getValor()).isEqualTo(given.getValor());
        assertThat(found.getConta()).isEqualTo(given.getConta());
        assertThat(found.getStatus()).isEqualTo(given.getStatus());
    }
}