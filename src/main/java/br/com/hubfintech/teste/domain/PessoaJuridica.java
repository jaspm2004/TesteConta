package br.com.hubfintech.teste.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose San Pedro
 */
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PessoaJuridica implements Serializable {
    
    @OneToOne()
    @JoinColumn(name = "id")
    @JsonBackReference(value="Pessoa-PessoaJuridica")
    private Pessoa pessoa;
    
    @Column
    private String nomeFantasia;
    
    @Id
    @NotNull
    @Column(unique = true)
    private String cnpj;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}