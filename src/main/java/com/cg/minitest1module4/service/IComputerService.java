package com.cg.minitest1module4.service;

import com.cg.minitest1module4.model.Computer;
import com.cg.minitest1module4.model.Type;
import com.cg.minitest1module4.service.impl.ComputerService;

public interface IComputerService extends IGenerateService<Computer> {
    Iterable<Computer> findAllByType(Type type);
}
