package br.com.caio.concessionaria.service;

import br.com.caio.concessionaria.dtos.VeiculoDeleteDto;
import br.com.caio.concessionaria.dtos.VeiculoDto;
import br.com.caio.concessionaria.models.Veiculo;
import br.com.caio.concessionaria.repository.VeiculoRepository;
import br.com.caio.concessionaria.service.exception.ObjectExistException;
import br.com.caio.concessionaria.service.exception.ObjectNotFoundException;
import jdk.swing.interop.SwingInterOpUtils;
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

    public void atualizarVeiculo(VeiculoDto veiculoDto) {
        if (!veiculoRepository.existsById(veiculoDto.getPlaca().toUpperCase())){
            throw new ObjectNotFoundException("Veículo não encontrado. Placa: " + veiculoDto.getPlaca().toUpperCase());
        }
        lancaExcecaoParaClienteInexistente(veiculoDto.getCpfProprietario());

        efetuarAtualizacao(veiculoDto);
    }

    private void efetuarAtualizacao(VeiculoDto veiculoDto) {
      Veiculo veiculo = new Veiculo();

      Optional<Veiculo> veiculoEncontrado = veiculoRepository.findById(veiculoDto.getPlaca().toUpperCase());

        if (veiculoEncontrado.orElse(null) != null) {
            veiculo = veiculoEncontrado.get();

            if(!veiculoDto.getPlaca().equals(veiculo.getPlaca())) {
                veiculo.setPlaca(veiculoDto.getPlaca());
            }

            if(!veiculoDto.getAnoVeiculo().equals(veiculo.getAnoVeiculo())) {
                veiculo.setAnoVeiculo(veiculoDto.getAnoVeiculo());
            }

            if(!veiculoDto.getModelo().equals(veiculo.getModelo())){
                veiculo.setModelo(veiculoDto.getModelo());
            }
        }

        veiculoRepository.save(veiculo);
    }

    public void deletarVeiculo(VeiculoDeleteDto veiculo) {
        String cpf = veiculo.getCpfProprietario();
        String placa = veiculo.getPlaca().toUpperCase();

        lancaExcecaoParaClienteInexistente(cpf);
        buscaVeiculoCadastrado(cpf, placa);
        veiculoRepository.deletarVeiculo(placa, cpf);
    }

}
