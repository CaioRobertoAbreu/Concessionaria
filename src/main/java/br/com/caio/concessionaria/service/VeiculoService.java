package br.com.caio.concessionaria.service;

import br.com.caio.concessionaria.dtos.VeiculoDto;
import br.com.caio.concessionaria.models.Veiculo;
import br.com.caio.concessionaria.repository.VeiculoRepository;
import br.com.caio.concessionaria.service.exception.ObjectExistException;
import br.com.caio.concessionaria.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ClienteService clienteService;
    private final StatusSolicitacaoVendaService statusSolicitacaoVendaService;

    public VeiculoService(VeiculoRepository veiculoRepository,
                          ClienteService clienteService,
                          StatusSolicitacaoVendaService statusSolicitacaoVendaService) {

        this.veiculoRepository = veiculoRepository;
        this.clienteService = clienteService;
        this.statusSolicitacaoVendaService = statusSolicitacaoVendaService;
    }


    public Veiculo buscaVeiculoCadastrado(String cpf, String placa){
        lancaExcecaoParaClienteInexistente(cpf);

        Optional<Veiculo> veiculo = veiculoRepository.findById(placa);

        return veiculo.orElseThrow(() -> new ObjectNotFoundException("Veiculo não encontrado. Placa  " +
                placa));
    }


    public Veiculo cadastrarVeiculo(VeiculoDto veiculoDto) {
        Veiculo veiculo = VeiculoDto.toVeiculo(veiculoDto);
        lancaExcecaoParaClienteInexistente(veiculoDto.getCpfProprietario());

        if(!veiculoRepository.existsById(veiculo.getPlaca())){
            statusSolicitacaoVendaService.salvarStatus(veiculo.getStatusVenda());
            return veiculoRepository.save(veiculo);
        }

        throw new ObjectExistException("Já existe um veículo cadastrado com esta placa. Placa:" + veiculo.getPlaca());
    }


    private void lancaExcecaoParaClienteInexistente(String cpfCliente) {
        if(!clienteService.verificaClienteCadastradoNoSistema(cpfCliente)){
            throw new ObjectNotFoundException("Cliente não encontrado");
        }
    }
}
