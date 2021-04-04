package com.dhyego.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.dhyego.curso.boot.domain.Departamento;
import com.dhyego.curso.boot.util.PaginacaoUtil;
import java.util.List;

@Repository
public class DepartamentoDaoImpl extends AbstractDao<Departamento, Long> implements DepartamentoDao {

    public PaginacaoUtil<Departamento> buscaPaginada(int pagina, String direcao) {
        int tamanho = 5;
        int inicio = (pagina - 1) * tamanho;

        List<Departamento> departamentos = getEntityManager()
                .createQuery("SELECT d From Departamento d ORDER BY d.nome " + direcao, Departamento.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

        return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, departamentos);
    }

    public long count() {
        return getEntityManager()
                .createQuery("SELECT COUNT(*) FROM Departamento", Long.class)
                .getSingleResult();
    }

}
