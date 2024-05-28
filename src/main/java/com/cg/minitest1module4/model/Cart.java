package com.cg.minitest1module4.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Computer,Integer> computers = new HashMap<>();

    public Cart() {
    }

    public Cart(Map<Computer, Integer> computers) {
        this.computers = computers;
    }

    public Map<Computer, Integer> getComputers() {
        return computers;
    }

    public void setComputers(Map<Computer, Integer> computers) {
        this.computers = computers;
    }
    private boolean checkItemsinCart(Computer computer){
        for (Map.Entry<Computer,Integer> entry : computers.entrySet()){
            if( entry.getKey().getId().equals(computer.getId())){
                return true;
            }
        }
        return false;
    }
    private Map.Entry<Computer, Integer> selectItemInCart(Computer computer){
        for (Map.Entry<Computer,Integer> entry : computers.entrySet()){
            if( entry.getKey().getId().equals(computer.getId())){
                return entry;
            }
        }
        return null;
    }
    public void addComputer(Computer computer){
        if(!checkItemsinCart(computer)){
            computers.put(computer,1);
        }else {
            Map.Entry<Computer, Integer> itemEntry = selectItemInCart(computer);
            Integer newQuantity = itemEntry.getValue() + 1;
            computers.replace(itemEntry.getKey(),newQuantity);
        }
    }
    public Integer countComputerQuantity(){
        Integer computerquantity = 0;
        for (Map.Entry<Computer, Integer> entry : computers.entrySet()) {
            computerquantity += entry.getValue();
        }
        return computerquantity;
    }
    public Integer countItemQuantity(){
        return computers.size();
    }
    public Float countTotalPayment(){
        float payment = 0;
        for (Map.Entry<Computer, Integer> entry : computers.entrySet()) {
//            payment += entry.getKey().getPrice() * (float) entry.getValue();
            payment += 1 * (float) entry.getValue();
        }
        return payment;
    }
}
