package com.paulo.arquivos_virtuais.controller;

import com.paulo.arquivos_virtuais.domain.dto.FileDTO;
import com.paulo.arquivos_virtuais.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class FileControllerTest {

    @Mock
    FileService fileService;

    @InjectMocks
    FileController fileController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createFile() {
        when(fileService.createFile(any(FileDTO.class))).thenReturn(any(FileDTO.class));
        ResponseEntity<FileDTO> response = fileController.createFile(new FileDTO());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void listAllFiles() {
        when(fileService.listFiles()).thenReturn(List.of(new FileDTO()));
        ResponseEntity<List<FileDTO>> response = fileController.listAllFiles();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateFile() {
        when(fileService.updateFile(anyLong(), any(FileDTO.class))).thenReturn(new FileDTO());
        ResponseEntity<FileDTO> response = fileController.updateFile(anyLong(), any(FileDTO.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deteleFile() {
        ResponseEntity<Void> response = fileController.deleteFile(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}