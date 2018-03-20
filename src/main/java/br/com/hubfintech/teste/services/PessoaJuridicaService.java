package br.com.hubfintech.teste.services;

import br.com.caelum.stella.validation.CNPJValidator;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para Pessoa Jurídica
 * 
 * @author Jose San Pedro
 */
@Service
public class PessoaJuridicaService {
    
    /**
     * Verifica se o CNPJ é válido
     * 
     * @param cnpj
     * @return 
     */
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
