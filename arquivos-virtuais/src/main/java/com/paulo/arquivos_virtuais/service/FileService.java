package com.paulo.arquivos_virtuais.service;

import com.paulo.arquivos_virtuais.domain.dto.FileDTO;
import com.paulo.arquivos_virtuais.exceptions.DataNotFoundException;
import com.paulo.arquivos_virtuais.models.Diretorio;
import com.paulo.arquivos_virtuais.models.File;
import com.paulo.arquivos_virtuais.repository.FileRepository;
import com.paulo.arquivos_virtuais.service.converter.FileConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileConverter fileConverter;
    @Autowired
    @Lazy
    private DiretorioService diretorioService;

    public FileDTO createFile(FileDTO fileDTO) {
        File fileConv = fileConverter.convertDTOtoEntity(fileDTO);
        fileConv.setDiretorio(getDiretorioId(fileDTO.getDiretorioId()));
        return fileConverter.convertEntityToDTO(fileRepository.save(fileConv));
    }

    public List<FileDTO> listFiles() {
        return fileConverter.convertEntityToDTOList(fileRepository.findAll());
    }

    public FileDTO updateFile(Long id, FileDTO fileDTO) {
        File fileExistente = getFile(id);
        BeanUtils.copyProperties(fileDTO, fileExistente);
        return fileConverter.convertEntityToDTO(fileRepository.save(fileExistente));
    }

    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }

    public File getFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Arquivo não encontrado"));
    }

    private Diretorio getDiretorioId(Long diretorioId) {
        return Optional.ofNullable(diretorioId)
                .map(diretorioService::getDiretorioExistente)
                .orElse(null);
    }
}