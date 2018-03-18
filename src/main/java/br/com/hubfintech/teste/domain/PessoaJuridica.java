package br.com.hubfintech.teste.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Classe entidade para Pessoa Jurídica
 * 
 * @author Jose San Pedro
 */
@Entity
public class PessoaJuridica implements Serializable {
    
    @Id
    @NotNull
    @Column(unique = true)
    private String cnpj;
    
    @Column
    private String nomeFantasia;
    private String razaoSocial;
    
    @OneToOne()
    @JoinColumn(name = "id")
    @JsonBackReference(value="Pessoa-PessoaJuridica")
    private Pessoa pessoa;
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}