package br.com.caio.concessionaria.controllers.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalTime;

public class ErroPadrao implements Serializable {
    private static final long serialVersionUID = 3909905203953026047L;

    private Integer status;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime momento;
    private Long timeStamp;
    private String mensagem;

    public ErroPadrao(Integer status, LocalTime momento, Long timeStamp, String mensagem) {
        this.status = status;
        this.momento = momento;
        this.timeStamp = timeStamp;
        this.mensagem = mensagem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalTime getMomento() {
        return momento;
    }

    public void setMomento(LocalTime momento) {
        this.momento = momento;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
