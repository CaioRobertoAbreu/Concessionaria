package br.com.caio.concessionaria.dtos;

import br.com.caio.concessionaria.models.Cliente;
import br.com.caio.concessionaria.models.Status;
import br.com.caio.concessionaria.models.StatusSolicitacaoVenda;
import br.com.caio.concessionaria.models.Veiculo;
import br.com.caio.concessionaria.service.validation.AnoVeiculo;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@AnoVeiculo
public class VeiculoDeleteDto implements Serializable {
    private static final long serialVersionUID = -8708745828774011463L;

    @CPF(message = "CPF inválido")
    @NotEmpty(message = "preenchimento obrigatório")
    private final String cpfProprietario;
    @NotEmpty(message = "preenchimento obrigatório")
    private final String placa;

    public VeiculoDeleteDto(String cpfProprietario, String placa) {
        this.cpfProprietario = cpfProprietario;
        this.placa = placa;

    }

    public String getCpfProprietario() {
        return cpfProprietario;
    }

    public String getPlaca() {
        return placa;
    }

}
