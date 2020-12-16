package br.com.intergado.projetobasico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDTO {
    private Long id;
    private String tag;
    private Long fazenda;
}
