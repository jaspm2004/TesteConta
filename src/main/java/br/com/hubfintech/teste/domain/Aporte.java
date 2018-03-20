package br.com.hubfintech.teste.domain;

import br.com.hubfintech.teste.domain.types.StatusConta;
import br.com.hubfintech.teste.domain.types.StatusTransacao;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 * Classe entidade para Aporte
 * 
 * @author Jose San Pedro
 */
@Entity
public class Aporte implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private Long valor;
    
    @ManyToOne
    @JoinColumn(name = "contaid")
    private Conta conta;
    
    @Enumerated(EnumType.STRING)
    private StatusTransacao status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }    

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public void setStatus(StatusTransacao status) {
        this.status = status;
    }
}