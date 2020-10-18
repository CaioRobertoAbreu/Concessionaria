package br.com.caio.concessionaria.controllers;

import br.com.caio.concessionaria.models.Cidade;
import br.com.caio.concessionaria.service.CidadeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Cidade>> buscarTodasCidades() {
        List<Cidade> cidades = cidadeService.buscarTodasCidades();

        return ResponseEntity.ok().body(cidades);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Cidade>> buscarTodasCidadesPorPaginacao(
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "25") Integer elementosPorPagina) {

        Page<Cidade> cidades = cidadeService.buscarTodasCidadesPorPaginacao(pagina, elementosPorPagina);


        return ResponseEntity.ok().body(cidades);

    }
}
