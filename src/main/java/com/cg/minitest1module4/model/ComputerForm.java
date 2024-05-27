package com.cg.minitest1module4.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;



public class ComputerForm implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String producer;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;
    private MultipartFile img;

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public ComputerForm() {
    }

    public ComputerForm(Long id, String code, String name, String producer, Type type, MultipartFile img) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.producer = producer;
        this.type = type;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Computer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ComputerForm computer = (ComputerForm) target;
        String code = computer.getCode();
        String name = computer.getName();
        String producer = computer.getProducer();
        Type type1 = computer.getType();
        ValidationUtils.rejectIfEmpty(errors,"code", "code.emty", "Không được bỏ trống");
        if(!code.matches("(^CG[a-zA-Z0-9]{6}$)")){
            errors.rejectValue("code","code.matches", "Phải có 8 kí tự và bắt đầu bằng CG, không chứa kí tự đặc biệt");
        }
        ValidationUtils.rejectIfEmpty(errors,"name", "name.emty", "Không được bỏ trống");
        ValidationUtils.rejectIfEmpty(errors,"producer", "producer.emty", "Không được bỏ trống");
        ValidationUtils.rejectIfEmpty(errors,"type", "type.emty", "Không được bỏ trống");
    }


}
