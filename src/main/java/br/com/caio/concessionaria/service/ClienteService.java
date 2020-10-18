package br.com.caio.concessionaria.service;

import br.com.caio.concessionaria.dtos.ClienteDto;
import br.com.caio.concessionaria.models.Cliente;
import br.com.caio.concessionaria.models.Endereco;
import br.com.caio.concessionaria.repository.ClienteRepository;
import br.com.caio.concessionaria.repository.EnderecoRepository;
import br.com.caio.concessionaria.service.exception.ObjectExistException;
import br.com.caio.concessionaria.service.exception.ObjectNotFoundException;
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

    private void verifcaClienteCadastradoNoSistema(String cpf){

        boolean existe = clienteRepository.existsById(cpf);

        if(existe) {
            throw new ObjectExistException("Este CPF já consta cadastrado no sistema");
        }
    }

    public Cliente cadastrarCliente(ClienteDto clienteDto){
        verifcaClienteCadastradoNoSistema(clienteDto.getCpf());
        cidadeService.VerificaCidadeExistente(clienteDto.getEndereco().getCidadeId());

        Cliente cliente = ClienteDto.toCliente(clienteDto);

        enderecoService.salvarEndereco(cliente.getEndereco());
        return clienteRepository.save(cliente);

    }


}
