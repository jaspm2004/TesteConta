package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.Conta;
import br.com.hubfintech.teste.domain.Pessoa;
import br.com.hubfintech.teste.repository.ContaRepository;
import br.com.hubfintech.teste.repository.PessoaRepository;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jose San Pedro
 */
@RestController
@RequestMapping(value = "/conta")
public class ContaController {
    
    @Autowired 
    ContaRepository repository;
    
    @Autowired
    PessoaRepository repositoryP;
    
    /**
     * Retorna a conta cadastrada com o id correspondente, ou uma lista de todas as contas caso id = null
     * 
     * @param id    id da conta
     * @return      200 se a conta for encontrada, 
     *              404 se não existe conta com esse id, 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listContaById(@RequestParam(value = "id", required = false, defaultValue = "") Long id) {
        // se o id é null, retorna todas as contas
        if (id == null) {
            return new ResponseEntity(repository.findAll(), HttpStatus.OK);
        }
        
        // se o id não é null, filtra
        List<Conta> listById = repository.findById(id);
        if (listById.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(listById, HttpStatus.OK);
    }
    
    /**
     * Insere uma nova conta
     * 
     * @param conta     a conta que será inserida
     * @return          200 se a conta é inserida com sucesso, 
     *                  400 se falta informação para inserir,
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createConta(@RequestBody Conta conta) {
        // o nome não pode ser null
        String nome = conta.getNome();
        
        if (nome.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        Pessoa pessoa = repositoryP.getOne(conta.getPessoa().getId());
        Hibernate.initialize(pessoa.getPf());
        Hibernate.initialize(pessoa.getPj());
        conta.setPessoa(pessoa);
        return new ResponseEntity(repository.saveAndFlush(conta), HttpStatus.OK);
    }
    
}