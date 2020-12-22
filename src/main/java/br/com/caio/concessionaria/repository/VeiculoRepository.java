package br.com.caio.concessionaria.repository;

import br.com.caio.concessionaria.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Veiculo v WHERE v.placa = :placa AND " +
            "v.proprietario.cpf = :cpf")
    void deletarVeiculo(@Param("placa") String placa,
                        @Param("cpf") String cpfProprietario);
}
