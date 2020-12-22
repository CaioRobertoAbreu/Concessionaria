package br.com.caio.concessionaria.dtos;

import br.com.caio.concessionaria.models.Cliente;
import br.com.caio.concessionaria.service.validation.DataNascimento;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@DataNascimento
public class ClienteDto implements Serializable {
    private static final long serialVersionUID = -3201511360864408257L;

    @NotEmpty(message = "campo obrigatorio")
    @CPF
    private final String cpf;
    @NotEmpty(message = "Campo obrigatorio")
    private final String nome;
    @NotEmpty(message = "obrigatorio")
    private final String dataNascimento;
    @Valid
    private final EnderecoDto endereco;
    @NotEmpty(message = "campo obrigatorio")
    @Email(message = "E-mail inválido")
    private final String email;
    private final Set<String> telefones = new HashSet<>();

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


    public String getNome() {
        return nome;
    }


    public String getDataNascimento() {
        return dataNascimento;
    }


    public EnderecoDto getEndereco() {
        return endereco;
    }


    public String getEmail() {
        return email;
    }


    public Set<String> getTelefones() {
        return telefones;
    }


    public static Cliente toCliente(ClienteDto clienteDto){

        Cliente cliente = new Cliente(clienteDto.getCpf(), clienteDto.getNome(), clienteDto.getDataNascimento(),
                EnderecoDto.toEndereco(clienteDto.getEndereco()), clienteDto.getEmail());

        cliente.setTelefones(clienteDto.getTelefones());
        return cliente;
    }
}
