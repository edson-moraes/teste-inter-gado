package br.com.intergado.projetobasico.controllers;

import br.com.intergado.projetobasico.dto.FazendaDTO;
import br.com.intergado.projetobasico.service.FazendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("fazenda")
public class FazendaController {

    private final FazendaService fazendaService;

    @Autowired
    public FazendaController(FazendaService fazendaService) {
        this.fazendaService = fazendaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FazendaDTO>> getFazenda() {
        return ResponseEntity.ok(fazendaService.findAllFazendas());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FazendaDTO> getFazenda(@PathVariable Long id) {
        return ResponseEntity.ok(fazendaService.findFazenda(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FazendaDTO> createFazenda(@RequestBody FazendaDTO Fazenda) {
        return ResponseEntity.ok(fazendaService.createFazenda(Fazenda));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteFazenda(@PathVariable Long id) {
        fazendaService.deleteFazenda(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<FazendaDTO> updateFazenda(@RequestBody FazendaDTO Fazenda) {
        return ResponseEntity.ok(fazendaService.updateFazenda(Fazenda));
    }
}
