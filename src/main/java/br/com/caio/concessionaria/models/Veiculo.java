package br.com.caio.concessionaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1812040767627402700L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente proprietario; //Todo fazer mapeamento
    private String placa;
    private String anoVeiculo;
    private String modelo;
    private BigDecimal preco;
    //Todo private StatusSolicitacaoVeiculo statusVenda;

    public Veiculo() {

    }

    public Veiculo(Long id, Cliente proprietario, String placa, String anoVeiculo, String modelo, BigDecimal preco) {
        this.id = id;
        this.proprietario = proprietario;
        this.placa = placa;
        this.anoVeiculo = anoVeiculo;
        this.modelo = modelo;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(String anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return getId().equals(veiculo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
