package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.Pessoa;
import br.com.hubfintech.teste.domain.PessoaJuridica;
import br.com.hubfintech.teste.repository.PessoaJuridicaRepository;
import br.com.hubfintech.teste.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
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
    
    /**
     * Retorna a pessoa jurídica cadastrada com o id correspondente, ou uma lista de todas as pessoas jurídicas cadastradas caso id = null
     * 
     * @param id    id da pessoa
     * @return      200 se a pessoa for encontrada, 
     *              404 se não existe pessoa com esse id, 
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listPessoaJuridica() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    
    /**
     * Insere uma nova pessoa jurídica
     * 
     * @param pessoa    a pessoa que será inserida
     * @return          200 se o pessoa é inserida com sucesso, 
     *                  400 se falta informação para inserir,
     *                  409 se já existe uma com o mesmo CNPJ
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createPessoaJuridica(@RequestBody PessoaJuridica pessoa) {
        // o nome e o CPF não podem ser null
        String nome = pessoa.getNomeFantasia() == null ? "" : pessoa.getNomeFantasia();
        String cnpj = pessoa.getCnpj() == null ? "" : pessoa.getCnpj();
        
        if (nome.isEmpty() 
                || cnpj.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
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