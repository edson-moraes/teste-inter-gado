package br.com.intergado.projetobasico.repositories;

import br.com.intergado.projetobasico.entities.Fazenda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FazendaRepository extends CrudRepository<Fazenda,Long> {

    List<Fazenda> findAll();
}
