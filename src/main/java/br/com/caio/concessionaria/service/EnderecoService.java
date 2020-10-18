package br.com.caio.concessionaria.service;

import br.com.caio.concessionaria.models.Endereco;
import br.com.caio.concessionaria.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void salvarEndereco(Endereco endereco){
        enderecoRepository.save(endereco);
    }

}
