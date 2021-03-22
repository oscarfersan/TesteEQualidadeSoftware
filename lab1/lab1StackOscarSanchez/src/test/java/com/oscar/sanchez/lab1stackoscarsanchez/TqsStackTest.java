/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oscar.sanchez.lab1stackoscarsanchez;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author oscar
 */
public class TqsStackTest {
    
    public TqsStackTest() {
    }
    
    //declarar las stack vacias para los diferentes test
    TqsStack<String> empty = new TqsStack<>();
    TqsStack<String> notEmpty = new TqsStack<>();
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        //preparar para cada uno de los test
        this.notEmpty.push("Aveiro");
        this.notEmpty.push("Oporto");
    }
    
    @AfterEach
    public void tearDown() {
    }
    @DisplayName("Empty Stack")
    @Test
    void isEmpty(){
        assertTrue(empty.isEmpty(),"Stack should be empty");
    }
    @DisplayName("Size is 0")
    @Test
    void sizeIsZero(){
        assertNotEquals(empty.size(),"Stack should be gt 0");
    }
    @DisplayName("Push n elements")
    @Test
    void pushNElements(){
        empty.push("Example");
        //assertFalse(stack.isEmpty(),"Stack should not be empty");
        assertAll("stack",()->assertFalse(empty.isEmpty(),"Stack should not be empty"),
                ()->assertEquals(empty.size(),1));
        empty.pop();
    }
    @Test
    void pushAndPop(){
        TqsStack<String> stack = new TqsStack<>();
        String value = "Aveiro";
        stack.push(value);
        assertEquals(stack.pop(), value, "Value pop not equal!");
    }
    
    @Test
    void pushAndPeek(){
        TqsStack<String> stack = new TqsStack<>();
        String value = "Aveiro";
        stack.push(value);
        assertAll("stack",()->assertEquals(stack.peek(), value, "Value pop not equal!"),
                ()->assertEquals(stack.size(),1));
    }
    @Test
    void popException(){
        TqsStack<String> stack = new TqsStack<>();
        assertThrows(NoSuchElementException.class, ()->stack.pop());
    }
    @Test
    void peekException(){
        TqsStack<String> stack = new TqsStack<>();
        assertThrows(NoSuchElementException.class, ()->stack.peek());
    }
    
}
