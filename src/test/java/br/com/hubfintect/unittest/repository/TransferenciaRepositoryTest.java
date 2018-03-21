package br.com.hubfintect.unittest.repository;

import br.com.hubfintech.teste.domain.Transferencia;
import br.com.hubfintech.teste.domain.types.StatusTransacao;
import br.com.hubfintech.teste.repository.ContaRepository;
import br.com.hubfintech.teste.repository.TransferenciaRepository;
import br.com.hubfintect.unittest.AbstractTest;
import org.apache.log4j.Logger;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * Teste do repositório de Transferência
 * 
 * @author Jose San Pedro
 */
@DataJpaTest
public class TransferenciaRepositoryTest extends AbstractTest {
    
    private final Logger LOGGER = Logger.getLogger(TransferenciaRepositoryTest.class);
    
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private TransferenciaRepository repository;
    
    @Autowired
    private ContaRepository repositoryC;
    
    @Test
    public void whenFindById_thenReturnTransferencia() throws Exception {
        // given
        Transferencia given = new Transferencia();
        given.setValor(200L);
        given.setContaOrigem(repositoryC.findOne(1L));
        given.setContaDestino(repositoryC.findOne(2L));
        given.setStatus(StatusTransacao.PROCESSADA);
        
        given = entityManager.persist(given);
        LOGGER.info("given.getId = " + given.getId());
        entityManager.flush();
        
        // when
        Transferencia found = repository.findOne(given.getId());
        
        // then
        assertThat(found.getId()).isEqualTo(given.getId());
        assertThat(found.getValor()).isEqualTo(given.getValor());
        assertThat(found.getContaOrigem()).isEqualTo(given.getContaOrigem());
        assertThat(found.getContaDestino()).isEqualTo(given.getContaDestino());
        assertThat(found.getStatus()).isEqualTo(given.getStatus());
    }
}