package br.com.caio.concessionaria.controllers;

import br.com.caio.concessionaria.dtos.VeiculoDeleteDto;
import br.com.caio.concessionaria.dtos.VeiculoDto;
import br.com.caio.concessionaria.models.Veiculo;
import br.com.caio.concessionaria.service.VeiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping("/{cpf}/{placa}")
    public ResponseEntity<Veiculo> buscarVeiculo(@PathVariable String cpf,
                                                 @PathVariable String placa) {
        Veiculo veiculo = veiculoService.buscaVeiculoCadastrado(cpf, placa);

        return ResponseEntity.ok().body(veiculo);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarVeiculo(@RequestBody @Valid VeiculoDto veiculoDto) {

        Veiculo veiculo = veiculoService.cadastrarVeiculo(veiculoDto);
        URI uri = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8080/veiculo/" +
                veiculo.getProprietario().getCpf()).path("/{placa}")
                .buildAndExpand(veiculoDto.getPlaca()).toUri();

        return ResponseEntity.created(uri).body("Veiculo cadastrado");
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<String> atualizarVeiculo(@RequestBody @Valid VeiculoDto veiculoDto) {

        veiculoService.atualizarVeiculo(veiculoDto);

        return ResponseEntity.noContent().build();
    }

    //TODO deletar veiculo

    @DeleteMapping("/deletar")
    public ResponseEntity<Veiculo> deletarVeiculo(@RequestBody VeiculoDeleteDto veiculo) {
        veiculoService.deletarVeiculo(veiculo);

        return ResponseEntity.noContent().build();
    }
}
