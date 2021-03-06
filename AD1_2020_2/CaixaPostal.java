package AD1_2020_2;


public class CaixaPostal {
    public String nome;
    public Email[] emails = new Email[0];

    public CaixaPostal(String nome) {
        this.nome = nome;
    }

    public void adicionaEmail(Email email) {
        Email[] aux = new Email[this.emails.length + 1];
        for (int i = 0; i < this.emails.length; i++) {
            aux[i] = emails[i];
        }
        aux[this.emails.length] = email;
        this.emails = aux;
    }

    public String toString() {
        System.out.println(">>> Caixa Postal de " + this.nome + " <<<");
        for (int i = 0; i < this.emails.length; i++) {
            System.out.println(this.emails[i]);
        }

        return "\n";
    }

}
