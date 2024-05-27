package com.cg.minitest1module4.repository;

import com.cg.minitest1module4.model.Computer;
import com.cg.minitest1module4.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface IComputerRepository extends CrudRepository<Computer, Long> {
    Iterable<Computer> findAllByType(Type type);

    Boolean existsByCode(String code);
}
