package com.dhyego.curso.boot.web.controller;

import com.dhyego.curso.boot.domain.Cargo;
import com.dhyego.curso.boot.domain.Departamento;
import com.dhyego.curso.boot.service.CargoService;
import com.dhyego.curso.boot.service.DepartamentoService;
import com.dhyego.curso.boot.util.PaginacaoUtil;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private DepartamentoService departamentoService;

    private Cargo cargoPreEditar;

    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo) {
        return "cargo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("dir") Optional<String> dir) {

        int paginaAtual = page.orElse(1);  
        String ordem = dir.orElse("asc");
              
        PaginacaoUtil<Cargo> pageCargo = cargoService.buscarPorPagina(paginaAtual, ordem);

        model.addAttribute("pageCargo", pageCargo);
        return "cargo/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "cargo/cadastro";
        }

        cargoService.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo " + cargo.getNome() + " inserido com sucesso.");
        return "redirect:/cargos/cadastrar";
    }

    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos() {
        return departamentoService.buscarTodos();
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("cargo", cargoService.buscarPorId(id));
        cargoPreEditar = cargoService.buscarPorId(id);
        return "cargo/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "cargo/cadastro";
        }

        cargoService.editar(cargo);
        attr.addFlashAttribute("success", "Cargo " + cargoPreEditar.getNome()
                + " renomeado para " + cargo.getNome() + "  com sucesso.");
        return "redirect:/cargos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {

        Cargo cargo = cargoService.buscarPorId(id);

        if (cargoService.cargoTemFuncionarios(id)) {
            attr.addFlashAttribute("fail", "Cargo " + cargo.getNome() + " não removido. Possui funcionário(s) vinculado(s).");
        } else {
            cargoService.excluir(id);
            attr.addFlashAttribute("success", "Cargo " + cargo.getNome() + " excluído com sucesso.");
        }

        return "redirect:/cargos/listar";
    }
}
