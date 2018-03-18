package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.Transacao;
import br.com.hubfintech.teste.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose
 */
@RestController
@RequestMapping(value = "/trans")
public class TransacaoController {
    
    @Autowired
    TransacaoRepository repository;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listTransacao() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createTransacao(@RequestBody Transacao transacao) {
        
        return new ResponseEntity(repository.saveAndFlush(transacao), HttpStatus.OK);
    }
}