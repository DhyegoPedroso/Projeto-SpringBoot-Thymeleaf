package com.dhyego.curso.boot.dao;

import java.util.List;

import com.dhyego.curso.boot.domain.Departamento;
import com.dhyego.curso.boot.util.PaginacaoUtil;

public interface DepartamentoDao {

    void save(Departamento departamento);

    void update(Departamento departamento);

    void delete(Long id);

    Departamento findById(Long id);

    List<Departamento> findAll();

    PaginacaoUtil<Departamento> buscaPaginada(int pagina, String direcao);
}
