package com.cg.minitest1module4.controller;

import com.cg.minitest1module4.exception.CodeExists;
import com.cg.minitest1module4.exception.IdNotFound;
import com.cg.minitest1module4.model.Cart;
import com.cg.minitest1module4.model.Computer;
import com.cg.minitest1module4.model.ComputerForm;
import com.cg.minitest1module4.model.Type;
import com.cg.minitest1module4.service.IComputerService;
import com.cg.minitest1module4.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
@SessionAttributes("cart")
@RequestMapping("/computer")
public class ComputerController {
    @Value("${file-upload}")
    private String upload;
    @Autowired
    private IComputerService computerService;
    @Autowired
    private ITypeService typeService;
    @ModelAttribute("cart")
    public Cart setupCart(){
        return new Cart();
    }
    private static final Logger logger = Logger.getLogger(ComputerController.class.getName());

    @ModelAttribute("types")
    public Iterable<Type> listType() {
        return typeService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView listComputer() {
        ModelAndView modelAndView = new ModelAndView("list");
        Iterable<Computer> computers = computerService.findAll();
        modelAndView.addObject("computer", computers);
        return modelAndView;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("computerForm", new ComputerForm());
        return "create";
    }


    @PostMapping("/create")
    public String checkvalidation(@Validated ComputerForm computerForm, Model model, BindingResult bindingResult) {
        new ComputerForm().validate(computerForm, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "create";
        }
        computerService.existsByCode(computerForm.getCode());
        MultipartFile multipartFile = computerForm.getImg();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(computerForm.getImg().getBytes(), new File(upload, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Computer computer = new Computer(computerForm.getId(), computerForm.getCode(),
                computerForm.getName(), computerForm.getProducer(), computerForm.getType(), fileName);
        computerService.save(computer);
        model.addAttribute("message", "Create new customer successfully");
        return "redirect:/computer";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) throws Exception {
//        Computer computer = computerService.findById(id);
//        Optional<ComputerForm> computerForm = computerService.findById(id);
//        if (computer != null) {
//            ModelAndView modelAndView = new ModelAndView("/update");
//            modelAndView.addObject("computer",  computer);
//            return modelAndView;
//        } else {
//            return new ModelAndView("/error_404");
//        }
        try {
            Optional<Computer> computer = computerService.findById(id);
            if (computer != null) {
                ModelAndView modelAndView = new ModelAndView("update");
                modelAndView.addObject("computer", computer);
                logger.info("Showing update form for computer ID: " + id);
                return modelAndView;
            } else {
                logger.warning("Computer with ID " + id + " not found");
                return new ModelAndView("error_404");
            }
        } catch (RuntimeException runtimeException) {
            return new ModelAndView("error_404");
        }

        //handlexception
//        Computer computer = computerService.findById(id);
//        computerService.exitsById(id);
//        if (computer != null) {
//            ModelAndView modelAndView = new ModelAndView("update");
//            modelAndView.addObject("computer", computer);
//            logger.info("Showing update form for computer ID: " + id);
//            return modelAndView;
//        } else {
//            logger.warning("Computer ID " + id + " not found");
//            return new ModelAndView("error_404b");
//        }

    }

//        @PostMapping("/update/{id}")
//    public String update(Computer computer ,Model model){
//        computerService.save(computer);
//        model.addAttribute("message","Update customer successfully" );
//        return "redirect:/computer";
//    }
    @PostMapping("/update/{id}")
    public String checkvalidation2(@Validated ComputerForm computerForm, Model model, BindingResult bindingResult) {
        new ComputerForm().validate(computerForm, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "update";
        }
        MultipartFile multipartFile = computerForm.getImg();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(computerForm.getImg().getBytes(), new File(upload, fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Computer computer = new Computer(computerForm.getId(), computerForm.getCode(),
                computerForm.getName(), computerForm.getProducer(), computerForm.getType(), fileName);
        computerService.save(computer);
        model.addAttribute("message", "Update customer successfully");
        return "redirect:/computer";


    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        computerService.remove(id);
        redirect.addFlashAttribute("message", "Delete customer successfully");
        return "redirect:/computer";
    }
    @ExceptionHandler(IdNotFound.class)
    public ModelAndView computerIdNotFound() {
        return new ModelAndView("error_404b");
    }
    @ExceptionHandler(CodeExists.class)
    public ModelAndView computerCodeExists() {
        return new ModelAndView("error_404b");
    }

    // shopping
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            @ModelAttribute Cart cart,
                            @RequestParam("action") String action) throws Exception {
        Optional<Computer> productOptional = computerService.findById(id);
        if (!productOptional.isPresent()) {
            return "/error_404";
        }
        if (action.equals("show")) {
            cart.addComputer(productOptional.get());
            return "redirect:/cart";
        }
        cart.addComputer(productOptional.get());
        return "redirect:/computer";
    }

}
