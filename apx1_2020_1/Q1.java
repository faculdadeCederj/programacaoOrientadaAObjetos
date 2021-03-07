package apx1_2020_1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Q1 {
    public static void main(String[] args) {
        Nota n1 = new Nota("CPF: 999999999-99", new String[] {"documento",
                "identificação"}); // O segundo argumento é uma forma compacta de criar um vetor de strings
        Nota n2 = new Nota("Matricula UFF: 99999999-9", new String[] {"documento", "universidade"});
        Bloco anotacoes = new Bloco();
        anotacoes.adicionarNota(n1);
        anotacoes.adicionarNota(n2);
        System.out.println(anotacoes); // Imprime todas as notas
        Bloco faculdade = anotacoes.buscaNotas("UFF");
        System.out.println(faculdade); // Imprime apenas notas com a palavra UFF incluída
    }
}

class Nota {
    String texto;
    String[] labels;
    LocalDateTime criacao;

    Nota(String texto, String[] labels) {
        this.criacao = LocalDateTime.now();
        this.texto = texto;
        this.labels = labels;
    }
}

class Bloco {
    List<Nota> notas;

    Bloco() {
        this.notas = new ArrayList<>();
    }

    public void adicionarNota(Nota nota) {
        this.notas.add(nota);
    }

    public Bloco buscaNotas(String busca) {
        Bloco blocoRetornado = new Bloco();
        for (Nota nota : this.notas) {
            if (nota.texto.contains(busca)) blocoRetornado.adicionarNota(nota);
        }

        return blocoRetornado;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (Nota nota : this.notas) {
            returnString.append("Nota: ").append(nota.texto).append("\n");
        }
        return returnString.toString();
    }
}


