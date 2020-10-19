package br.com.caio.concessionaria.dtos;

import br.com.caio.concessionaria.models.Cliente;
import br.com.caio.concessionaria.models.Status;
import br.com.caio.concessionaria.models.StatusSolicitacaoVenda;
import br.com.caio.concessionaria.models.Veiculo;

import java.io.Serializable;

public class VeiculoDto implements Serializable {
    private static final long serialVersionUID = -8708745828774011463L;

    //Todo fazer validacoes

    private final String cpfProprietario;
    private final String placa;
    private final Integer anoVeiculo;
    private final String modelo;

    public VeiculoDto(String cpfProprietario, String placa, Integer anoVeiculo, String modelo) {
        this.cpfProprietario = cpfProprietario;
        this.placa = placa;
        this.anoVeiculo = anoVeiculo;
        this.modelo = modelo;
    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public Integer getAnoVeiculo() {
        return anoVeiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public static Veiculo toVeiculo(VeiculoDto veiculoDto) {
        Cliente cliente = new Cliente();
        cliente.setCpf(veiculoDto.getCpfProprietario());

        Veiculo veiculo = new Veiculo(cliente, veiculoDto.getPlaca().toUpperCase(), veiculoDto.getAnoVeiculo(),
                veiculoDto.getModelo());

        StatusSolicitacaoVenda status = new StatusSolicitacaoVenda(null, Status.AGENDAMENTO_NAO_REALIZADO,
                veiculo);

        veiculo.setStatusVenda(status);

        return veiculo;
    }
}
