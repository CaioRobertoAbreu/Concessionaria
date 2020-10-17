package br.com.caio.concessionaria.repository;

import br.com.caio.concessionaria.models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, String> {
}
