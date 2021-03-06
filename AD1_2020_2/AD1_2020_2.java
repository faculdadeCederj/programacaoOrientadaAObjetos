package AD1_2020_2;

public class AD1_2020_2 {
    public static void main(String[] args) {
        ServidorEmails servidor = new ServidorEmails();
        CaixaPostal fulano = new CaixaPostal("Fulano");
        CaixaPostal sicrano = new CaixaPostal("Sicrano");
        CaixaPostal beltrano = new CaixaPostal("Beltrano");
        CaixaPostal outrano = new CaixaPostal("Outrano");
        servidor.adicionaCaixa(fulano);
        servidor.adicionaCaixa(sicrano);
        servidor.adicionaCaixa(beltrano);
        servidor.adicionaCaixa(outrano);
        Email e1 = new Email("Fulano",
                            new String[]{"Sicrano", "Beltrano"},
                            "Super importante!",
                            "Retorne-me o quanto antes!");
        servidor.enviaEmail(e1);
        servidor.encaminhaEmail(e1, "Outrano");
        System.out.println(fulano);
        System.out.println(sicrano);
        System.out.println(beltrano);
        System.out.println(outrano);
    }
}
