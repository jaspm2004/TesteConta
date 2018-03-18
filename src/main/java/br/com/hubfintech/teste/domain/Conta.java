package br.com.hubfintech.teste.domain;

import br.com.hubfintech.teste.domain.types.StatusConta;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jose San Pedro
 */
@Entity
public class Conta implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private String nome;    
    private Long saldo = 0l;
    
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy'T'HH:mm:ssZ")
    private Date dataCriacao = new Date(); 
    
    @Enumerated(EnumType.STRING)
    private StatusConta statusConta;
    
    @ManyToOne //(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoaid")
    private Pessoa pessoa;
    
    @ManyToOne
    @JoinColumn(name = "maeid")
    //@JsonBackReference(value = "Conta-Conta")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Conta mae;
    
    /*@OneToOne(mappedBy = "mae")
    @JsonBackReference(value = "Conta-Conta")
    private Conta filial;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getSaldo() {
        return saldo;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusConta getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(StatusConta statusConta) {
        this.statusConta = statusConta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
 
    public Conta getMae() {
        return mae;
    }

    public void setMae(Conta mae) {
        this.mae = mae;
    }
}