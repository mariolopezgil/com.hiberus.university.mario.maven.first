package com.hiberus.university.mario.maven.first.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumerosAleatorios {
    public List numerosAleatorios(int cantidad, int tamanio) {
        List<Integer> listaNumeros = new ArrayList<>();
        Random random = new Random();

        while (listaNumeros.size() < cantidad) {
            int num = random.nextInt(tamanio)+1 ;
            if (!listaNumeros.contains(num)) {
                listaNumeros.add(num);
            }
        }
        return listaNumeros;

    }
}
