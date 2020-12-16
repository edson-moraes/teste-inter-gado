package br.com.intergado.projetobasico.controllers;

import br.com.intergado.projetobasico.dto.AnimalDTO;
import br.com.intergado.projetobasico.service.AnimalService;
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
@RequestMapping("animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalDTO>> getAnimal() {
        return ResponseEntity.ok(animalService.findAllAnimals());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDTO> getAnimal(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.findAnimal(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDTO> createAnimal(@RequestBody AnimalDTO animal) {
        return ResponseEntity.ok(animalService.createAnimal(animal));
    }

    @PostMapping(path = "/bulk",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalDTO>> createAnimals(@RequestBody List<AnimalDTO> animal) {
        return ResponseEntity.ok(animalService.createAnimals(animal));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<AnimalDTO> updateAnimal(@RequestBody AnimalDTO animal) {
        return ResponseEntity.ok(animalService.updateAnimal(animal));
    }

}
