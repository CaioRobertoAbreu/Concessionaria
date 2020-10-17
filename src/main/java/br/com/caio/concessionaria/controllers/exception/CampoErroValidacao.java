package br.com.caio.concessionaria.controllers.exception;

import java.io.Serializable;

public class CampoErroValidacao implements Serializable {
    private static final long serialVersionUID = 770323916401798988L;

    private String campo;
    private String mensagem;

    public CampoErroValidacao(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
