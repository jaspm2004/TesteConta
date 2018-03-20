package br.com.hubfintech.teste.controllers;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.hubfintech.teste.domain.Pessoa;
import br.com.hubfintech.teste.domain.PessoaFisica;
import br.com.hubfintech.teste.repository.PessoaFisicaRepository;
import br.com.hubfintech.teste.repository.PessoaRepository;
import br.com.hubfintech.teste.services.PessoaFissicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST para Pessoa Física
 * 
 * @author Jose San Pedro
 */
@RestController
@RequestMapping(value = "/pf")
public class PessoaFisicaController {
    
    @Autowired
    PessoaFisicaRepository repository;
    
    @Autowired
    PessoaRepository repositoryP;
    
    @Autowired
    PessoaFissicaService service;
            
    /**
     * Retorna a pessoa física cadastrada com o id correspondente, ou uma lista de todas as pessoas físicas cadastradas caso id = null
     * 
     * @param cpf   id da pessoa
     * @return      200 se a pessoa for encontrada, 
     *              404 se não existe pessoa com esse id
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getPessoaFisica(@RequestParam(value = "cpf", required = false, defaultValue = "") String cpf) {
        if (cpf.isEmpty()) {
            return new ResponseEntity(repository.findAll(), HttpStatus.OK);
        } else {
            PessoaFisica pessoa = repository.findByCpf(cpf);
            if (pessoa == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity(pessoa, HttpStatus.OK);
        }
    }
    
    /**
     * Cria uma nova pessoa
     * 
     * @param pessoa    a pessoa que será criada
     * @return          200 se o pessoa fora criada com sucesso, 
     *                  400 se falta informação para criar a pessoa,
     *                  409 se já existe uma com o mesmo CPF
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createPessoaFisica(@RequestBody PessoaFisica pessoa) {
        String nome = pessoa.getNome() == null ? "" : pessoa.getNome();
        String cpf = pessoa.getCpf() == null ? "" : pessoa.getCpf();

        // nome, CPF e data de nascimento não podem ser null        
        if (nome.isEmpty() 
                || cpf.isEmpty()
                    || pessoa.getDataNascimento() == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        // verifica se o CPF é válido
        if (!service.validaCPF(cpf))
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        // verifica se já existe uma pessoa com o mesmo CPF
        if (repository.existsByCpf(cpf)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else {
            Pessoa pessoaP = new Pessoa();
            pessoaP.setPf(pessoa);
            return new ResponseEntity(repositoryP.saveAndFlush(pessoaP), HttpStatus.OK);
        }
    }
}