package br.com.hubfintech.teste.services;

import br.com.caelum.stella.validation.CNPJValidator;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose San Pedro
 */
@Service
public class PessoaJuridicaService {
    
    public boolean validaCNPJ(String cnpj) {
        CNPJValidator validator = new CNPJValidator();
        try {
            validator.assertValid(cnpj);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
