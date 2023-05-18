import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dipendente { //Rappresenta un dipendente e ha un riferimento al ruolo associato in un unità organizzativa specifica
    private String nome;
    private Map<UnitaOrganizzativa,Ruolo> ruoli; //In questo caso ogni dipendente può avere un solo ruolo all'interno di un unità, se si vuole assegnargli più ruoli all'interno della stessa unità basta usare come valore una lista di ruoli


    public Dipendente(String nome){
        this.nome=nome;
        this.ruoli=new HashMap<>();
    }

    public void aggiungiAdUnita(UnitaOrganizzativa unita,Ruolo ruolo){
        if (unita.getRuoli().contains(ruolo)) {
            if (ruoli.containsKey(unita)) {
                System.out.println("Il dipendente fa già parte dell'unità " + unita.getNome());
            } else {
                this.ruoli.put(unita, ruolo);
                System.out.println("Dipendente aggiunto all'unità " + unita.getNome() + " con ruolo " + ruolo.getNome());
            }
        }else {
            System.out.println("Il ruolo "+ruolo.getNome()+" non è presente nell'unità "+unita.getNome());
        }
    }

    public void rimuoviDaUnita(UnitaOrganizzativa unita){
        if (!ruoli.containsKey(unita)){
            System.out.println("Il dipendente non fa parte dell'unità "+unita.getNome());
        }else {
            this.ruoli.remove(unita);
            System.out.println("Dipendente rimosso dall'unità "+unita.getNome());
        }
    }

    public void modificaRuolo(UnitaOrganizzativa unita, Ruolo ruolo){
        if (!ruoli.containsKey(unita)){
            System.out.println("Il Dipendente non fa parte dell'unità "+unita.getNome());
        }if (!unita.getRuoli().contains(ruolo)) {
            System.out.println("L'unità "+unita.getNome()+" non ha il ruolo "+ruolo.getNome());
        }else {
            ruoli.put(unita,ruolo);
        }
    }
    public Map<UnitaOrganizzativa, Ruolo> getRuoli(){
        return ruoli;
    }

    public String getNome(){
        return nome;
    }
}
