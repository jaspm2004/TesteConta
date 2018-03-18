package br.com.hubfintech.teste.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
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
            // verifica se são contas filiais
            if (contaSrv.isContaFilial(conta1id)
                && contaSrv.isContaFilial(conta2id)) {
                // TODO: verifica se a conta2 está na árvora de conta1
                return true;
            }
        }
        
        return false;
    }
    
    public void executaTransferencia(long conta1id, long conta2id, long valor) {
        contaSrv.executaTransferencia(conta1id, conta2id, valor);
    }
}
