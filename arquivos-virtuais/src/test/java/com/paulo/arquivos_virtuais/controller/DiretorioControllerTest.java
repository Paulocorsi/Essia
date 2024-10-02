package com.paulo.arquivos_virtuais.controller;

import com.paulo.arquivos_virtuais.domain.dto.DiretorioDTO;
import com.paulo.arquivos_virtuais.service.DiretorioService;
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

class DiretorioControllerTest {

    @Mock
    DiretorioService diretorioService;

    @InjectMocks
    DiretorioController diretorioController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDiretorio() {
        when(diretorioService.createDiretorio(any())).thenReturn(any(DiretorioDTO.class));
        ResponseEntity<DiretorioDTO> response = diretorioController.createDiretorio(new DiretorioDTO());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void listAllDiretorios() {
        when(diretorioService.listAllDiretorios()).thenReturn(List.of(new DiretorioDTO()));
        ResponseEntity<List<DiretorioDTO>> response = diretorioController.listAllDiretorios();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void listSubDiretorios() {
        when(diretorioService.listSubDiretorios(anyLong())).thenReturn(List.of(new DiretorioDTO()));
        ResponseEntity<List<DiretorioDTO>> response = diretorioController.listSubDiretorios(anyLong());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateDiretorio() {
        when(diretorioService.updateDiretorio(anyLong(), any(DiretorioDTO.class))).thenReturn(new DiretorioDTO());
        ResponseEntity<DiretorioDTO> response = diretorioController.updateDiretorio(anyLong(), any(DiretorioDTO.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteDiretorio() {
        ResponseEntity<Void> response = diretorioController.deleteDiretorio(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}