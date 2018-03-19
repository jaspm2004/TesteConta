package br.com.hubfintech.teste.services;

import br.com.hubfintech.teste.domain.Conta;
import br.com.hubfintech.teste.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para Contas
 * 
 * @author Jose San Pedro
 */
@Service
public class ContaService {
    
    @Autowired
    ContaRepository repository;
    
    /**
     * Verifica se é uma conta ATIVA
     * 
     * @param id    id da conta
     * @return 
     */
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
    
    /**
     * Verifica se é uma conta MATRIZ
     * 
     * @param id    id da conta
     * @return 
     */
    public boolean isContaMatriz(long id) {
        Conta conta = repository.findOne(id);
        
        if (conta != null) {
            if (conta.getMae() == null) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Verifica se é uma conta FILIAL
     * 
     * @param id    id da conta
     * @return 
     */
    public boolean isContaFilial(long id) {
        return !isContaMatriz(id);
    }
    
    /**
     * Verifica se a conta destino pertence à árvore da conta origem
     * 
     * @param conta1id  id da conta origem
     * @param conta2id  id da conta destino
     * @return 
     */
    public boolean isNaArvore(long conta1id, long conta2id) {
        
        // TODO: implementar verificação
        
        return false;
    }
    
    /**
     * Executa transação de aporte, creditando o valor no saldo da conta
     * 
     * @param id        id da conta
     * @param valor     valor a ser creditado
     */
    public void executaAporte(long id, long valor) {
        Conta conta = repository.findOne(id);
        
        if (conta != null) {
            conta.setSaldo(conta.getSaldo() + valor);
            repository.saveAndFlush(conta);
        }
    }
    
    /**
     * Executa transação de transferência, debitando da conta origem e creditando na conta destino
     * 
     * @param conta1id  id da conta origem
     * @param conta2id  id da conta destino
     * @param valor     valor a transferido
     */
    public void executaTransferencia(long conta1id, long conta2id, long valor) {
        Conta conta1 = repository.findOne(conta1id);
        Conta conta2 = repository.findOne(conta2id);
        
        if (conta1 != null && conta2 != null) {
            // débito
            conta1.setSaldo(conta1.getSaldo() - valor);
            repository.saveAndFlush(conta1);
            
            // crédito
            conta2.setSaldo(conta2.getSaldo() + valor);
            repository.saveAndFlush(conta2);
        }
    }
}
