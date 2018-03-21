package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.Pessoa;
import br.com.hubfintech.teste.domain.PessoaJuridica;
import br.com.hubfintech.teste.repository.PessoaJuridicaRepository;
import br.com.hubfintech.teste.repository.PessoaRepository;
import br.com.hubfintech.teste.services.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para Pessoa Jurídica
 * 
 * @author Jose San Pedro
 */
@RestController
@RequestMapping(value = "/pj")
public class PessoaJuridicaController {
    
    @Autowired
    PessoaJuridicaRepository repository;
    
    @Autowired
    PessoaRepository repositoryP;
    
    @Autowired
    PessoaJuridicaService service;
    
    /**
     * Retorna a pessoa jurídica cadastrada com o id correspondente, ou uma lista de todas as pessoas jurídicas cadastradas caso id = null
     * 
     * @param cnpj  id da pessoa
     * @return      200 se a pessoa for encontrada, 
     *              404 se não existe pessoa com esse id
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getPessoaJuridica(@RequestParam(value = "cnpj", required = false, defaultValue = "") String cnpj) {
        if (cnpj.isEmpty()) {
            return new ResponseEntity(repository.findAll(), HttpStatus.OK);
        } else {
            PessoaJuridica pessoa = repository.findByCnpj(cnpj);
            if (pessoa == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            
            return new ResponseEntity(pessoa, HttpStatus.OK);
        }
    }
    
    /**
     * Cria uma nova pessoa jurídica
     * 
     * @param pessoa    a pessoa que será criada
     * @return          200 se o pessoa é criada com sucesso, 
     *                  400 se falta informação para criar,
     *                  409 se já existe uma com o mesmo CNPJ
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createPessoaJuridica(@RequestBody PessoaJuridica pessoa) {
        // o nome e o CPF não podem ser null
        String nome = pessoa.getNomeFantasia() == null ? "" : pessoa.getNomeFantasia();
        String cnpj = pessoa.getCnpj() == null ? "" : pessoa.getCnpj();
        String razaoSocial = pessoa.getRazaoSocial() == null ? "" : pessoa.getRazaoSocial();
        
        if (nome.isEmpty() 
                || cnpj.isEmpty()
                    || razaoSocial.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        // verifica se o cnpj é válido
        if (!service.validaCNPJ(cnpj))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        // verifica se já existe uma pessoa com o mesmo CNPJ
        if (repository.existsByCnpj(cnpj)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else {
            Pessoa pessoaP = new Pessoa();
            pessoaP.setPj(pessoa);            
            return new ResponseEntity(repositoryP.saveAndFlush(pessoaP), HttpStatus.OK);
        }
    }
}