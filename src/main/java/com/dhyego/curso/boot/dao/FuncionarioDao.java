package com.dhyego.curso.boot.dao;

import java.util.List;

import com.dhyego.curso.boot.domain.Funcionario;
import com.dhyego.curso.boot.util.PaginacaoUtil;
import java.time.LocalDate;

public interface FuncionarioDao {

    void save(Funcionario funcionario);

    void update(Funcionario funcionario);

    void delete(Long id);

    Funcionario findById(Long id);

    List<Funcionario> findAll();

    List<Funcionario> findByNome(String nome);

    List<Funcionario> findByCargoId(Long id);

    List<Funcionario> findByData(LocalDate entrada, LocalDate saida);
  
}
