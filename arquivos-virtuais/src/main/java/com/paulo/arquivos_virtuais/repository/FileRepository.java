package com.paulo.arquivos_virtuais.repository;

import com.paulo.arquivos_virtuais.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}