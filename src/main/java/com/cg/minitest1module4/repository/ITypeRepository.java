package com.cg.minitest1module4.repository;

import com.cg.minitest1module4.model.Type;
import org.springframework.data.repository.CrudRepository;

public interface ITypeRepository extends CrudRepository<Type, Long> {
}
