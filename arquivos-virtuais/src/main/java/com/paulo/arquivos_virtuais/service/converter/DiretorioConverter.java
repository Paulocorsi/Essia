package com.paulo.arquivos_virtuais.service.converter;

import com.paulo.arquivos_virtuais.domain.dto.DiretorioDTO;
import com.paulo.arquivos_virtuais.models.Diretorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DiretorioConverter {

    private FileConverter fileConverter;

    public DiretorioDTO convertEntityTDTO(Diretorio diretorio) {
        DiretorioDTO diretorioResponseDTO = new DiretorioDTO();
        BeanUtils.copyProperties(diretorio, diretorioResponseDTO);
        diretorioResponseDTO.setSubDiretorios(convertEntityToDTOList(diretorio.getSubDiretorios()));
        diretorioResponseDTO.setParentDiretorioId(Optional.ofNullable(diretorio.getParentDiretorio())
                .map(Diretorio::getId)
                .orElse(null));
        diretorioResponseDTO.setFiles(fileConverter.convertEntityToDTOList(diretorio.getFiles()));
        return diretorioResponseDTO;
    }

    public Diretorio convertDTOtoEntity(DiretorioDTO diretorioDTO) {
        Diretorio entity = new Diretorio();
        BeanUtils.copyProperties(diretorioDTO, entity);
        return entity;
    }

    public List<DiretorioDTO> convertEntityToDTOList(List<Diretorio> diretorios) {
        return Optional.ofNullable(diretorios)
                .map(list -> list.stream()
                        .map(this::convertEntityTDTO)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

}
