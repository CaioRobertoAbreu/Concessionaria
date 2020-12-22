package br.com.caio.concessionaria;

import br.com.caio.concessionaria.models.Cidade;
import br.com.caio.concessionaria.models.Cliente;
import br.com.caio.concessionaria.models.Endereco;
import br.com.caio.concessionaria.repository.CidadeRepository;
import br.com.caio.concessionaria.repository.ClienteRepository;
import br.com.caio.concessionaria.repository.EnderecoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ConcessionariaApplication implements CommandLineRunner {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;
    private final CidadeRepository cidadeRepository;

    public ConcessionariaApplication(EnderecoRepository enderecoRepository,
                                     ClienteRepository clienteRepository,
                                     CidadeRepository cidadeRepository) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
        this.cidadeRepository = cidadeRepository;
    }


    public static void main(String[] args) {

        SpringApplication.run(ConcessionariaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
