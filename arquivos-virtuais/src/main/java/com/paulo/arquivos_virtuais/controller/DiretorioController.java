package com.paulo.arquivos_virtuais.controller;

import com.paulo.arquivos_virtuais.domain.dto.DiretorioDTO;
import com.paulo.arquivos_virtuais.service.DiretorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/diretorios")
@CrossOrigin
public class DiretorioController {

    private final DiretorioService diretorioService;

    @Operation(summary = "Criar um novo diretório",
            description = "Cria um novo diretório no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Diretório criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<DiretorioDTO> createDiretorio(@RequestBody DiretorioDTO diretorioDTO) {
        return new ResponseEntity<>(diretorioService.createDiretorio(diretorioDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os diretórios",
            description = "Recupera uma lista de todos os diretórios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de diretórios recuperada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<DiretorioDTO>> listAllDiretorios() {
        return new ResponseEntity<>(diretorioService.listAllDiretorios(), HttpStatus.OK);
    }

    @Operation(summary = "Listar subdiretórios",
            description = "Recupera uma lista de subdiretórios para um determinado ID de diretório.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de subdiretórios recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Diretório não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}/subdiretorios")
    public ResponseEntity<List<DiretorioDTO>> listSubDiretorios(
            @Parameter(description = "ID do diretório para listar os subdiretórios", required = true)
            @PathVariable Long id) {
        return new ResponseEntity<>(diretorioService.listSubDiretorios(id), HttpStatus.OK);
    }

    @Operation(summary = "Atualizar um diretório",
            description = "Atualiza os detalhes de um diretório existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Diretório atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Diretório não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DiretorioDTO> updateDiretorio(
            @Parameter(description = "ID do diretório a ser atualizado", required = true)
            @PathVariable Long id,
            @RequestBody DiretorioDTO diretorioDTO) {
        return new ResponseEntity<>(diretorioService.updateDiretorio(id, diretorioDTO), HttpStatus.OK);
    }

    @Operation(summary = "Deletar um diretório",
            description = "Deleta um diretório do sistema pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Diretório deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Diretório não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiretorio(
            @Parameter(description = "ID do diretório a ser deletado", required = true)
            @PathVariable Long id) {
        diretorioService.deleteDiretorio(id);
        return ResponseEntity.noContent().build();
    }
}
