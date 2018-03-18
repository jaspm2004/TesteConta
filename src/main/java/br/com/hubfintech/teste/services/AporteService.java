package br.com.hubfintech.teste.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose San Pedro
 */
@Service
public class AporteService {

    @Autowired
    ContaService contaSrv;
    
    /**
     * Verifica se é possível fazer o aporte na conta especificada
     * 
     * @param contaId   id da conta que vai recever o aporte
     * @return 
     */
    public boolean podeFazerAporte(long contaId) {
        if (contaSrv.isContaAtiva(contaId)) {
            if (contaSrv.isContaMatriz(contaId)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void executaAporte(long contaId, long valor) {
        contaSrv.executaAporte(contaId, valor);
    }
}
