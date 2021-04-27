package apx1_2021_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Q1 {

    private Q1() {

    }

    public static void main(String[] args) {

        List<char[]> matriz = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite a string a ser verificada na matriz:");
        String palavra = scan.nextLine().strip().toUpperCase();
        System.out.println("Digite as linhas da matriz, com todos os elementos unidos, separadas por <ENTER> Ex(acprc):");
        String line = scan.nextLine();
        while (!line.equals("FIM")) {
            char[] lineArray = line.strip().toUpperCase().toCharArray();
            matriz.add(lineArray.clone());
            line = scan.nextLine();
        }

        boolean presente = verificaString(matriz, palavra);
        System.out.println("Resultado da busca:" + presente);

    }

    static boolean verificaString(List<char[]> matriz, String palavra) {
        char[] listaPalavra = palavra.toCharArray();
        int contadorLetra = 0;
        boolean resultado = false;

        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < matriz.get(i).length; j++) {
                if (matriz.get(i)[j] == listaPalavra[contadorLetra]) {
                    contadorLetra ++;
                    return verificaArredores(i, j, listaPalavra, matriz, contadorLetra);
                }
            }
        }

        return resultado;
    }

    static boolean verificaArredores(int i, int j, char[] listaPalavra, List<char[]> matriz, int contadorLetra) {
        boolean returnValue = false;

        if (contadorLetra == listaPalavra.length - 1) {
            return true;
        }

        for (int l = i - 1; l <= i+1; l++) {
            for (int k = j - 1; k <= j+1; k++) {
                if (l < 0) { continue;}
                if (k < 0) { continue;}
                if (l > matriz.size() - 1) { continue;}
                if (k > matriz.get(i).length -1) { continue;}

                if (listaPalavra[contadorLetra] == matriz.get(l)[k]) {
                    contadorLetra++;
                    matriz.get(l)[k] = '#';
                    returnValue = verificaArredores(l, k, listaPalavra, matriz, contadorLetra);

                }
             }

        }
        return returnValue;
    }


}
