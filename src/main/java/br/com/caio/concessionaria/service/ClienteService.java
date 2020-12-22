package br.com.caio.concessionaria.service;

import br.com.caio.concessionaria.dtos.ClienteDto;
import br.com.caio.concessionaria.dtos.ClienteDtoAtualizacao;
import br.com.caio.concessionaria.models.Cliente;
import br.com.caio.concessionaria.repository.ClienteRepository;
import br.com.caio.concessionaria.service.exception.ObjectExistException;
import br.com.caio.concessionaria.service.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;
    private final CidadeService cidadeService;

    public ClienteService(ClienteRepository clienteRepository,
                          EnderecoService enderecoService,
                          CidadeService cidadeService) {

        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
        this.cidadeService = cidadeService;
    }

    public Cliente buscarCliente(String cpf) {
        Optional<Cliente> cliente = clienteRepository.findById(cpf);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
    }


    public Cliente cadastrarCliente(ClienteDto clienteDto){

        if (clienteRepository.existsById(clienteDto.getCpf())){
            throw new ObjectExistException("Este CPF já consta cadastrado no sistema");
        }

        cidadeService.VerificaCidadeInexistente(clienteDto.getEndereco().getCidadeId());

        Cliente cliente = ClienteDto.toCliente(clienteDto);

        enderecoService.salvarEndereco(cliente.getEndereco());
        return clienteRepository.save(cliente);

    }


    public void atualizaCliente(ClienteDtoAtualizacao clienteDto, String cpf) {
        if(!clienteRepository.existsById(cpf)) {
            throw new ObjectNotFoundException("Cliente não encontrado");
        }

        Cliente cliente = ClienteDtoAtualizacao.toCliente(clienteDto, cpf);

        efetuarAtualizacao(cliente);
    }


    private void efetuarAtualizacao(Cliente cliente) {
        Cliente clienteAtualizado = new Cliente();

        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getCpf());

        if(clienteOptional.orElse(null) != null) {

            clienteAtualizado = clienteOptional.get();

            if(cliente.getEndereco() != null) {
                clienteAtualizado.setEndereco(cliente.getEndereco());
            }

            if(cliente.getEmail() != null) {
                clienteAtualizado.setEmail(cliente.getEmail());
            }

            if(cliente.getTelefones() != null) {
                clienteAtualizado.setTelefones(cliente.getTelefones());
            }
        }
        clienteRepository.save(clienteAtualizado);
    }


    public boolean verificaClienteCadastradoNoSistema(String cpfCliente) {
        return clienteRepository.existsById(cpfCliente);
    }

    public void deletarCliente(String cpf) {
        Cliente cliente = buscarCliente(cpf);

        try {
            clienteRepository.delete(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir cliente com veículo cadastrado");
        }
    }
}
