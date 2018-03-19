package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.Transferencia;
import br.com.hubfintech.teste.repository.TransferenciaRepository;
import br.com.hubfintech.teste.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para Transferência
 * 
 * @author Jose San Pedro
 */
@RestController
@RequestMapping(value = "/transferencia")
public class TransferenciaController {
    
    @Autowired
    TransferenciaRepository repository;
    
    @Autowired
    TransferenciaService service;    
    
    /**
     * Lista todas as transferências registradas
     * 
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listTransferencia() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    
    /**
     * Executa uma nova transferência
     * 
     * @param transferencia
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createTransferencia(@RequestBody Transferencia transferencia) {
        long conta1id = transferencia.getContaOrigem().getId();
        long conta2id = transferencia.getContaDestino().getId();

        // verifica se é possível fazer o transferência
        if (service.podeFazerTransferencia(conta1id, conta2id)) {
            service.executaTransferencia(conta1id, conta2id, transferencia.getValor());
            return new ResponseEntity(repository.saveAndFlush(transferencia), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
