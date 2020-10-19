package br.com.caio.concessionaria.service;

import br.com.caio.concessionaria.models.StatusSolicitacaoVenda;
import br.com.caio.concessionaria.repository.StatusSolicitacaoVendaRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusSolicitacaoVendaService {

    private final StatusSolicitacaoVendaRepository statusSolicitacaoVendaRepository;

    public StatusSolicitacaoVendaService(StatusSolicitacaoVendaRepository statusSolicitacaoVendaRepository) {
        this.statusSolicitacaoVendaRepository = statusSolicitacaoVendaRepository;
    }

    public void salvarStatus(StatusSolicitacaoVenda status) {
        statusSolicitacaoVendaRepository.save(status);
    }
}
