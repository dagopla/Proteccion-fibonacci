package com.example.fibonacci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FibonacciServiceImpl implements FibonacciService {



    @Override
    public int[] fibonacci(int minutos, int segundos) {

        String numeroComoString = "";
        if (minutos < 10) {
             numeroComoString = String.valueOf(minutos)+"0";
        }else{
             numeroComoString = String.valueOf(minutos);
        }

        //Separa los minutos en dos terminos
        int[] terminos = new int[numeroComoString.length()];
        for (int i = 0; i < numeroComoString.length(); i++) {
            terminos[i] = Character.getNumericValue(numeroComoString.charAt(i));
        }
        return fibonacciSerie(segundos, terminos[0], terminos[1]);
    }

    public int[] fibonacciSerie(int numeroTerminos, int terminoX, int terminoY) {
        if(terminoX == 0 && terminoY == 0){
            throw new IllegalArgumentException("Los terminos semilla no pueden ser 0");
        }
        //Calcula la serie de fibonacci sin contar con los dos primeros terminos semilla
        int[] fibonacci = new int[numeroTerminos];
        fibonacci[0] = terminoY + terminoX;
        fibonacci[1] = fibonacci[0] + terminoY;
        for (int i = 2; i < numeroTerminos; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];

        }
        return reverseArray(fibonacci);
    }

    private int[] reverseArray(int[] fibonacci) {
        int inicio = 0;
        int fin = fibonacci.length - 1;
        while (inicio < fin) {
            // Intercambiar los elementos en inicio y fin
            int temp = fibonacci[inicio];
            fibonacci[inicio] = fibonacci[fin];
            fibonacci[fin] = temp;
            // Mover los índices hacia adentro
            inicio++;
            fin--;
        }
        return fibonacci;
    }

}
