package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.Conta;
import br.com.hubfintech.teste.domain.Transferencia;
import br.com.hubfintech.teste.domain.types.StatusTransacao;
import br.com.hubfintech.teste.repository.TransferenciaRepository;
import br.com.hubfintech.teste.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
     * Lista as transferências registradas
     * 
     * @param origem    filtra a listagem pela conta origem
     * @param destino   filtra a listagem pela conta destino
     * @param any       filtra a listagem por ambas as contas
     * 
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listTransferencia(@RequestParam(value = "origem", required = false) Long origem,
                                                @RequestParam(value = "destino", required = false) Long destino,
                                                    @RequestParam(value = "any", required = false) Long any) {
        
        if (any != null) {
            return new ResponseEntity(repository.findByContaOrigemIdOrContaDestinoId(any, any), HttpStatus.OK);
        }
        
        if (origem != null) {
            return new ResponseEntity(repository.findByContaOrigemId(origem), HttpStatus.OK);
        }
        
        if (destino != null) {
            return new ResponseEntity(repository.findByContaDestinoId(destino), HttpStatus.OK);
        }
        
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    
    /**
     * Retorna a transferência cadastrada com o id correspondente
     * 
     * @param id    id da transferência
     * @return      200 se a transferência for encontrada, 
     *              404 se não existe transferência com esse id
     */
    @RequestMapping(value = {"/{id}", "/{id}/"}, method = RequestMethod.GET)
    public ResponseEntity getTransferencia(@PathVariable("id") Long id) {
        Transferencia transferencia = repository.findOne(id);
        if (transferencia == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(transferencia, HttpStatus.OK);
    }
    
    /**
     * Executa uma nova transferência
     * 
     * @param transferencia
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createTransferencia(@RequestBody Transferencia transferencia) {
        Conta contaOrigem = transferencia.getContaOrigem();
        Conta contaDestino = transferencia.getContaDestino();
        
        if (transferencia.getValor() == null || contaOrigem == null || contaDestino == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        long conta1id = contaOrigem.getId();
        long conta2id = contaDestino.getId();
        long valor = transferencia.getValor();

        // verifica se é possível fazer o transferência
        if (service.podeFazerTransferencia(conta1id, conta2id, valor)) {
            service.executaTransferencia(conta1id, conta2id, valor);
            
            // atualiza status da transação
            transferencia.setStatus(StatusTransacao.PROCESSADA);
            
            return new ResponseEntity(repository.saveAndFlush(transferencia), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value = {"/{id}", "/{id}/"}, method = RequestMethod.PATCH)
    public ResponseEntity rollbackTrasnferencia(@PathVariable("id") Long id) {
        // id não pode ser vazío
        if (id == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        Transferencia transferencia = repository.getOne(id);
        if (transferencia != null) {
            // verifica se a transferência já foi estornada
            if (transferencia.getStatus() == StatusTransacao.ESTORNADA)
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            
            service.rollbackTransferencia(transferencia);
            transferencia = repository.saveAndFlush(transferencia);
            
            return new ResponseEntity(transferencia, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
