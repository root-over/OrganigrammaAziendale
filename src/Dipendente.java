import java.util.ArrayList;
import java.util.List;

public class Dipendente { //Rappresenta un dipendente e ha un riferimento al ruolo associato in un unità organizzativa specifica
    private String nome;
    private List<UnitaOrganizzativa> unitaOrganizzativa;
    private List<Ruolo> ruoli;


    public Dipendente(String nome){
        this.nome=nome;
        this.unitaOrganizzativa=new ArrayList<>();
        this.ruoli=new ArrayList<>();
    }

    public void aggiungiAdUnita(UnitaOrganizzativa unita){
        if (unitaOrganizzativa.contains(unita)){
            System.out.println("Il dipendente fa già parte dell'unità "+unita);
        }else {
            this.unitaOrganizzativa.add(unita);
            System.out.println("Dipendente aggiunto all'unità "+unita);
        }
    }

    public void aggiungiAdUnita(UnitaOrganizzativa unita, Ruolo ruolo){ //TODO
        if (unitaOrganizzativa.contains(unita)){
            System.out.println("Il dipendente fa già parte dell'unità "+unita);
        }else {
            this.unitaOrganizzativa.add(unita);
            System.out.println("Dipendente aggiunto all'unità "+unita);        }
    }

    public void rimuoviDaUnita(UnitaOrganizzativa unita){ //TODO RIMUOVERE ANCHE I RUOLI CHE AVEVA IN QUELLA UNITA
        if (!unitaOrganizzativa.contains(unita)){
            System.out.println("Il dipendente già non fa parte dell'unità "+unita);
        }else {
            this.unitaOrganizzativa.remove(unita);
            System.out.println("Dipendente rimosso dall'unità "+unita);
        }
    }

    public void aggiungiRuolo(Ruolo ruolo, UnitaOrganizzativa unita) { //Serve specificre anche l'unità perchè è possibile che più unità hanno ruoli con lo stesso nome
        if (!unitaOrganizzativa.contains(unita)) {
            System.out.println("Il dipendente non fa parte dell'unità " + unita);
        }
            else if (!unita.getRuoli().contains(ruolo)) {
                System.out.println("Questa unità organizzativa non ha al suo interno il ruolo " + ruolo);
            } else {
                this.ruoli.add(ruolo);
            }
    }
}
