package com.paulo.arquivos_virtuais.service.converter;

import com.paulo.arquivos_virtuais.domain.dto.FileDTO;
import com.paulo.arquivos_virtuais.models.File;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileConverter {

    public File convertDTOtoEntity(FileDTO fileDTO){
        File entity = new File();
        BeanUtils.copyProperties(fileDTO, entity);
        return entity;
    }

    public FileDTO convertEntityToDTO(File file){
        FileDTO fileDTO = new FileDTO();
        BeanUtils.copyProperties(file, fileDTO);
        fileDTO.setDiretorioId(file.getDiretorio().getId());
        return fileDTO;
    }

    public List<FileDTO> convertEntityToDTOList(List<File> fileList){
        return fileList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public List<File> convertDTOtoEntityList(List<FileDTO> fileDTOList){
        return fileDTOList.stream().map(this::convertDTOtoEntity).collect(Collectors.toList());
    }
}
