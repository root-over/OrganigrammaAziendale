import java.util.ArrayList;
import java.util.List;

public class UnitaOrganizzativa { // Rappresenta un unità organizzativa e le sottounità organizzative

    private String nome;
    private List<UnitaOrganizzativa> sottounita;
    private List<Ruolo> ruoli;

    //TODO puo essere utile una lista di dipendenti che sono in una determinata unità

    public UnitaOrganizzativa(String nome){
        this.nome=nome;
        this.sottounita=new ArrayList<>();
        this.ruoli=new ArrayList<>();
    }

    public void aggiungiRuolo(Ruolo ruolo){
        this.ruoli.add(ruolo);
    }

    public void rimuoviRuolo(Ruolo ruolo){
        this.ruoli.remove(ruolo);
    }

    public void aggiungiSottounita(UnitaOrganizzativa sottounita){
        this.sottounita.add(sottounita);
    }

    public void rimuoviSottounita(UnitaOrganizzativa sottunita){
        this.sottounita.remove(sottunita);
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