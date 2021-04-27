package apx1_2021_1;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Q2 {
    public static void main(String[] args) {
        Imovel i1 = new Imovel(100, "1/1/1980", "centro");
        System.out.println("IPTU do imóvel " + i1.getCodigo() + ": R$ " +
                i1.getValorIPTU());
        Imovel i2 = new Apto(100, "1/1/1980", "periferia", 2, "fundos");
        Imovel i3 = new Loja(100, "1/1/1980", "centro", false);
        Imoveis propriedades = new Imoveis(1000);
        propriedades.adicionaImovel(i1);
        propriedades.adicionaImovel(i2);
        propriedades.adicionaImovel(i3);
        System.out.println("Total de IPTU a ser arrecadado: R$ " +
                propriedades.calculaIPTUTotal());
    }

}

class Imovel {
    int metragem;
    LocalDate dataConstrucao;
    String localizacao;
    static int contadorId = 1;
    int idImovel;
    Double aliquota = 0.1;

    Imovel(int _metragem, String _dataConstrucao, String _localizacao) {
        this.metragem = _metragem;
        String[] date = _dataConstrucao.split("/");
        this.dataConstrucao = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]),Integer.parseInt(date[0]));
        this.localizacao = _localizacao;
        this.idImovel = contadorId;
        contadorId ++;
    }

    int getCodigo() {
        return idImovel;
    }

    Double getValorIPTU() {
        Double valorVenal = 0.0;
        if (this.localizacao.equals("centro")) {
            valorVenal = 1000.0 * this.metragem;
        } else {
            valorVenal = 500.0 * this.metragem;
        }

        Period diferenca_data = Period.between(this.dataConstrucao, LocalDate.now());
        int diferenca_anos = diferenca_data.getYears();

        if (diferenca_anos < 10) {
            return valorVenal * this.aliquota;
        } else if (diferenca_anos < 30) {
            return valorVenal * 0.8 * this.aliquota;
        } else {
            return valorVenal * 0.6 * this.aliquota;
        }

    }


}

class Imoveis {
    List<Imovel> imoveis;

    Imoveis(int _qtdImoveis) {
        this.imoveis = new ArrayList(_qtdImoveis);
    }

    void adicionaImovel(Imovel imovel) {
        this.imoveis.add(imovel);
    }

    Double calculaIPTUTotal() {
        Double total = 0.0;

        for (Imovel imovel : this.imoveis) {
            total += imovel.getValorIPTU();
        }

        return total;
    }
}

class Apto extends Imovel {
    int qtdQuartos;
    String frenteOuFundos;
    Double aliquota = 0.1;

    Apto(int _metragem, String _dataConstrucao, String _localizacao, int _qtdQuartos, String _frenteOuFundos) {
        super(_metragem, _dataConstrucao, _localizacao);
        this.qtdQuartos = _qtdQuartos;
        this.frenteOuFundos = _frenteOuFundos;
        if (_qtdQuartos < 3 && _frenteOuFundos.equals("fundos")) {
            this.aliquota = 0.05;
        }
     }

    Double getValorIPTU() {
        Double valorVenal = 0.0;
        if (this.localizacao.equals("centro")) {
            valorVenal = 1000.0 * this.metragem;
        } else {
            valorVenal = 500.0 * this.metragem;
        }

        Period diferenca_data = Period.between(this.dataConstrucao, LocalDate.now());
        int diferenca_anos = diferenca_data.getYears();

        if (diferenca_anos < 10) {
            return valorVenal * this.aliquota;
        } else if (diferenca_anos < 30) {
            return valorVenal * 0.8 * this.aliquota;
        } else {
            return valorVenal * 0.6 * this.aliquota;
        }

    }
}

class Loja extends Imovel {
    boolean shopping;
    Double aliquota = 0.1;


    Loja(int _metragem, String _dataConstrucao, String _localizacao, boolean _shopping) {
        super(_metragem, _dataConstrucao, _localizacao);
        this.shopping = _shopping;
        if (!_shopping) {
            this.aliquota = 0.08;
        }
    }

    Double getValorIPTU() {
        Double valorVenal = 0.0;
        if (this.localizacao.equals("centro")) {
            valorVenal = 1000.0 * this.metragem;
        } else {
            valorVenal = 500.0 * this.metragem;
        }

        Period diferenca_data = Period.between(this.dataConstrucao, LocalDate.now());
        int diferenca_anos = diferenca_data.getYears();

        if (diferenca_anos < 10) {
            return valorVenal * this.aliquota;
        } else if (diferenca_anos < 30) {
            return valorVenal * 0.8 * this.aliquota;
        } else {
            return valorVenal * 0.6 * this.aliquota;
        }

    }
}
