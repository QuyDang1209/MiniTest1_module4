package com.cg.minitest1module4.exception;

public class CodeExists extends RuntimeException {
    public CodeExists(String message){
        super(message);
    }
}
