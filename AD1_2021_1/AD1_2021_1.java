package AD1_2021_1;


import java.util.ArrayList;
import java.util.List;

public class AD1_2021_1 {
    public static void main(String[] args) {
        Amigo fulano = new Amigo("Fulano");
        Amigo ciclano = new Amigo("Ciclano");
        Amigo beltrano = new Amigo("Beltrano");

        Rede paraiso = new Rede();
        paraiso.adicionarAmigo(fulano);
        paraiso.adicionarAmigo(ciclano);
        paraiso.adicionarAmigo(beltrano);

        Post bomdia = new Post("Bom dia!");
        Post boatarde = new Post("Bom tarde!");
        Post boanoite = new Post("Bom noite!");

        fulano.postar(bomdia);
        fulano.postar(boatarde);
        ciclano.postar(boanoite);

        beltrano.curtir(bomdia);
        beltrano.curtir(boatarde);
        beltrano.curtir(boanoite);
        fulano.curtir(boanoite);

        System.out.println("Timeline:");
        System.out.println(paraiso.timeline());
        System.out.println("Post mais curtido de um usuário:");
        Post maiscurtido = ciclano.retornaPostMaisCurtido();
        System.out.println(maiscurtido);
        System.out.println("Quem curtiu: " + maiscurtido.retornaNomesQueCurtiram());
    }
}


class Amigo {
    String nome;
    List<Post> posts;

    public Amigo(String nome) {
        this.nome = nome;
        this.posts = new ArrayList<Post>();
    }

    void curtir(Post post) {
        post.curtir(this);
    }

    Post retornaPostMaisCurtido() {
        int qtdCurtidas = 0;
        Post postMaisCurtido = null;
        for (Post post : posts) {
            if (post.curtidas > qtdCurtidas) {
                qtdCurtidas = post.curtidas;
                postMaisCurtido = post;
            }
        }
        return postMaisCurtido;
    }
    void postar(Post post) {
        this.posts.add(post);
    }
}


class Post {
    String mensagem;
    int curtidas;
    List<Amigo> nomesCurtiram;

    public Post(String mensagem) {
        this.mensagem = mensagem;
        this.curtidas = 0;
        this.nomesCurtiram = new ArrayList<Amigo>();
    }

    String retornaNomesQueCurtiram() {
        String nomes = "";
        for ( Amigo amigo : nomesCurtiram) {
            nomes += amigo.nome + " ";
        }
        return nomes;
    }

    void curtir(Amigo amigo) {
        this.nomesCurtiram.add(amigo);
        curtidas ++;
    }

    @Override
    public String toString() {
        return this.mensagem + ": " + this.curtidas + " curtidas\n";
    }
}

class Rede {
    List<Amigo> amigos;

    public Rede() {
        this.amigos = new ArrayList<Amigo>();
    }

    void adicionarAmigo(Amigo amigo) {
        this.amigos.add(amigo);
    }

    String timeline() {
        String timelineMessage = "";
        for (Amigo amigo : amigos) {
            timelineMessage += amigo.nome + ":" + "\n";
            for (Post post : amigo.posts) {
                timelineMessage += post.mensagem + ": " + post.curtidas + " curtidas \n";
            }
        }

        return timelineMessage;
    }
}