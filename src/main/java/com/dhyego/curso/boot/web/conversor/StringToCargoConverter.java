package com.dhyego.curso.boot.web.conversor;

import com.dhyego.curso.boot.domain.Cargo;
import com.dhyego.curso.boot.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

    @Autowired
    private CargoService cargoService;

    @Override
    public Cargo convert(String text) {

        if (text.isEmpty()) {
            return null;
        }

        Long id = Long.valueOf(text);
        return cargoService.buscarPorId(id);

    }

}
