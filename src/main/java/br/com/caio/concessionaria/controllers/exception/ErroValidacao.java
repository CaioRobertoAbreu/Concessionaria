package br.com.caio.concessionaria.controllers.exception;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ErroValidacao extends ErroPadrao {
    private static final long serialVersionUID = -504856291595641964L;

    private List<CampoErroValidacao> erros = new ArrayList<>();

    public ErroValidacao(Integer status, LocalTime momento, Long timeStamp, String mensagem) {
        super(status, momento, timeStamp, mensagem);
    }

    public List<CampoErroValidacao> getErros() {
        return erros;
    }

    public void adicionarErro(String campo, String mensagem) {
        this.erros.add(new CampoErroValidacao(campo, mensagem));
    }
}
