package br.com.caio.concessionaria.models;

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
    private LocalDateTime dataAtualização;
    private Integer codigoStatus;
    private String mensagem;
    @MapsId
    @OneToOne(mappedBy = "statusVenda")
    @JsonIgnore
    private Veiculo veiculo;

    public StatusSolicitacaoVenda() {

    }

    public StatusSolicitacaoVenda(String id, Status codigoStatus, String mensagem,
                                  Veiculo veiculo) {
        this.id = id;
        this.dataAtualização = LocalDateTime.now();
        this.codigoStatus = codigoStatus.getCodigo();
        this.mensagem = codigoStatus.getDescricao();
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

    public Status getCodigoStatus() {
        return Status.toEnum(codigoStatus);
    }

    public void setCodigoStatus(Integer status) {
        this.codigoStatus = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(Status statusCodigo) {
        this.mensagem = statusCodigo.getDescricao();
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
