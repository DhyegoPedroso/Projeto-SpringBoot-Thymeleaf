package com.dhyego.curso.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhyego.curso.boot.dao.FuncionarioDao;
import com.dhyego.curso.boot.domain.Funcionario;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioDao dao;

    @Transactional(readOnly = false)
    @Override
    public void salvar(Funcionario funcionario) {
        dao.save(funcionario);
    }

    @Transactional(readOnly = false)
    @Override
    public void editar(Funcionario funcionario) {
        dao.update(funcionario);
    }

    @Transactional(readOnly = false)
    @Override
    public void excluir(Long id) {
        dao.delete(id);
    }

    @Override
    public Funcionario buscarPorId(Long id) {

        return dao.findById(id);
    }

    @Override
    public List<Funcionario> buscarTodos() {

        return dao.findAll();
    }

    @Override
    public List<Funcionario> buscarPorNome(String nome) {
        return dao.findByNome(nome);

    }

    @Override
    public List<Funcionario> buscarPorCargo(Long id) {
        return dao.findByCargoId(id);
    }

    @Override
    public List<Funcionario> buscarPorDatas(LocalDate entrada, LocalDate saida) {

        if (entrada == null && saida == null) {
            return new ArrayList<>();
        } else {
            return dao.findByData(entrada, saida);
        }

    }
}
