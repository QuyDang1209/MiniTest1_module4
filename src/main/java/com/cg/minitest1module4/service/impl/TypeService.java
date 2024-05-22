package com.cg.minitest1module4.service.impl;

import com.cg.minitest1module4.model.Type;
import com.cg.minitest1module4.repository.ITypeRepository;
import com.cg.minitest1module4.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class TypeService implements ITypeService {
    @Autowired
    private ITypeRepository iTypeRepository;
    @Override
    public Iterable<Type> findAll() {
        return iTypeRepository.findAll();
    }

    @Override
    public void save(Type type) {
    iTypeRepository.save(type);
    }

    @Override
    public Optional<Type> findById(Long id) {
        return iTypeRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
    iTypeRepository.deleteById(id);
    }
}
