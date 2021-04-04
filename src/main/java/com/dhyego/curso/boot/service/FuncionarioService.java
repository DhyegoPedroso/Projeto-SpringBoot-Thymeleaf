package com.dhyego.curso.boot.service;

import java.util.List;

import com.dhyego.curso.boot.domain.Funcionario;
import com.dhyego.curso.boot.util.PaginacaoUtil;
import java.time.LocalDate;

public interface FuncionarioService {

    void salvar(Funcionario funcionario);

    void editar(Funcionario funcionario);

    void excluir(Long id);

    Funcionario buscarPorId(Long id);

    List<Funcionario> buscarTodos();

    List<Funcionario> buscarPorNome(String nome);

    List<Funcionario> buscarPorCargo(Long id);

    List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida);
}
