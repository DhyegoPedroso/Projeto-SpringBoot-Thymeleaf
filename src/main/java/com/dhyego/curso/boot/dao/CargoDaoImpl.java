package com.dhyego.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.dhyego.curso.boot.domain.Cargo;
import com.dhyego.curso.boot.util.PaginacaoUtil;
import java.util.List;

@Repository
public class CargoDaoImpl extends AbstractDao<Cargo, Long> implements CargoDao {

    public PaginacaoUtil<Cargo> buscaPaginada(int pagina, String direcao) {
        int tamanho = 5;
        int inicio = (pagina - 1) * tamanho; // 0*5=0 1*5=5 2*5=10
        List<Cargo> cargos = getEntityManager()
                .createQuery("SELECT c FROM Cargo c ORDER BY c.nome " + direcao, Cargo.class)
                .setFirstResult(inicio)
                .setMaxResults(tamanho)
                .getResultList();

        long totalRegistros = count();
        long totalDePaginas = (totalRegistros + (tamanho - 1)) / tamanho;

        return new PaginacaoUtil<>(tamanho, pagina, totalDePaginas, direcao, cargos);
    }

    public long count() {
        return getEntityManager()
                .createQuery("SELECT COUNT(*) FROM Cargo", Long.class)
                .getSingleResult();
    }

}
