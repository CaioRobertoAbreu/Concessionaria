package br.com.caio.concessionaria.service.validation;

import br.com.caio.concessionaria.controllers.exception.CampoErroValidacao;
import br.com.caio.concessionaria.controllers.exception.ErroValidacao;
import br.com.caio.concessionaria.dtos.ClienteDto;
import br.com.caio.concessionaria.dtos.VeiculoDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class AnoVeiculoValidator implements ConstraintValidator<AnoVeiculo, VeiculoDto> {
    public void initialize(AnoVeiculo constraint) {
    }

    public boolean isValid(VeiculoDto veiculoDto, ConstraintValidatorContext context) {
        List<CampoErroValidacao> erros = new ArrayList<>();

        int anoBaseValidacaoDeVeiculos = Integer.parseInt(Year.now().plusYears(2).toString());

        if(veiculoDto.getAnoVeiculo() >= anoBaseValidacaoDeVeiculos){
            erros.add(new CampoErroValidacao("anoVeiculo", "Campo inválido"));
        }

        for (CampoErroValidacao erro : erros) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(erro.getMensagem()).addPropertyNode(erro.getCampo())
                    .addConstraintViolation();
        }

        return erros.isEmpty();
    }
}
