import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Dipendente implements Serializable { //Rappresenta un dipendente e ha un riferimento al ruolo associato in un unità organizzativa specifica
    private final String nome;
    private final Map<UnitaOrganizzativa,Ruolo> ruoli;

    public Dipendente(String nome){
        this.nome=nome;
        this.ruoli=new HashMap<>();
    }
    public void getRuoliString(){
        System.out.println("Lista dei ruoli di "+nome);
        for (UnitaOrganizzativa u : ruoli.keySet()){
            System.out.println(ruoli.get(u).getNome()+" -> "+u.getNome());
        }
    }

    public Map<UnitaOrganizzativa, Ruolo> getRuoli(){
        return ruoli;
    }

    public String getNome(){
        return nome;
    }

    public void setRuolo(UnitaOrganizzativa unita, Ruolo ruolo){
        ruoli.put(unita,ruolo);
    }

    public void rimuoviRuolo(UnitaOrganizzativa unita){
        ruoli.remove(unita);
    }

    public static void main(String[] args) { //TEST
        UnitaOrganizzativa u = new UnitaOrganizzativa("Apple");
        Ruolo r = new Ruolo("Capo");
        Ruolo r2= new Ruolo("Apprendista");

        Dipendente d = new Dipendente("Giuseppe");

        u.aggiungiRuolo(r);
        u.aggiungiDipendente(d,r);

        u.aggiungiDipendente(d,r2);
        u.modificaRuoloDipendente(d,r);
        d.getRuoliString();
    }
}
