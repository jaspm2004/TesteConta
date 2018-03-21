package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.Conta;
import br.com.hubfintech.teste.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para Conta
 * 
 * @author Jose San Pedro
 */
@RestController
@RequestMapping(value = "/conta")
public class ContaController {
    
    @Autowired 
    ContaRepository repository;
    
    /**
     * Lista todas as contas cadastradas
     * 
     * @return 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listContas() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    
    /**
     * Retorna a conta cadastrada com o id correspondente
     * 
     * @param id    id da conta
     * @return      200 se a conta for encontrada, 
     *              404 se não existe conta com esse id
     */
    @RequestMapping(value = {"/{id}", "/{id}/"}, method = RequestMethod.GET)
    public ResponseEntity getConta(@PathVariable("id") Long id) {
        Conta conta = repository.findOne(id);
        if (conta == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(conta, HttpStatus.OK);
    }
    
    /**
     * Cria uma nova conta
     * 
     * @param conta     a conta que será criada
     * @return          200 se a conta for criada com sucesso, 
     *                  400 se falta informação para criar a conta
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createConta(@RequestBody Conta conta) {
        String nome = conta.getNome();

        // nome, status: não pode ser null        
        // a conta precisa estar sempre atrelada a uma pessoa
        if (nome.isEmpty()
                || conta.getStatus() == null
                    || conta.getPessoa().getId() == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity(repository.saveAndFlush(conta), HttpStatus.OK);
    }
}