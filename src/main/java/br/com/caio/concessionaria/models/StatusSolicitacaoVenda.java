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
    private Long id;
    private LocalDateTime dataAtualização;
    private Integer codigoStatus;
    private String mensagemMotivoStatus;
    @MapsId
    @OneToOne(mappedBy = "statusVenda")
    @JsonIgnore
    private Veiculo veiculo;

    public StatusSolicitacaoVenda() {

    }

    public StatusSolicitacaoVenda(Long id, LocalDateTime dataAtualização, Status codigoStatus,
                                  String mensagemMotivoStatus, Veiculo veiculo) {
        this.id = id;
        this.dataAtualização = dataAtualização;
        this.codigoStatus = codigoStatus.getCodigo();
        this.mensagemMotivoStatus = mensagemMotivoStatus;
        this.veiculo = veiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getMensagemMotivoStatus() {
        return mensagemMotivoStatus;
    }

    public void setMensagemMotivoStatus(String mensagemMotivoStatus) {
        this.mensagemMotivoStatus = mensagemMotivoStatus;
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
