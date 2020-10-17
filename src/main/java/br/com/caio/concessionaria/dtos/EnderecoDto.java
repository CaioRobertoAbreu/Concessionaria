package br.com.caio.concessionaria.dtos;

import br.com.caio.concessionaria.models.Cidade;
import br.com.caio.concessionaria.models.Endereco;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class EnderecoDto implements Serializable {
    private static final long serialVersionUID = 1443485043823642869L;

    @NotBlank(message = "Informe logradouro")
    private String logradouro;
    private String numero;
    private String complemento;
    @NotBlank(message = "Informe CEP")
    private String cep;
    @NotBlank(message = "Informe o Id da cidade")
    private String cidadeId;

    public EnderecoDto(String logradouro, String numero, String complemento, String cep, String cidadeId) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidadeId = cidadeId;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(String cidadeId) {
        this.cidadeId = cidadeId;
    }

    public static Endereco toEndereco(EnderecoDto enderecoDto){
        Cidade cidade = new Cidade();
        cidade.setId(enderecoDto.getCidadeId());
        return new Endereco(null, enderecoDto.getLogradouro(), enderecoDto.getNumero(),
                enderecoDto.getComplemento(), enderecoDto.getCep(), cidade);
    }
}
