package br.com.hubfintech.teste.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para Transferências
 * 
 * @author Jose San Pedro
 */
@Service
public class TransferenciaService {
    
    @Autowired
    ContaService contaSrv;
    
    /**
     * Verifica se é possível fazer a transferência entre as contas especificadas
     * 
     * @param conta1id  conta origem
     * @param conta2id  conta destingo
     * 
     * @return 
     */
    public boolean podeFazerTransferencia(long conta1id, long conta2id) {
        
        // verifica se as contas estão ativas
        if (contaSrv.isContaAtiva(conta1id)
                && contaSrv.isContaAtiva(conta2id)) {
            // verifica se a conta destino é filial
            if (contaSrv.isContaFilial(conta2id)) {
                // verifica se a conta destino pertence à árvore da conta origem
                if (contaSrv.isNaArvore(conta1id, conta2id)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public void executaTransferencia(long conta1id, long conta2id, long valor) {
        contaSrv.executaTransferencia(conta1id, conta2id, valor);
    }
}
