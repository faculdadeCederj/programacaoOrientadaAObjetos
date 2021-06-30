package apx2_2021_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1 {
        public static void main(String[] args) {
                Solicitacao sangue = new Exame("Exame de Sangue", 20);
                Solicitacao biopsia = new Cirurgia("Biopsia", 30);
                Tratamento analiseAlergia = new Tratamento();
                analiseAlergia.adicionaSolicitacao(Arrays.asList(sangue, biopsia));
                System.out.println(analiseAlergia);
                Solicitacao anestesia = new Medicamento("Anestesia", 1000);
                Solicitacao septo = new Cirurgia("Correção de Septo", 120);
                Tratamento correcaoSepto = new Tratamento();
                correcaoSepto.adicionaSolicitacao(anestesia);
                correcaoSepto.adicionaSolicitacao(septo);
                System.out.println(correcaoSepto);
                Solicitacao cisto = new Cirurgia("Extração de Cisto", 60);
                Tratamento extracaoCisto = new Tratamento();
                extracaoCisto.adicionaSolicitacao(Arrays.asList(analiseAlergia, anestesia, cisto));
                System.out.println(extracaoCisto);
        }
}

interface Solicitacao {
        Double custo();
}

class Exame implements Solicitacao {
        String descricao;
        Integer tempoMedio;

        public Exame(String _descricao, Integer _tempoMedio) {
                descricao = _descricao;
                tempoMedio = _tempoMedio;
        }

        @Override
        public Double custo() {
                return tempoMedio * 20.0;
        }

        @Override
        public String toString() {
                return descricao + ", " + custo();
        }
}

class Cirurgia implements Solicitacao {
        String descricao;
        Integer tempoMedio;

        public Cirurgia(String _descricao, Integer _tempoMedio) {
                descricao = _descricao;
                tempoMedio = _tempoMedio;
        }


        @Override
        public Double custo() {
                return tempoMedio * 100.0;
        }

        @Override
        public String toString() {
                return descricao + ", " + custo();
        }
}

class Medicamento implements Solicitacao {
        String principioAtivo;
        Integer custo;

        public Medicamento(String _principioAtivo, Integer _custo) {
                principioAtivo = _principioAtivo;
                custo = _custo;
        }

        @Override
        public Double custo() {
                return custo.doubleValue();
        }

        @Override
        public String toString() {
                return principioAtivo + ", " + custo();
        }
}

class Tratamento implements Solicitacao {
        static int counter = 0;
        int posicao;
        List<Solicitacao> solicitacoes = new ArrayList();

        public Tratamento() {
                posicao = counter;
                counter ++;
        }

        public void adicionaSolicitacao(Solicitacao _solicitacao) {
                solicitacoes.add(_solicitacao);
        }

        public void adicionaSolicitacao(List _solicitacao) {
                solicitacoes.addAll(_solicitacao);
        }
        @Override
        public String toString() {
                String returnValue = "Tratamento " + posicao + "{\n";
                for (int i = 0; i < solicitacoes.size(); i++) {
                        returnValue += solicitacoes.get(i).toString() + "\n";
                }
                returnValue += "}";
                return returnValue;
        }

        @Override
        public Double custo() {
                return null;
        }
}

