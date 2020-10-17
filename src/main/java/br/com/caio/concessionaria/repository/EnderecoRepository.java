package br.com.caio.concessionaria.repository;

import br.com.caio.concessionaria.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
