package br.com.caio.concessionaria.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class StatusSolicitacaoVenda implements Serializable {
    private static final long serialVersionUID = -5160132141596615599L;

    @Id
    private String id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAtualização;
    private Integer codigoStatus;
    private String mensagem;
    @MapsId
    @OneToOne(mappedBy = "statusVenda")
    @JsonIgnore
    private Veiculo veiculo;

    public StatusSolicitacaoVenda() {

    }

    public StatusSolicitacaoVenda(String id, Status status, Veiculo veiculo) {
        this.id = id;
        this.dataAtualização = LocalDateTime.now();
        this.codigoStatus = status.getCodigo();
        this.mensagem = status.getDescricao();
        this.veiculo = veiculo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDataAtualização() {
        return dataAtualização;
    }

    public void setDataAtualização(LocalDateTime dataAtualização) {
        this.dataAtualização = dataAtualização;
    }

    public Integer getCodigoStatus() {
        return codigoStatus;
    }

    public void setCodigoStatusEMensagem(Integer statusCodigo) {
        this.codigoStatus = statusCodigo;
        this.mensagem = Status.toEnum(statusCodigo).getDescricao();
    }

    public String getMensagem() {
        return mensagem;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusSolicitacaoVenda that = (StatusSolicitacaoVenda) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
