package br.com.caio.concessionaria.service.validation;

import br.com.caio.concessionaria.controllers.exception.CampoErroValidacao;
import br.com.caio.concessionaria.dtos.ClienteDto;
import org.apache.tomcat.jni.Local;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class DataNascimentoValidator implements ConstraintValidator<DataNascimento, ClienteDto> {

    public void initialize(DataNascimento constraint) {
    }

    @Override
    public boolean isValid(ClienteDto clienteDto, ConstraintValidatorContext context) {
        List<CampoErroValidacao> erros = new ArrayList<>();

        int dia, mes, ano;

        dia = Integer.parseInt(clienteDto.getDataNascimento().substring(0, 2));
        mes = Integer.parseInt(clienteDto.getDataNascimento().substring(3, 5));
        ano = Integer.parseInt(clienteDto.getDataNascimento().substring(6, 10));

        LocalDate data = LocalDate.now();
        try{
            data = LocalDate.of(ano, mes, dia);

        } catch (DateTimeException e){
            erros.add(new CampoErroValidacao("dataNascimento", "Data de nascimento inválida"));
        }

        int anoAtual = Integer.parseInt(Year.now().toString());

        if(!verificaMaioridade(data)) {
            erros.add(new CampoErroValidacao("dataNascimento", "Menor de 18 anos"));
        }


        if( (anoAtual - data.getYear() > 150) || data.isAfter(LocalDate.now())) {
            erros.add(new CampoErroValidacao("dataNascimento", "Erro ao processar data de nascimento"));
        }

        for (CampoErroValidacao erro : erros) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(erro.getMensagem()).addPropertyNode(erro.getCampo())
                    .addConstraintViolation();
        }

        return erros.isEmpty();
    }

    private boolean verificaMaioridade(LocalDate dataNascimento){
        int anoNascimento = dataNascimento.getYear();
        int anoAtual = Integer.parseInt(Year.now().toString());

        int idade = anoAtual - anoNascimento;
        if(idade < 18) {
            return false;
        } else if (idade == 18) {
            MonthDay diaEMesAtual = MonthDay.now();
            MonthDay diaEMesNascimento = MonthDay.of(dataNascimento.getMonth(), dataNascimento.getDayOfMonth());

            return diaEMesAtual.isAfter(diaEMesNascimento);
        }

        return true;
    }

}

