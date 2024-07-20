package com.mycompany.app;

public class Calculadora extends Object{

    private double resultado;

    public Calculadora(double resultado) {
    	super();
    	this.resultado = resultado;
    }
    
    public double sumar(double sumando) {
        this.resultado = this.resultado + sumando;
        return this.resultado;
    }

    public double restar(double restando) {
        this.resultado = this.resultado - restando;
        return this.resultado;
    }

    public double multiplicar(double multiplicando) {
        this.resultado = this.resultado * multiplicando;
        return this.resultado;
    }

    public double dividir(double dividiendo) {
        this.resultado = this.resultado / dividiendo;
        return this.resultado;
    }
}
