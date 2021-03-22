/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oscar.sanchez.lab1stackoscarsanchez;

import java.util.LinkedList;

/**
 *
 * @author oscar
 */
public class TqsStack<T> {
    private LinkedList<T> list = new LinkedList<T>();
    public TqsStack() {
    
    }
    
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public void push(T element){
        list.addFirst(element);
    }
    public T pop(){
        return list.removeFirst();
    }
    public T peek(){
        return list.getFirst();
    }
    public int size(){
        return list.size();
    }
}
