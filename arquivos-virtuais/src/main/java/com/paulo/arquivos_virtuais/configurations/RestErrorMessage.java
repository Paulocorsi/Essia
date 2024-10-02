package com.paulo.arquivos_virtuais.configurations;

import org.springframework.http.HttpStatus;

public record RestErrorMessage (
    HttpStatus status,
    String message
){}
