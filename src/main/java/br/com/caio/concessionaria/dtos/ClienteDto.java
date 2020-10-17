package br.com.caio.concessionaria.dtos;

import br.com.caio.concessionaria.models.Cliente;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ClienteDto implements Serializable {
    private static final long serialVersionUID = -3201511360864408257L;

    @NotBlank(message = "campo obrigatorio")
    @Size(min = 11, max = 11)
    private String cpf;
    @NotBlank(message = "Campo obrigatorio")
    private String nome;
    @NotBlank(message = "obrigatorio")
    //Todo fazer validacao
    private String dataNascimento;
    @Valid
    private EnderecoDto endereco;
    @NotBlank(message = "campo obrigatorio")
    //Todo fazer validacao
    private String email;

    public ClienteDto(String cpf, String nome, String dataNascimento, EnderecoDto endereco, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDto endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Cliente toCliente(ClienteDto clienteDto){

        return new Cliente(clienteDto.getCpf(), clienteDto.getNome(), clienteDto.getDataNascimento(),
                EnderecoDto.toEndereco(clienteDto.getEndereco()), clienteDto.getEmail());
    }
}
