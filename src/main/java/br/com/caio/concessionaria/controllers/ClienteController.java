package br.com.caio.concessionaria.controllers;

import br.com.caio.concessionaria.dtos.ClienteDto;
import br.com.caio.concessionaria.dtos.ClienteDtoAtualizacao;
import br.com.caio.concessionaria.models.Cliente;
import br.com.caio.concessionaria.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable String cpf){
        Cliente cliente = clienteService.buscarCliente(cpf);

        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarCliente(@RequestBody @Valid ClienteDto clienteDto){
        Cliente cliente = clienteService.cadastrarCliente(clienteDto);
        URI uri = ServletUriComponentsBuilder.fromHttpUrl("http://localhost:8080/cliente")
                .path("/{cpf}").buildAndExpand(cliente.getCpf()).toUri();

        return ResponseEntity.created(uri).body("Cliente cadastrado com sucesso");
    }

    @PatchMapping("/atualizar/{cpf}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable String cpf,
                                                   @RequestBody ClienteDtoAtualizacao clienteDto){
        clienteService.atualizaCliente(clienteDto, cpf);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletar/{cpf}")
    public ResponseEntity<?> deletarCliente(@PathVariable String cpf){

        clienteService.deletarCliente(cpf);

        return ResponseEntity.ok().build();
    }

    //Todo fazer controller para agendamento
}
