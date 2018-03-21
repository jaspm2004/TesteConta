package br.com.hubfintect.unittest.repository;

import br.com.hubfintech.teste.domain.PessoaFisica;
import br.com.hubfintech.teste.repository.PessoaFisicaRepository;
import br.com.hubfintect.unittest.AbstractTest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

/**
 * Teste do repositório de Pessoa Física
 * 
 * @author José San Pedro
 */
@DataJpaTest
public class PessoaFisicaReporsitoryTest extends AbstractTest {
    
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private PessoaFisicaRepository repository;
    
    @Test
    public void whenFindByCpf_thenReturnPessoaFisica() throws Exception {
        // given
        PessoaFisica given = new PessoaFisica();
        given.setCpf("123.456");
        given.setNome("Pessoa 1");
        
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dataNascimento = (Date) formatter.parse("18-11-1981");
        given.setDataNascimento(dataNascimento);
        
        entityManager.persist(given);
        entityManager.flush();

        // when
        PessoaFisica found = repository.findByCpf("123.456");

        // then
        assertThat(found.getCpf()).isEqualTo(given.getCpf());
        assertThat(found.getNome()).isEqualTo(given.getNome());
        assertThat(found.getDataNascimento()).isEqualTo(given.getDataNascimento());
    }
}