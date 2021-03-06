package AD1_2020_2;

public class ServidorEmails {
    public CaixaPostal[] caixas = new CaixaPostal[0];

    public void adicionaCaixa(CaixaPostal nome) {
        CaixaPostal[] aux = new CaixaPostal[this.caixas.length + 1];

        for (int i = 0; i < this.caixas.length; i++) {
            aux[i] = this.caixas[i];
        }

        aux[this.caixas.length] = nome;

        this.caixas = aux;
    }

    public void enviaEmail(Email e1) {
        for (int i = 0; i < e1.para.length; i++) {
            for (int j = 0; j < this.caixas.length; j++) {
                if (e1.para[i] == this.caixas[j].nome) {
                    this.caixas[j].adicionaEmail(e1);
                }
            }
        }
    }

    public void encaminhaEmail(Email e1, String nome) {
        for ( int i = 0; i < this.caixas.length; i++) {
            if (nome == this.caixas[i].nome) {
                this.caixas[i].adicionaEmail(e1);
            }
        }
    }
}
