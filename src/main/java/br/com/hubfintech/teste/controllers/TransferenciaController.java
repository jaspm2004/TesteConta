package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.Aporte;
import br.com.hubfintech.teste.domain.Transferencia;
import br.com.hubfintech.teste.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose San Pedro
 */
@RestController
@RequestMapping(value = "/transferencia")
public class TransferenciaController {
    
    @Autowired
    TransferenciaRepository repository;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listTransferencia() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createTransferencia(@RequestBody Transferencia transferencia) {
        // TODO: acionar serviço para verificar se é possível fazer a transferência
        
        return new ResponseEntity(repository.saveAndFlush(transferencia), HttpStatus.OK);
    }
}
