package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculadoraOtroTest {

	private Calculadora cut;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSumar() {
		cut = new Calculadora(2);
        assertEquals(5.0, cut.sumar(3));
	}

	@Test
	void testRestar() {
		cut = new Calculadora(5);
		assertEquals(4.0, cut.restar(1));
	}

	@Test
	void testMultiplicar() {
		cut = new Calculadora(4);
		assertEquals(8.0, cut.multiplicar(2));
	}

	@Test
	void testDividir() {
		cut = new Calculadora(8);
		assertEquals(2.0, cut.dividir(4));;
	}

}
