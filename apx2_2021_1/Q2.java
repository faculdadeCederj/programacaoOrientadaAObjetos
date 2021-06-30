package apx2_2021_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Q2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int arquivo = 0;
        System.out.println("Digite o nome do arquivo, para sair digite FIM");
        String filename = scan.nextLine();
        List<Integer> nrEmTodos = new ArrayList<>();
        List<Integer> nrApareceEmUm = new ArrayList<>();

        while(!filename.equals("FIM")) {
            List<String> linhas = readFile(filename);
            ArrayList<Conta> contas = new ArrayList<>();
            List<Integer> numeros = new ArrayList<>();
            for ( String linha : linhas ) {
                String[] splittedLinha = linha.split(" ");
                for ( String numero : splittedLinha) {
                    numeros.add(Integer.parseInt(numero));
                }
            }
            if (arquivo == 0) {
                nrEmTodos.addAll(numeros);
                nrApareceEmUm.addAll(numeros);
            } else {
                List<Integer> finalNumeros = numeros;
                nrEmTodos = nrEmTodos.stream().filter(finalNumeros::contains).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
                nrApareceEmUm = nrApareceEmUm.stream().filter(a -> !finalNumeros.contains(a)).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());
                List<Integer> finalNrEmTodos = nrEmTodos;
                nrApareceEmUm.addAll(finalNumeros.stream().filter(a -> !finalNrEmTodos.contains(a)).collect(Collectors.toList()));

            }



            while (numeros.size() != 0) {
                Integer numero = numeros.get(0);
                Integer frequencia = Math.toIntExact(numeros.stream().filter(a -> a.equals(numero)).count());
                Conta conta = new Conta(numero, arquivo);
                conta.quantidade_ocorrencias = frequencia;
                contas.add(conta);

                numeros = numeros.stream().filter( a -> !a.equals(numero)).collect(Collectors.toList());
            }
            System.out.println(maiorOcorrencia(contas));
            System.out.println(naoRepetem(contas));

            filename = scan.nextLine();
            arquivo++;
        }

        System.out.println(nrEmTodos);
        System.out.println(nrApareceEmUm);

    }


    static List<String> readFile(String filename) {
        List<String> fileContent = new ArrayList();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while ( scanner.hasNextLine() ) {
                fileContent.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nao foi possivel ler o arquivo");
            e.printStackTrace();
        }

        return fileContent;
    }

    static String maiorOcorrencia(List<Conta> contas) {
        String moda = "";
        int qtd_ocorrencias = 0;
        for (Conta conta : contas) {
            if (conta.quantidade_ocorrencias > qtd_ocorrencias) {
                qtd_ocorrencias = conta.quantidade_ocorrencias;
                moda = conta.num + " ";
            }
             else if (conta.quantidade_ocorrencias == qtd_ocorrencias) {
                 moda += conta.num + " ";
            }
        }

        return moda;
    }

    static String naoRepetem(List<Conta> contas) {
        String nao_repetem = "";
        for (Conta conta: contas) {
            if (conta.quantidade_ocorrencias == 1) {
                nao_repetem +=  conta.num + " ";
            }
        }
        return nao_repetem;
    }
}

class Conta{
    int num;
    int quantidade_ocorrencias;
    List<Integer> linhas = new ArrayList<Integer>();

    Conta(int num, int linha){
        this.num = num;
        quantidade_ocorrencias = 1;
        linhas.add(new Integer(linha));
    }
    public String toString(){
        return num + " ";
    }
}
