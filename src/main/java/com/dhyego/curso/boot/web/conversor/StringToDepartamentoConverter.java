package com.dhyego.curso.boot.web.conversor;

import com.dhyego.curso.boot.domain.Departamento;
import com.dhyego.curso.boot.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

    @Autowired
    private DepartamentoService departamentoService;

    @Override
    public Departamento convert(String text) {

        if (text.isEmpty()) {
            return null;
        }

        Long id = Long.valueOf(text);
        return departamentoService.buscarPorId(id);

    }

}
