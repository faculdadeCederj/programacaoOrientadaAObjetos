package apx1_2020_2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Q2 {
    public static void main(String[] args) {
        Navegador raposa = new Navegador("Firefox",
                "http://pesquisadores.uff.br/researcher/carlos-bazilio-martins");
        raposa.adicionaAba(new AbaAnonima("http://www.ic.uff.br/~bazilio/"));
        raposa.adicionaAba(new Aba("https://www.escavador.com/sobre/7717446/carlosbazilio-martins"));
        System.out.println(raposa.exibePaginas());
        raposa.proximaAba();
        raposa.acessaPagina("https://github.com/carlosbazilio");
        System.out.println(raposa.exibePaginas());
        raposa.proximaAba();
        raposa.acessaPagina("https://www.youtube.com/channel/UCCqz9CJtH17DSMU4KJs-mfQ");
        System.out.println(raposa.exibePaginas());
        raposa.proximaAba();
        raposa.fechaAba();
        System.out.println(raposa.exibePaginas());
    }
}

class Navegador {
    String nome;
    List<AbaAnonima> abas;
    int abaSelecionada;

    Navegador(String nome, String aba) {
        this.nome = nome;
        this.abas = new ArrayList<AbaAnonima>();
        this.abaSelecionada = 0;
        this.abas.add(new Aba(aba));
    }

    Navegador(String nome) {
        this.nome = nome;
        this.abas = new ArrayList<AbaAnonima>();
        this.abaSelecionada = 0;
        this.abas.add(new Aba("http://www.uff.br/"));
    }

    public void adicionaAba(AbaAnonima aba) {
        this.abas.add(aba);
    }

    public String exibePaginas() {
        String paginas = "";
        for (int i = 0; i < abas.size(); i++) {
            paginas += "Aba " + i + ": " + this.abas.get(i).toString() + "\n";
        }
        return paginas;
    }

    public void proximaAba() {
        this.abaSelecionada++;
        if (this.abaSelecionada >= this.abas.size()) {
            this.abaSelecionada = 0;
        }
    }

    public void acessaPagina(String pagina) {
        this.abas.remove(this.abaSelecionada);
        this.abas.add(this.abaSelecionada, new Aba(pagina));

    }

    public void fechaAba() {
        this.abas.remove(this.abaSelecionada);
    }
}

class AbaAnonima {
    String url;

    AbaAnonima(String url) {
        this.url = url;
    }

    public String toString() {
        return this.url;
    }
}

class Aba extends AbaAnonima {
    String url;
    LocalDateTime dataAcesso;

    Aba(String url) {
        super(url);
        this.url = url;
        dataAcesso = LocalDateTime.now();
    }

    public String toString() {
        return this.url + " " + this.dataAcesso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm"));
    }
}
