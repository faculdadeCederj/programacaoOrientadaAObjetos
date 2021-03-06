package AD1_2020_2;

public class Email {
    public String de, assunto, texto;
    public String[] para;

    public Email(String de, String[] para, String assunto, String texto) {
        this.de = de;
        this.para = para;
        this.assunto = assunto;
        this.texto = texto;
    }

    public String toString() {
        String para = "";

        for ( int i = 0; i < this.para.length; i++) {
            if (i != 0) {
                para += ", " + this.para[i];
            }
            else {
                para += this.para[i];
            }
        }
        return "De:" + this.de + "\n" + "Para: [" +
                para + "]\n" + this.assunto + "\n" + this.texto;
    }
}
