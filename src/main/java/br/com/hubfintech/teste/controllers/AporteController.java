package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.Aporte;
import br.com.hubfintech.teste.domain.types.StatusTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.hubfintech.teste.repository.AporteRepository;
import br.com.hubfintech.teste.services.AporteService;
import org.springframework.web.bind.annotation.RequestParam;

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
            
            // atualiza status da transação
            aporte.setStatus(StatusTransacao.PROCESSADA);
            
            return new ResponseEntity(repository.saveAndFlush(aporte), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity rollbackAporte(@RequestParam(value = "id") String id) {
        // id não pode ser vazío
        if (id.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        Aporte aporte = repository.getOne(id);
        if (aporte != null) {
            service.rollbackAporte(aporte);
            aporte = repository.saveAndFlush(aporte);
            
            return new ResponseEntity(aporte, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}