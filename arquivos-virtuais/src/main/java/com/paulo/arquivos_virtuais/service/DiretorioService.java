package com.paulo.arquivos_virtuais.service;

import com.paulo.arquivos_virtuais.domain.dto.DiretorioDTO;
import com.paulo.arquivos_virtuais.domain.dto.FileDTO;
import com.paulo.arquivos_virtuais.exceptions.DataNotFoundException;
import com.paulo.arquivos_virtuais.models.Diretorio;
import com.paulo.arquivos_virtuais.models.File;
import com.paulo.arquivos_virtuais.repository.DiretorioRepository;
import com.paulo.arquivos_virtuais.service.converter.DiretorioConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiretorioService {

    @Autowired
    private DiretorioRepository diretorioRepository;
    @Autowired
    private DiretorioConverter diretorioConverter;
    @Autowired
    @Lazy
    private FileService fileService;

    public DiretorioDTO createDiretorio(DiretorioDTO diretorioDTO) {
        Diretorio diretorioConv = diretorioConverter.convertDTOtoEntity(diretorioDTO);
        diretorioConv.setParentDiretorio(getParentDiretorio(diretorioDTO.getParentDiretorioId()));
        return diretorioConverter.convertEntityTDTO(diretorioRepository.save(diretorioConv));
    }

    public List<DiretorioDTO> listAllDiretorios() {
        return diretorioConverter.convertEntityToDTOList(diretorioRepository.findByParentDiretorioIsNull());
    }

    public List<DiretorioDTO> listSubDiretorios(Long parentDiretorioId) {
        Diretorio parentDiretorio = getDiretorioExistente(parentDiretorioId);
        return diretorioConverter.convertEntityToDTOList(diretorioRepository.findByParentDiretorio(parentDiretorio));
    }

    public DiretorioDTO updateDiretorio(Long id, DiretorioDTO diretorioDTO) {
        Diretorio diretorioExistente = getDiretorioExistente(id);
        BeanUtils.copyProperties(diretorioDTO, diretorioExistente);
        diretorioExistente.setParentDiretorio(getParentDiretorio(diretorioDTO.getParentDiretorioId()));
        diretorioExistente.setSubDiretorios(getSubDiretorios(diretorioDTO));
        diretorioExistente.setFiles(getFiles(diretorioDTO.getFiles()));
        return diretorioConverter.convertEntityTDTO(diretorioRepository.save(diretorioExistente));
    }

    public void deleteDiretorio(Long id) {
        diretorioRepository.deleteById(id);
    }


    private List<Diretorio> getSubDiretorios(DiretorioDTO diretorioDTO) {
        return Optional.ofNullable(diretorioDTO.getSubDiretorios())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(diretorioConverter::convertDTOtoEntity)
                .collect(Collectors.toList());
    }

    private Diretorio getParentDiretorio(Long pParentDiretorioId) {
        return pParentDiretorioId == null
                ? null
                : diretorioRepository.findById(pParentDiretorioId)
                .orElseThrow(() -> new DataNotFoundException("Parent Diretorio não encontrado."));
    }

    public Diretorio getDiretorioExistente(Long id) {
        return diretorioRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Diretorio não encontrado."));
    }

    private List<File> getFiles(List<FileDTO> fileDTOList) {
        return Optional.ofNullable(fileDTOList)
                .orElse(Collections.emptyList())
                .stream()
                .map(fileDTO -> fileService.getFile(fileDTO.getDiretorioId()))
                .collect(Collectors.toList());
    }
}
