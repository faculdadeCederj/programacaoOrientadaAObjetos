package apx1_2020_1;

import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[][] matriz = new char[9][9];
        for (int i = 0; i < 9; i++) {
            String[] linha = scan.nextLine().split(" ");
            for (int j = 0; j < 9; j++) {
                matriz[i][j] = linha[j].charAt(0);
            }
        }
        boolean valido = testaLinhas(matriz);
        if (!valido) {
            System.out.println(valido);
            return;
        }

        valido = testaColunas(matriz);
        if (!valido) {
            System.out.println(valido);
            return;
        }

        valido = testaBlocos(matriz);
        if (!valido) {
            System.out.println(valido);
            return;
        }

        System.out.println("Matriz valida!");
    }

    static private boolean testaLinhas(char[][] matriz) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
               for (int l = 0; l < 8; l++) {
                   if (matriz[i][j] == matriz[i][l]) return false;
               }
            }
        }
        return true;
    }

    static private boolean testaColunas(char[][] matriz) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                for (int l = 0; l < 8; l++) {
                    if (matriz[j][i] == matriz[j][l]) return false;
                }
            }
        }
        return true;
    }

    static private boolean testaBlocos(char[][] matriz) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                for (int l = 0; l < 8; l++) {
                    if (matriz[i][j] == matriz[i][l]) return false;
                }
            }
        }
        return true;
    }
}
