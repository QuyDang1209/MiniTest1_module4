package com.cg.minitest1module4.service;

import com.cg.minitest1module4.model.Type;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    void save(T t);

     Optional<T> findById(Long id) throws Exception;

    void remove(Long id);
}
