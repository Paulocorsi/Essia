package com.paulo.arquivos_virtuais.controller;

import com.paulo.arquivos_virtuais.domain.dto.FileDTO;
import com.paulo.arquivos_virtuais.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/files")
@CrossOrigin
public class FileController {

    @Autowired
    private FileService fileService;

    @Operation(summary = "Criar um novo arquivo",
            description = "Cria um novo arquivo no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Arquivo criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<FileDTO> createFile(@RequestBody FileDTO fileDTO) {
        return new ResponseEntity<>(fileService.createFile(fileDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os arquivos",
            description = "Recupera uma lista de todos os arquivos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de arquivos recuperada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<FileDTO>> listAllFiles() {
        return new ResponseEntity<>(fileService.listFiles(), HttpStatus.OK);
    }

    @Operation(summary = "Atualizar um arquivo",
            description = "Atualiza os detalhes de um arquivo existente pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Arquivo não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FileDTO> updateFile(
            @Parameter(description = "ID do arquivo a ser atualizado", required = true)
            @PathVariable Long id,
            @RequestBody FileDTO fileDTO) {
        return new ResponseEntity<>(fileService.updateFile(id, fileDTO), HttpStatus.OK);
    }

    @Operation(summary = "Deletar um arquivo",
            description = "Deleta um arquivo do sistema pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Arquivo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Arquivo não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(
            @Parameter(description = "ID do arquivo a ser deletado", required = true)
            @PathVariable Long id) {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
