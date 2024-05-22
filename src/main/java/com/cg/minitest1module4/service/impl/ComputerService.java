package com.cg.minitest1module4.service.impl;

import com.cg.minitest1module4.model.Computer;
import com.cg.minitest1module4.model.Type;
import com.cg.minitest1module4.repository.IComputerRepository;
import com.cg.minitest1module4.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Optional<Computer> findById(Long id) {
        return iComputerRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
    iComputerRepository.deleteById(id);
    }

    @Override
    public Iterable<Computer> findAllByType(Type type) {
        return iComputerRepository.findAllByType(type);
    }
}
