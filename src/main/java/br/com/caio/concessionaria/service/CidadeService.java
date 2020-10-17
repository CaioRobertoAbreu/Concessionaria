package br.com.caio.concessionaria.service;

import br.com.caio.concessionaria.models.Cidade;
import br.com.caio.concessionaria.repository.CidadeRepository;
import br.com.caio.concessionaria.service.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }


    public List<Cidade> buscarTodasCidades() {

        return  cidadeRepository.findAll();
    }

    public Page<Cidade> buscarTodasCidadesPorPaginacao(Integer pagina, Integer elementosPorPaginas) {

        PageRequest cidadesPaginadas = PageRequest.of(pagina, elementosPorPaginas);

        return cidadeRepository.findAll(cidadesPaginadas);
    }

    public void VerificaCidadeExistente(String id){
        if(!cidadeRepository.existsById(id)){
            throw new ObjectNotFoundException("Não existe cidade com este codigo");
        }
    }
}
