package br.com.hubfintect.unittest.repository;

import br.com.hubfintech.teste.domain.PessoaJuridica;
import br.com.hubfintech.teste.repository.PessoaJuridicaRepository;
import br.com.hubfintect.unittest.AbstractTest;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * Teste do repositório de Pessoa Jurídica
 * 
 * @author José San Pedro
 */
@DataJpaTest
public class PessoaJuridicaReporsitoryTest extends AbstractTest {
    
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private PessoaJuridicaRepository repository;
    
    @Test
    public void whenFindByCnpj_thenReturnPessoaJuridica() throws Exception {
        // given
        PessoaJuridica given = new PessoaJuridica();
        given.setCnpj("456.789");
        given.setNomeFantasia("PJ Fantasia");
        given.setRazaoSocial("Forn. Bens e Serviços");
        
        entityManager.persist(given);
        entityManager.flush();

        // when
        PessoaJuridica found = repository.findByCnpj("456.789");

        // then
        assertThat(found.getCnpj()).isEqualTo(given.getCnpj());
        assertThat(found.getNomeFantasia()).isEqualTo(given.getNomeFantasia());
        assertThat(found.getRazaoSocial()).isEqualTo(given.getRazaoSocial());
    }
}