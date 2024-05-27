package com.cg.minitest1module4.service.impl;

import com.cg.minitest1module4.exception.CodeExists;
import com.cg.minitest1module4.exception.IdNotFound;
import com.cg.minitest1module4.model.Computer;
import com.cg.minitest1module4.model.Type;
import com.cg.minitest1module4.repository.IComputerRepository;
import com.cg.minitest1module4.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ComputerService implements IComputerService {
    @Autowired
private IComputerRepository iComputerRepository;

    @Override
    public Iterable<Computer> findAll() {
        return iComputerRepository.findAll();
    }

    @Override
    public void save(Computer computer) {
    iComputerRepository.save(computer);
    }

    @Override
    public Computer findById(Long id){

        return iComputerRepository.findById(id).orElseThrow(() -> new RuntimeException("Computer not found"));
    }

    @Override
    public void remove(Long id) {
    iComputerRepository.deleteById(id);
    }

    @Override
    public Iterable<Computer> findAllByType(Type type) {
        return iComputerRepository.findAllByType(type);
    }
    @Override
    public Boolean exitsById(Long id) {
        Boolean exist = iComputerRepository.existsById(id);
        if (!exist) {
            throw new IdNotFound("Computer id " + id + " not found");
        }
        return iComputerRepository.existsById(id);
    }

    @Override
    public Boolean existsByCode(String code) {
        Boolean exist = iComputerRepository.existsByCode(code);
        if (exist) {
            throw new CodeExists("Computer code " + code + " Exists");
        }
        return iComputerRepository.existsByCode(code);
    }
}
