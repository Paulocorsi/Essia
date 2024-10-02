package com.paulo.arquivos_virtuais.service;

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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class FileServiceTest {

    @Mock
    FileRepository fileRepository;

    @Mock
    FileConverter fileConverter;

    @Mock
    DiretorioService diretorioService;

    @Mock
    DiretorioRepository diretorioRepository;

    @Mock
    DiretorioConverter diretorioConverter;

    @InjectMocks
    FileService fileService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createFile() {
        when(diretorioRepository.findById(anyLong())).thenReturn(Optional.ofNullable(mockDiretorio()));
        when(fileRepository.save(any(File.class))).thenReturn(mockFile());
        when(fileConverter.convertDTOtoEntity(any(FileDTO.class))).thenReturn(mockFile());
        when(fileConverter.convertEntityToDTO(any(File.class))).thenReturn(mockFileDTO());
        FileDTO expected = mockFileDTO();
        FileDTO response = fileService.createFile(mockFileDTO());

        assertNotNull(response);
        assertEquals(expected.getNome(), response.getNome());
        assertEquals(expected.getFileType(), response.getFileType());
        assertEquals(expected.getDiretorioId(), response.getDiretorioId());
        verify(fileRepository, times(1)).save(any(File.class));
    }

    @Test
    void listFiles() {
        when(fileRepository.findAll()).thenReturn(List.of(mockFile()));
        when(fileConverter.convertEntityToDTOList(anyList())).thenReturn(List.of(mockFileDTO()));
        List<FileDTO> response = fileService.listFiles();
        assertNotNull(response);
        assertEquals(1, response.size());
    }

    @Test
    void updateFile() {
        when(fileRepository.findById(anyLong())).thenReturn(Optional.of(mockFile()));
        when(fileRepository.save(any(File.class))).thenReturn(mockFile());
        fileService.updateFile(anyLong(), mockFileDTO());
        verify(fileRepository, times(1)).save(mockFile());
    }

    @Test
    void deleteFile() {
        fileService.deleteFile(anyLong());
        verify(fileRepository, times(1)).deleteById(any());
    }

    public Diretorio mockDiretorio() {
        return Diretorio.builder()
                .id(1L)
                .nome("Diretorio Teste")
                .subDiretorios(new ArrayList<>())
                .files(new ArrayList<>())
                .build();
    }

    public File mockFile() {
        return File.builder()
                .id(1L)
                .nome("FileTeste")
                .fileType(".pdf")
                .diretorio(mockDiretorio())
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