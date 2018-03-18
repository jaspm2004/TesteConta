package br.com.hubfintech.teste.services;

import br.com.hubfintech.teste.domain.Conta;
import br.com.hubfintech.teste.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose San Pedro
 */
@Service
public class ContaService {
    
    @Autowired
    ContaRepository repository;
    
    public boolean isContaAtiva(long id) {
        Conta conta = repository.findOne(id);
        
        if (conta != null) {
            switch(conta.getStatusConta()) {
                case ATIVA:
                    return true;
            }
        }
        
        return false;
    }
    
    public boolean isContaMatriz(long id) {
        Conta conta = repository.findOne(id);
        
        if (conta != null) {
            if (conta.getMae() == null) {
                return true;
            }
        }
        
        return false;
    }
    
    public void executaAporte(long id, long valor) {
        Conta conta = repository.findOne(id);
        
        if (conta != null) {
            conta.setSaldo(conta.getSaldo() + valor);
            repository.saveAndFlush(conta);
        }
    }
}
