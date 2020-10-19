package br.com.caio.concessionaria.dtos;

import br.com.caio.concessionaria.models.Cliente;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ClienteDtoAtualizacao implements Serializable {
    private static final long serialVersionUID = -3433624790325478531L;

    //Todo fazer validacoes

    private final EnderecoDto enderecoDto;
    private final String email;
    @NotBlank
    private final Set<String> telefones = new HashSet<>();

    public ClienteDtoAtualizacao(EnderecoDto enderecoDto, String email) {
        this.enderecoDto = enderecoDto;
        this.email = email;
    }

    public EnderecoDto getEnderecoDto() {
        return enderecoDto;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public String getEmail() {
        return email;
    }

    public static Cliente toCliente(ClienteDtoAtualizacao clienteDto, String cpf){
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);

        if(clienteDto.getEnderecoDto() != null){
            cliente.setEndereco(EnderecoDto.toEndereco(clienteDto.enderecoDto));
        }

        if(clienteDto.getEmail() != null){
            cliente.setEmail(clienteDto.getEmail());
        }

        if(clienteDto.getTelefones() != null){
            cliente.setTelefones(clienteDto.getTelefones());
        }

        return cliente;
    }
}
