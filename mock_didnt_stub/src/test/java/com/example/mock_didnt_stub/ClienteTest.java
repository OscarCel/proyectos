package com.example.mock_didnt_stub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClienteTest {
    @Mock
    private Servicio servicioMock;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        // Inicializar los mocks
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(servicioMock);
    }

    @Test
    public void testProcesarDato() {
        // Configurar el stub
        when(servicioMock.obtenerDato()).thenReturn("Dato de prueba");

        // Ejecutar el método a probar
        String resultado = cliente.procesarDato();

        // Verificar el resultado
        assertEquals("Procesado: Dato de prueba", resultado);
    }
}
