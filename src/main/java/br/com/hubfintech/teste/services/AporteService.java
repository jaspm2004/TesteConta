package br.com.hubfintech.teste.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jose San Pedro
 */
@Service
public class AporteService {

    private final Logger LOGGER = Logger.getLogger(AporteService.class);
    
    @Autowired
    ContaService contaSrv;
    
    /**
     * Verifica se é possível fazer o aporte na conta especificada
     * 
     * @param contaId   id da conta que vai recever o aporte
     * @return 
     */
    public boolean podeFazerAporte(long contaId) {
        LOGGER.debug("verificando se é possível fazer aporte na conta " + contaId);
        if (contaSrv.isContaAtiva(contaId)) {
            LOGGER.debug("conta " + contaId + " está ATIVA...");
            if (contaSrv.isContaMatriz(contaId)) {
                LOGGER.debug("conta " + contaId + " está MATRIZ...");
                return true;
            }
        }
        
        LOGGER.debug("a conta " + contaId + " não qualifica para receber aportes!");
        return false;
    }
    
    public void executaAporte(long contaId, long valor) {
        contaSrv.executaAporte(contaId, valor);
    }
}
