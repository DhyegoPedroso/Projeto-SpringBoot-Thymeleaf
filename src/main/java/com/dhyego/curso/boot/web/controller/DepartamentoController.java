package com.dhyego.curso.boot.web.controller;

import com.dhyego.curso.boot.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dhyego.curso.boot.domain.Departamento;
import com.dhyego.curso.boot.service.DepartamentoService;
import com.dhyego.curso.boot.util.PaginacaoUtil;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    private Departamento depPreEditar;

    @GetMapping("/cadastrar")
    public String cadastrar(Departamento departamento) {
        return "departamento/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("dir") Optional<String> dir) {

        int paginaAtual = page.orElse(1);
        String ordem = dir.orElse("asc");

        PaginacaoUtil<Departamento> pageDepartamento = service.buscarPorPagina(paginaAtual, ordem);

        model.addAttribute("pageDepartamento", pageDepartamento);
        return "departamento/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "departamento/cadastro";
        }

        service.salvar(departamento);
        attr.addFlashAttribute("success", "Departamento " + departamento.getNome() + " inserido com sucesso.");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("departamento", service.buscarPorId(id));
        depPreEditar = service.buscarPorId(id);
        return "departamento/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "departamento/cadastro";
        }

        service.editar(departamento);
        attr.addFlashAttribute("success", "Departamento " + depPreEditar.getNome() + " renomeado para " + departamento.getNome() + "  com sucesso.");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {

        Departamento departamento = service.buscarPorId(id);

        if (service.departamentoTemCargos(id)) {
            attr.addFlashAttribute("fail", "Departamento " + departamento.getNome() + " não removido. Possui cargo(s) vinculado(s).");
        } else {
            service.excluir(id);
            attr.addFlashAttribute("success", "Departamento " + departamento.getNome() + " excluído com sucesso.");
        }

        return "redirect:/departamentos/listar";
    }

}
