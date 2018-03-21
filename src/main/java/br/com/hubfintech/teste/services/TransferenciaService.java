package br.com.hubfintech.teste.services;

import br.com.hubfintech.teste.domain.Transferencia;
import br.com.hubfintech.teste.domain.types.StatusTransacao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para Transferências
 * 
 * @author Jose San Pedro
 */
@Service
public class TransferenciaService {
    
    private final Logger LOGGER = Logger.getLogger(TransferenciaService.class);
    
    @Autowired
    ContaService contaSrv;
    
    /**
     * Verifica se é possível fazer a transferência entre as contas especificadas
     * 
     * @param conta1id  conta origem
     * @param conta2id  conta destingo
     * @param valor     valor da transferência
     * 
     * @return 
     */
    public boolean podeFazerTransferencia(long conta1id, long conta2id, long valor) {
        
        // verifica se as contas são diferentes
        if (conta1id != conta2id) {
            LOGGER.info("contas diferentes - OK");
            
            // verifica se as contas estão ativas
            if (contaSrv.isContaAtiva(conta1id)
                    && contaSrv.isContaAtiva(conta2id)) {
                LOGGER.info("contas ativas - OK");

                // verifica se a conta destino é filial
                if (contaSrv.isContaFilial(conta2id)) {
                    LOGGER.info("contas destino filial - OK");

                    // verifica se a conta destino pertence à árvore da conta origem
                    if (contaSrv.isNaArvore(conta1id, conta2id)) {
                        LOGGER.info("conta destino ná árvore da conta origem - OK");

                        // verifica se o saldo na conta origem é >= que o valor da transferência
                        if (contaSrv.isSaldoDisponível(conta1id, valor)) {
                            LOGGER.info("saldo disponível na conta origem - OK");

                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * Processa a Transferência
     * 
     * @param conta1id  id da conta origem
     * @param conta2id  id da conta destino
     * @param valor     valor a ser debitado da conta origem e creditado na conta destino
     */
    public void executaTransferencia(long conta1id, long conta2id, long valor) {
        contaSrv.executaTransferencia(conta1id, conta2id, valor);
    }
    
    /**
     * Processa o estorno da Transferência
     * Atualiza o status da transação para ESTORNADA
     * 
     * @param transferencia 
     */
    public void rollbackTransferencia(Transferencia transferencia) {
        contaSrv.rollbackTransferencia(transferencia.getContaOrigem().getId(), 
                                        transferencia.getContaDestino().getId(), 
                                        transferencia.getValor());
        transferencia.setStatus(StatusTransacao.ESTORNADA);
    }
}
