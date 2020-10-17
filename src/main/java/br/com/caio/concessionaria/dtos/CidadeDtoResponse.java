package br.com.caio.concessionaria.dtos;

import br.com.caio.concessionaria.models.Cidade;

import java.io.Serializable;

public class CidadeDtoResponse implements Serializable {
    private static final long serialVersionUID = 7209440363793470073L;

    private String id;
    private String nome;
    private String uf;

    public CidadeDtoResponse(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
        this.uf = cidade.getUf();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
