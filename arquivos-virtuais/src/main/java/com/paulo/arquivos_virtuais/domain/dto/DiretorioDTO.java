package com.paulo.arquivos_virtuais.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DiretorioDTO {

    private Long id;

    @NotNull
    private String nome;

    private Long parentDiretorioId;

    private List<DiretorioDTO> subDiretorios = new ArrayList<>();

    private List<FileDTO> files = new ArrayList<>();

}
