package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    private Calculadora cut;

    @Test
    public void testSumar() {
    	cut = new Calculadora(0);
        double res = cut.sumar(5);
        assertEquals(5.0, res);
    }
    
    @Test
    public void testMultiplicar() {
    	cut = new Calculadora(5);
        double res = cut.multiplicar(4);
        assertEquals(20.0, res);
    }
    
    @Test
    public void testDividir() {
    	cut = new Calculadora(20);
        double res = cut.dividir(4);
        assertEquals(5.0, res);
    }
    
    @Test
    public void testMultiplicar2() {
    	cut = new Calculadora(5);
        double res = cut.multiplicar(2);
        assertEquals(10.0, res);
    }

    @Test
    public void testRestar() {
    	cut = new Calculadora(10);
        double res = cut.restar(2);
        assertEquals(8.0, res);
    }
    
    @Test
    public void testSumar2() {
    	cut = new Calculadora(8);
        double res = cut.sumar(1);
        assertEquals(9.0, res);
    }
}
