package br.com.hubfintech.teste.controllers;

import br.com.hubfintech.teste.domain.PessoaFisica;
import br.com.hubfintech.teste.repository.PessoaFisicaRepository;
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
public class PessoaFisicaController {
    
    @Autowired
    PessoaFisicaRepository repository;
    
    /**
     * Insere um novo produto
     * 
     * @param pessoa    a pessoa que será inserida
     * @return          200 se o pessoa é inserida com sucesso, 
     *                  400 se falta informação para inserir,
     *                  409 se já existe uma com o mesmo CPF
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createProduct(@RequestBody PessoaFisica pessoa) {
        // o nome e o CPF não podem ser null
        String nome = pessoa.getNome();
        String cpf = pessoa.getCpf();
        
        if (nome.isEmpty() 
                || cpf.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        // verifica se já existe uma pessoa com o mesmo CPF
        if (repository.existsByCpf(cpf)) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity(repository.save(pessoa), HttpStatus.OK);
        }
    }
}