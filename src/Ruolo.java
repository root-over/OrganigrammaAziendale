import java.io.Serializable;

public class Ruolo implements Serializable { //Rappresenta un ruolo che può essere associato a un dipendente all'interno di un unità organizzativa specifica
    private final String nome;

    public Ruolo(String nome){
        this.nome=nome;
    }

    public String getNome(){
        return nome;
    }

}
