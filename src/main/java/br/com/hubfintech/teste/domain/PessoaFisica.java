package br.com.hubfintech.teste.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose San Pedro
 */
@Entity
public class PessoaFisica extends Pessoa implements Serializable {
    @Column
    private String nome;
    
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento; 
            
    @NotNull
    @Column(unique = true)
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}