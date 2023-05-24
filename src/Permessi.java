import java.util.HashMap;
import java.util.Map;

public class Permessi { //Rappresenta i permessi di uno specifico utente del sistema

    //TODO creare salvataggio delle credenziali su file
    private final Map<String, String> credenziali;

    public Permessi(String nome, String password){
        this.credenziali= new HashMap<>();
    }


    public boolean ePresente(String nome){
        return credenziali.containsKey(nome);
    }
    public String getPassword(String nome){
        return credenziali.get(nome);
    }

    public void aggiungiCredenziali(String nome, String password){
        credenziali.put(nome, password);
    }
}
