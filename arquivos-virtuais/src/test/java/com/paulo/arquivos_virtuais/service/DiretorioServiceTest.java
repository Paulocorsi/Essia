package com.paulo.arquivos_virtuais.service;

import com.paulo.arquivos_virtuais.domain.dto.DiretorioDTO;
import com.paulo.arquivos_virtuais.domain.dto.FileDTO;
import com.paulo.arquivos_virtuais.models.Diretorio;
import com.paulo.arquivos_virtuais.models.File;
import com.paulo.arquivos_virtuais.repository.DiretorioRepository;
import com.paulo.arquivos_virtuais.repository.FileRepository;
import com.paulo.arquivos_virtuais.service.converter.DiretorioConverter;
import com.paulo.arquivos_virtuais.service.converter.FileConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DiretorioServiceTest {

    @Mock
    DiretorioRepository diretorioRepository;

    @Mock
    DiretorioConverter diretorioConverter;

    @Mock
    FileConverter fileConverter;

    @Mock
    FileService fileService;

    @Mock
    FileRepository fileRepository;

    @InjectMocks
    DiretorioService diretorioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createDiretorio() {
        when(diretorioRepository.save(any(Diretorio.class))).thenReturn(mockDiretorio());
        when(diretorioConverter.convertDTOtoEntity(any(DiretorioDTO.class))).thenReturn(mockDiretorio());
        when(diretorioConverter.convertEntityTDTO(any(Diretorio.class))).thenReturn(mockDiretorioDTO());
        DiretorioDTO expected = mockDiretorioDTO();
        DiretorioDTO response = diretorioService.createDiretorio(mockDiretorioDTO());

        assertNotNull(response);
        assertEquals(expected.getId(), response.getId());
        assertEquals(expected.getNome(), response.getNome());
        assertEquals(expected.getParentDiretorioId(), response.getParentDiretorioId());
        assertEquals(expected.getFiles().size(), response.getFiles().size());
        assertEquals(expected.getSubDiretorios().size(), response.getSubDiretorios().size());
    }

    @Test
    void listAllDiretorios() {
        when(diretorioRepository.findByParentDiretorioIsNull()).thenReturn(List.of(mockDiretorio()));
        when(diretorioConverter.convertEntityToDTOList(anyList())).thenReturn(List.of(mockDiretorioDTO()));
        List<DiretorioDTO> expected = List.of(mockDiretorioDTO());
        List<DiretorioDTO> response = diretorioService.listAllDiretorios();

        assertNotNull(response);
        assertEquals(expected.size(), response.size());
        assertEquals(expected.get(0).getParentDiretorioId(), response.get(0).getParentDiretorioId());
        assertEquals(expected.get(0).getSubDiretorios().size(), response.get(0).getSubDiretorios().size());
        assertEquals(expected.get(0).getFiles().size(), response.get(0).getFiles().size());
    }

    @Test
    void listSubDiretorios() {
        when(diretorioRepository.findById(any())).thenReturn(Optional.of(mockDiretorio()));
        when(diretorioRepository.findByParentDiretorio(any())).thenReturn(List.of(mockDiretorio()));
        when(diretorioConverter.convertEntityToDTOList(anyList())).thenReturn(List.of(mockDiretorioDTO()));
        List<DiretorioDTO> expected = List.of(mockDiretorioDTO());
        List<DiretorioDTO> response = diretorioService.listSubDiretorios(any());

        assertNotNull(response);
        assertEquals(expected.size(), response.size());
        assertEquals(expected.get(0).getParentDiretorioId(), response.get(0).getParentDiretorioId());
        assertEquals(expected.get(0).getSubDiretorios().size(), response.get(0).getSubDiretorios().size());
        assertEquals(expected.get(0).getFiles().size(), response.get(0).getFiles().size());
    }

    @Test
    void updateDiretorio() {
        when(diretorioRepository.findById(anyLong())).thenReturn(Optional.of(mockDiretorio()));
        when(diretorioRepository.save(any(Diretorio.class))).thenReturn(mockDiretorio());
        when(fileRepository.findById(anyLong())).thenReturn(Optional.of(mockFile(mockDiretorio())));
        diretorioService.updateDiretorio(anyLong(), mockDiretorioDTO());
        verify(diretorioRepository, times(1)).save(mockDiretorio());
    }

    @Test
    void deleteDiretorio() {
        diretorioService.deleteDiretorio(any());
        verify(diretorioRepository, times(1)).deleteById(any());
    }

    public Diretorio mockDiretorio() {
        Diretorio diretorio = Diretorio.builder()
                .id(1L)
                .nome("Diretorio Teste")
                .build();
        diretorio.setSubDiretorios(List.of(mockSubDiretorio(diretorio)));
        diretorio.setFiles(List.of(mockFile(diretorio)));
        return diretorio;
    }

    public File mockFile(Diretorio diretorio) {
        return File.builder()
                .nome("FileTeste")
                .fileType(".pdf")
                .diretorio(diretorio)
                .build();
    }

    public DiretorioDTO mockDiretorioDTO() {
        return DiretorioDTO.builder()
                .id(1L)
                .nome("Diretorio Teste")
                .subDiretorios(List.of(mockSubDiretorioDTO()))
                .files(List.of(mockFileDTO()))
                .build();
    }

    public Diretorio mockSubDiretorio(Diretorio diretorio) {
        return Diretorio.builder()
                .id(2L)
                .nome("SubDiretorio Teste")
                .subDiretorios(List.of(diretorio))
                .files(new ArrayList<>())
                .build();
    }

    public DiretorioDTO mockSubDiretorioDTO() {
        return DiretorioDTO.builder()
                .id(2L)
                .nome("SubDiretorio Teste")
                .subDiretorios(new ArrayList<>())
                .files(new ArrayList<>())
                .build();
    }

    public FileDTO mockFileDTO() {
        return FileDTO.builder()
                .nome("FileTeste")
                .fileType(".pdf")
                .diretorioId(1L)
                .build();
    }
}