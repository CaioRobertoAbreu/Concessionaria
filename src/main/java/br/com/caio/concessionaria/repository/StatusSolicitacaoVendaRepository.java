package br.com.caio.concessionaria.repository;

import br.com.caio.concessionaria.models.StatusSolicitacaoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusSolicitacaoVendaRepository extends JpaRepository<StatusSolicitacaoVenda, String> {
}
