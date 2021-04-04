package com.dhyego.curso.boot.service;

import java.util.List;

import com.dhyego.curso.boot.domain.Departamento;
import com.dhyego.curso.boot.util.PaginacaoUtil;

public interface DepartamentoService {

    void salvar(Departamento departamento);

    void editar(Departamento departamento);

    void excluir(Long id);

    Departamento buscarPorId(Long id);

    List<Departamento> buscarTodos();

    boolean departamentoTemCargos(Long id);
    
    PaginacaoUtil<Departamento> buscarPorPagina(int pagina, String direcao);
}
