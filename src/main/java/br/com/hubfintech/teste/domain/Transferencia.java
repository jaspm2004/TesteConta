package br.com.hubfintech.teste.domain;

import br.com.hubfintech.teste.domain.types.StatusTransacao;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Classe entidade para Transferência
 * 
 * @author Jose San Pedro
 */
@Entity
public class Transferencia implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private Long valor;
    
    @ManyToOne
    @JoinColumn(name = "conta1id")
    private Conta contaOrigem;
    
    @ManyToOne
    @JoinColumn(name = "conta2id")
    private Conta contaDestino;
    
    @Enumerated(EnumType.STRING)
    private StatusTransacao status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public void setStatus(StatusTransacao status) {
        this.status = status;
    }
}