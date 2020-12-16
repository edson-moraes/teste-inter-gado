package br.com.intergado.projetobasico.service;

import br.com.intergado.projetobasico.dto.AnimalDTO;
import br.com.intergado.projetobasico.entities.Animal;
import br.com.intergado.projetobasico.entities.Fazenda;
import br.com.intergado.projetobasico.repositories.AnimalRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AnimalService {

    private final AnimalRepository animalRespository;
    private final ModelMapper modelMapper;

    @Autowired
    public AnimalService(AnimalRepository animalRespository, ModelMapper modelMapper) {
        this.animalRespository = animalRespository;
        this.modelMapper = modelMapper;
    }

    public List<AnimalDTO> findAllAnimals() {
        List<AnimalDTO> animals = new ArrayList<>();
        animalRespository.findAll().forEach(a -> animals.add(EntityToDTO(a)));
        return animals;
    }

    public AnimalDTO findAnimal(Long id) {
        Animal animal = animalRespository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return EntityToDTO(animal);
    }

    public AnimalDTO createAnimal(AnimalDTO animalDTO) {
        Animal animal = DTOToEntity(animalDTO);
        if (animal.getId() != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        return EntityToDTO(animalRespository.save(animal));
    }

    public List<AnimalDTO> createAnimals(List<AnimalDTO> animalsDTO) {
        Iterable<Animal> animals = animalRespository.saveAll(animalsDTO.stream().map(this::DTOToEntity).collect(Collectors.toList()));
        List<Animal> savedAnimals = StreamSupport.stream(animals.spliterator(), false).collect(Collectors.toList());
        return savedAnimals.stream().map(this::EntityToDTO).collect(Collectors.toList());
    }

    public AnimalDTO updateAnimal(AnimalDTO animalDTO) {
        Animal animal = DTOToEntity(animalDTO);
        if (animal.getId() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return EntityToDTO(animalRespository.save(animal));
    }

    public void deleteAnimal(Long id) {
        animalRespository.deleteAnimalById(id);
    }

    private Animal DTOToEntity(AnimalDTO from) {
        Converter<Long, Fazenda> converter = mappingContext -> Fazenda.builder().id(mappingContext.getSource()).build();
        modelMapper.addConverter(converter, Long.class, Fazenda.class);
        return modelMapper.map(from, Animal.class);
    }

    private AnimalDTO EntityToDTO(Animal from) {
        return modelMapper.map(from, AnimalDTO.class);
    }


}
