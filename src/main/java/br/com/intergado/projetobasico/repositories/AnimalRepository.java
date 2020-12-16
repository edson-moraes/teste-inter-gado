package br.com.intergado.projetobasico.repositories;

import br.com.intergado.projetobasico.entities.Animal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal,Long> {

    List<Animal> findAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM Animal a  WHERE a.id=:id")
    void deleteAnimalById(Long id);
}
