import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitaOrganizzativa { // Rappresenta un unità organizzativa e le sottounità organizzative

    private String nome;
    private List<UnitaOrganizzativa> sottounita;
    private List<Ruolo> ruoli;
    private Map<Dipendente,Ruolo> dipendenti; //Ogni dipendente può vere al più un ruolo all'interno di un unità organizzativa

    public UnitaOrganizzativa(String nome){
        this.nome=nome;
        this.sottounita=new ArrayList<>();
        this.ruoli=new ArrayList<>();
        this.dipendenti= new HashMap<>();
    }

    public void aggiungiDipendente(Dipendente dipendente, Ruolo ruolo){
        if (this.dipendenti.containsKey(dipendente)){
            System.out.println("Il dipendente fa già parte dell'unità");
        }if (!ruoli.contains(ruolo)){
            System.out.println("Il ruolo "+ruolo+" non esiste in questa unità organizzativa");
        }else {
            dipendenti.put(dipendente,ruolo);
            dipendente.setRuolo(this,ruolo);
            System.out.println("Dipendente aggiunto");
        }
    }

    public void rimuoviDipendente(Dipendente dipendente){
        if (!dipendenti.containsKey(dipendente)){
            System.out.println("Questo dipendente non fa parte di questa unità");
        }else {
            dipendenti.remove(dipendente);
            dipendente.rimuoviRuolo(this);
            System.out.println("Dipendente rimosso");
        }
    }

    public void modificaRuoloDipendente(Dipendente dipendente, Ruolo ruolo){
        if (!ruoli.contains(ruolo)){
            System.out.println("Il ruolo "+ruolo+" non esiste in questa unità organizzativa");
        }if (dipendenti.get(dipendente)==ruolo){
            System.out.println("Il dipendente ha gia questo ruolo");
        }
        else {
            dipendenti.put(dipendente,ruolo);
            dipendente.setRuolo(this,ruolo);
            System.out.println("Ruolo del dipendente aggiornato");
        }
    }

    public void aggiungiRuolo(Ruolo ruolo){
        this.ruoli.add(ruolo);
        System.out.println("Ruolo aggiunto");
    }

    public void rimuoviRuolo(Ruolo ruolo){
        this.ruoli.remove(ruolo);
        System.out.println("Ruolo rimosso");
    }

    public void aggiungiSottounita(UnitaOrganizzativa sottounita){
        this.sottounita.add(sottounita);
        System.out.println("Sotto unità aggiunta");
    }

    public void rimuoviSottounita(UnitaOrganizzativa sottunita){
        this.sottounita.remove(sottunita);
        System.out.println("Sotto unità rimossa");
    }

    public List<Ruolo> getRuoli() {
        return ruoli;
    }


    public String getNome() {
        return nome;
    }

    public List<UnitaOrganizzativa> getSottounita(){
        return sottounita;
    }


}