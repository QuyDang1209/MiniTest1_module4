package com.cg.minitest1module4.formatter;

import com.cg.minitest1module4.model.Type;
import com.cg.minitest1module4.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class TypeFormatter implements Formatter<Type> {
    private ITypeService typeService;

    @Autowired
    public TypeFormatter(ITypeService typeService) {
        this.typeService = typeService;
    }

    @Override
    public Type parse(String text, Locale locale) {
        Type optionalType = null;
        try {
            optionalType = typeService.findById(Long.parseLong(text));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return optionalType;
    }

    @Override
    public String print(Type object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
