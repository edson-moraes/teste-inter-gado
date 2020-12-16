package br.com.intergado.projetobasico.service;

import br.com.intergado.projetobasico.dto.FazendaDTO;
import br.com.intergado.projetobasico.entities.Fazenda;
import br.com.intergado.projetobasico.repositories.FazendaRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FazendaService {

    private final FazendaRepository fazendaRespository;
    private final ModelMapper modelMapper;

    @Autowired
    public FazendaService(FazendaRepository fazendaRespository, ModelMapper modelMapper) {
        this.fazendaRespository = fazendaRespository;
        this.modelMapper = modelMapper;
    }

    public List<FazendaDTO> findAllFazendas() {
        List<FazendaDTO> fazendas = new ArrayList<>();
        fazendaRespository.findAll().forEach(f -> fazendas.add(EntityToDTO(f)));
        return fazendas;
    }

    public FazendaDTO findFazenda(Long id) {
        Fazenda fazenda = fazendaRespository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return EntityToDTO(fazenda);
    }

    public FazendaDTO createFazenda(FazendaDTO fazendaDTO) {
        Fazenda fazenda = DTOToEntity(fazendaDTO);
        if (fazendaDTO.getId() != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return EntityToDTO(fazendaRespository.save(fazenda));
    }

    public FazendaDTO updateFazenda(FazendaDTO fazendaDTO) {
        Fazenda fazenda = DTOToEntity(fazendaDTO);
        if (fazendaDTO.getId() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return EntityToDTO(fazendaRespository.save(fazenda));
    }

    public void deleteFazenda(Long id) {
        fazendaRespository.deleteById(id);
    }


    private Fazenda DTOToEntity(FazendaDTO from) {
        return modelMapper.map(from, Fazenda.class);
    }

    private FazendaDTO EntityToDTO(Fazenda from) {
        Converter<Fazenda, Long> converter = mappingContext -> mappingContext.getSource().getId();
        modelMapper.addConverter(converter,Fazenda.class,Long.class);
        return modelMapper.map(from, FazendaDTO.class);
    }
}