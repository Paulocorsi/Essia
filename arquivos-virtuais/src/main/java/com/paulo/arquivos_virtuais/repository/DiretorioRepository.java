package com.paulo.arquivos_virtuais.repository;

import com.paulo.arquivos_virtuais.models.Diretorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiretorioRepository extends JpaRepository<Diretorio, Long> {
    List<Diretorio> findByParentDiretorio(Diretorio parent);

    List<Diretorio> findByParentDiretorioIsNull();
}