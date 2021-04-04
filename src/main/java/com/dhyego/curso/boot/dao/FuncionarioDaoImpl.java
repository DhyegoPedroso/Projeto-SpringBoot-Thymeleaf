package com.dhyego.curso.boot.dao;

import com.dhyego.curso.boot.domain.Cargo;
import org.springframework.stereotype.Repository;

import com.dhyego.curso.boot.domain.Funcionario;
import com.dhyego.curso.boot.util.PaginacaoUtil;
import java.time.LocalDate;
import java.util.List;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao {

    @Override
    public List<Funcionario> findByNome(String nome) {

        return createQuery("SELECT f FROM Funcionario f WHERE f.nome LIKE CONCAT('%', ?1, '%') ", nome);
    }

    @Override
    public List<Funcionario> findByCargoId(Long id) {
        return createQuery("SELECT f FROM Funcionario f WHERE f.cargo.id = ?1", id);
    }

    @Override
    public List<Funcionario> findByData(LocalDate entrada, LocalDate saida) {

        String jpql = "select f from Funcionario f ";

        if (entrada == null) {
            jpql += "where f.dataSaida <= ?1 order by f.dataEntrada asc";
            return createQuery(jpql, saida);

        } else if (saida == null) {

            jpql += "where f.dataEntrada >= ?1 order by f.dataEntrada asc";
            return createQuery(jpql, entrada);

        } else {
            jpql += "where f.dataEntrada >= ?1 and f.dataSaida <= ?2 order by f.dataEntrada asc ";
            return createQuery(jpql, entrada, saida);

        }

    }    

}
