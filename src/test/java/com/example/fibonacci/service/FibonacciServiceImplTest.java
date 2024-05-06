package com.example.fibonacci.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciServiceImplTest {
    final FibonacciServiceImpl fibonacciService = new FibonacciServiceImpl();

    @Test
    public void testFibonacciSerieDescendent() {
        int[] expected = {21, 13, 8, 5, 3}; // Resultado esperado para los primeros 5 términos de Fibonacci
        int[] actual = fibonacciService.fibonacciSerie(5, 1, 2); // Calcula los primeros 5 términos de Fibonacci sin contar los dos términos semilla
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testFibonacciSerieWithInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> {
            fibonacciService.fibonacciSerie(5, 0, 0); // Intenta calcular la serie de Fibonacci con términos semilla no válidos
        });
    }

}