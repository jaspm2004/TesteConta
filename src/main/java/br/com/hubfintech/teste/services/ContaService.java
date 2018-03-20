package br.com.hubfintech.teste.services;

import br.com.hubfintech.teste.domain.Conta;
import br.com.hubfintech.teste.repository.ContaRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para Contas
 * 
 * @author Jose San Pedro
 */
@Service
public class ContaService {
    
    private final Logger LOGGER = Logger.getLogger(ContaService.class);
    
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
            switch(conta.getStatus()) {
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
        
        LOGGER.info("verificando se está na árvore, origem = " + conta1id + ", destino = " + conta2id);
        Conta contaFilha = repository.findOne(conta2id);
        
        if (contaFilha.getMae() == null)
            return false;
        
        if (contaFilha.getMae().getId() == conta1id)
            return true;
        else {
            return isNaArvore(conta1id, contaFilha.getMae().getId());
        }
    }
    
    /**
     * Verifica se o saldo disponível na conta é maior ou igual ao valor
     * 
     * @param id    id da conta
     * @param valor valor
     * @return 
     */
    public boolean isSaldoDisponível(long id, long valor) {
        Conta conta = repository.findOne(id);
        
        if (conta != null) {
            if (conta.getSaldo() >= valor)
                return true;
        }
        
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
    
    /**
     * Executa estorno do Aporte, debitando o valor do aporte
     * 
     * @param id    id da conta para estorno
     * @param valor valor a ser estornado
     */
    public void rollbackAporte(long id, long valor) {
        Conta conta = repository.findOne(id);
        
        if (conta != null) {
            conta.setSaldo(conta.getSaldo() - valor);
            repository.saveAndFlush(conta);
        }
    }
    
    /**
     * Executa estorno da Transferência, debitando da conta destino e creditando na conta origem
     * 
     * @param conta1id  id da conta origem
     * @param conta2id  id da conta destino
     * @param valor     valor a ser estornado
     */
    public void rollbackTransferencia(long conta1id, long conta2id, long valor) {
        Conta conta1 = repository.findOne(conta1id);
        Conta conta2 = repository.findOne(conta2id);
        
        if (conta1 != null && conta2 != null) {
            // débito
            conta1.setSaldo(conta1.getSaldo() + valor);
            repository.saveAndFlush(conta1);
            
            // crédito
            conta2.setSaldo(conta2.getSaldo() - valor);
            repository.saveAndFlush(conta2);
        }
    }
}
