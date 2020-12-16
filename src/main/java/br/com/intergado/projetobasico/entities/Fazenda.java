package br.com.intergado.projetobasico.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Fazenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FG")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "fazenda", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Animal> animals = new HashSet<>();
}
