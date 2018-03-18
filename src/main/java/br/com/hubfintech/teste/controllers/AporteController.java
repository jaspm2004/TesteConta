package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.Aporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.hubfintech.teste.repository.AporteRepository;

/**
 *
 * @author Jose San Pedro
 */
@RestController
@RequestMapping(value = "/aporte")
public class AporteController {
    
    @Autowired
    AporteRepository repository;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listAporte() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAporte(@RequestBody Aporte aporte) {
        // TODO: acionar serviço para verificar se é possível fazer o aporte
        
        return new ResponseEntity(repository.saveAndFlush(aporte), HttpStatus.OK);
    }
}