package br.com.hubfintech.teste.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose San Pedro
 */
@Entity
public class PessoaJuridica  extends Pessoa implements Serializable {
    @Column
    private String nomeFantasia;
    
    @NotNull
    @Column(unique = true)
    private String cnpj;

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