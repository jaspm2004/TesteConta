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
import org.springframework.web.bind.annotation.PathVariable;
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
     * Lista todos os aportes registrados para uma conta específica
     * 
     * @param contaId   id da conta
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listAporte(@RequestParam(value = "conta", required = false) Long contaId) {
        if (contaId == null)
            return new ResponseEntity(repository.findAll(), HttpStatus.OK);
        
        return new ResponseEntity(repository.findByContaId(contaId), HttpStatus.OK);
    }
    
    /**
     * Retorna o aporte cadastrado com o id correspondente
     * 
     * @param id   id do aporte
     * @return 
     */
    @RequestMapping(value = {"/{id}", "/{id}/"}, method = RequestMethod.GET)
    public ResponseEntity getAporte(@PathVariable("id") String id) {
        if (id == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        Aporte aporte = repository.findOne(id);
        if (aporte == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        
        return new ResponseEntity(aporte, HttpStatus.OK);
    }
    
    /**
     * Executa um novo aporte
     * 
     * @param aporte
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAporte(@RequestBody Aporte aporte) {
        // valor, conta: não podem ser null
        if (aporte.getValor() == null
                || aporte.getConta() == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
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
            // verifica se o aporte já foi estornado
            if (aporte.getStatus() == StatusTransacao.ESTORNADA)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            
            service.rollbackAporte(aporte);
            aporte = repository.saveAndFlush(aporte);
            
            return new ResponseEntity(aporte, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}