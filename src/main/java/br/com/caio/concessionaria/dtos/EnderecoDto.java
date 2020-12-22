package br.com.caio.concessionaria.dtos;

import br.com.caio.concessionaria.models.Cidade;
import br.com.caio.concessionaria.models.Endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EnderecoDto implements Serializable {
    private static final long serialVersionUID = 1443485043823642869L;

    @NotEmpty(message = "Informe logradouro")
    private final String logradouro;
    private final String numero;
    private final String complemento;
    @NotEmpty(message = "Informe CEP")
    private final String cep;
    @NotEmpty(message = "Informe o Id da cidade")
    private final String cidadeId;

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

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }

    public String getCidadeId() {
        return cidadeId;
    }

    public static Endereco toEndereco(EnderecoDto enderecoDto){
        Cidade cidade = new Cidade();
        cidade.setId(enderecoDto.getCidadeId());
        return new Endereco(null, enderecoDto.getLogradouro(), enderecoDto.getNumero(),
                enderecoDto.getComplemento(), enderecoDto.getCep(), cidade);
    }

}
