package com.paulo.arquivos_virtuais.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileDTO {

    @NotNull
    private String nome;

    @NotNull
    private String fileType;

    @NotNull
    private Long diretorioId;

}