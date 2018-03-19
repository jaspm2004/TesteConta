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
import br.com.hubfintech.teste.services.AporteService;

/**
 * Controlador REST para Aporte
 * 
 * @author Jose San Pedro
 */
@RestController
@RequestMapping(value = "/aporte")
public class AporteController {
    
    @Autowired
    AporteRepository repository;
    
    @Autowired
    AporteService service;

    /**
     * Lista todos os aportes registrados
     * 
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listAporte() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    
    /**
     * Executa um novo aporte
     * 
     * @param aporte
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAporte(@RequestBody Aporte aporte) {
        long contaId = aporte.getConta().getId();

        // verifica se é possível fazer o aporte
        if (service.podeFazerAporte(contaId)) {
            service.executaAporte(contaId, aporte.getValor());
            return new ResponseEntity(repository.saveAndFlush(aporte), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}