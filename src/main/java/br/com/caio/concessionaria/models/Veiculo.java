package br.com.caio.concessionaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1812040767627402700L;

    @ManyToOne
    @JoinColumn(name = "cliente_cpf")
    @JsonIgnore
    private Cliente proprietario;
    @Id
    private String placa;
    private Integer anoVeiculo;
    private String modelo;
    @OneToOne
    @JoinColumn(name = "status_venda_id")
    private StatusSolicitacaoVenda statusVenda;

    public Veiculo() {

    }

    public Veiculo(Cliente proprietario, String placa, Integer anoVeiculo, String modelo) {
        this.proprietario = proprietario;
        this.placa = placa.toUpperCase();
        this.anoVeiculo = anoVeiculo;
        this.modelo = modelo;
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
        this.placa = placa.toUpperCase();
    }

    public Integer getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(Integer anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public StatusSolicitacaoVenda getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(StatusSolicitacaoVenda statusVenda) {
        this.statusVenda = statusVenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return getPlaca().equals(veiculo.getPlaca());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlaca());
    }
}
