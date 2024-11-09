package com.mercadolibre.service;

import org.springframework.stereotype.Service;

@Service
public class DNAService {
    
    public boolean isMutant(String [] dna){
        int length = dna.length;
        char[][] matriz = new char[length][length];

        for (int i = 0; i < length; i++) {
            matriz[i] = dna[i].toCharArray();
        }

        int contador = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (j <= length - 4 && checkHorizontal(matriz, i, j)) contador++;
                if (i <= length - 4 && checkVertical(matriz, i, j)) contador++;
                if (i <= length - 4 && j <= length - 4 && checkDiagonal(matriz, i, j)) contador++;
                if (i >= 3 && j <= length - 4 && checkAntiDiagonal(matriz, i, j)) contador++;

                if (contador > 1) return true;
            }
        }

        return false;
    }

    private static boolean checkHorizontal(char[][] matriz, int row, int col) {
        return matriz[row][col] == matriz[row][col + 1] &&
               matriz[row][col] == matriz[row][col + 2] &&
               matriz[row][col] == matriz[row][col + 3];
    }

    private static boolean checkVertical(char[][] matriz, int row, int col) {
        return matriz[row][col] == matriz[row + 1][col] &&
               matriz[row][col] == matriz[row + 2][col] &&
               matriz[row][col] == matriz[row + 3][col];
    }

    private static boolean checkDiagonal(char[][] matriz, int row, int col) {
        return matriz[row][col] == matriz[row + 1][col + 1] &&
               matriz[row][col] == matriz[row + 2][col + 2] &&
               matriz[row][col] == matriz[row + 3][col + 3];
    }


    private static boolean checkAntiDiagonal(char[][] matriz, int row, int col) {
        if (row + 3 >= matriz.length || col - 3 < 0) return false;
        return matriz[row][col] == matriz[row + 1][col - 1] &&
               matriz[row][col] == matriz[row + 2][col - 2] &&
               matriz[row][col] == matriz[row + 3][col - 3];
    }
}
