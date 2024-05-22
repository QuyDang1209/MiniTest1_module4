package com.cg.minitest1module4.controller;

import com.cg.minitest1module4.model.Computer;
import com.cg.minitest1module4.model.Type;
import com.cg.minitest1module4.service.IComputerService;
import com.cg.minitest1module4.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ComputerController {
    @Autowired
    private IComputerService computerService;
    @Autowired
    private ITypeService typeService;

    @ModelAttribute("types")
    public Iterable<Type> listType() {
        return typeService.findAll();
    }

    @GetMapping("/computer")
    public ModelAndView listComputer() {
        ModelAndView modelAndView = new ModelAndView("list");
        Iterable<Computer> computers = computerService.findAll();
        modelAndView.addObject("computer", computers);
        return modelAndView;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("computer", new Computer());
        return "create";
    }


    @PostMapping("/create")
    public String checkvalidation(@Valid Computer computer,Model model, BindingResult bindingResult){
        new Computer().validate(computer,bindingResult);
        if(bindingResult.hasFieldErrors()){
            return "create";
        }
        computerService.save(computer);
        model.addAttribute("message", "Create new customer successfully");
        return  "redirect:/computer";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Computer> computer = computerService.findById(id);
        if (computer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/update");
            modelAndView.addObject("computer", computer.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }
//    @PostMapping("/update/{id}")
//    public String update(Computer computer ,Model model){
//        computerService.save(computer);
//        model.addAttribute("message","Update customer successfully" );
//        return "redirect:/computer";
//    }
    @PostMapping("/update/{id}")
    public String checkvalidation2(@Valid Computer computer,Model model, BindingResult bindingResult){
        new Computer().validate(computer,bindingResult);
        if(bindingResult.hasFieldErrors()){
            return "update";
        }
        computerService.save(computer);
        model.addAttribute("message","Update customer successfully" );
        return "redirect:/computer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        computerService.remove(id);
        redirect.addFlashAttribute("message", "Delete customer successfully");
        return "redirect:/computer";
    }
}
