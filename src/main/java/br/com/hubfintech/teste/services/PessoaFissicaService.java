package br.com.hubfintech.teste.services;

import br.com.caelum.stella.validation.CPFValidator;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para Pessoa Física
 * 
 * @author Jose San Pedro
 */
@Service
public class PessoaFissicaService {

    /**
     * Verifica se o CPF é válido
     * 
     * @param cpf
     * @return 
     */
    public boolean validaCPF(String cpf) {
        CPFValidator validator = new CPFValidator();
        try {
            validator.assertValid(cpf);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
