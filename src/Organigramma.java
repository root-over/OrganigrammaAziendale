import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Organigramma implements Serializable {//Rappresenta l'organigramma aziendale, contiene le unità organizzative e gli organi di gestione
        private final UnitaOrganizzativa radice;


        public Organigramma(UnitaOrganizzativa radice) {
            this.radice = radice;
        }

    public UnitaOrganizzativa trovaUnitaPerNome(String nomeUnita) {
        return trovaUnitaPerNomeRicorsivo(radice, nomeUnita);
    }

    private UnitaOrganizzativa trovaUnitaPerNomeRicorsivo(UnitaOrganizzativa unita, String nomeUnita) {
        if (unita.getNome().equals(nomeUnita)) {
            return unita;
        }

        for (UnitaOrganizzativa sottounita : unita.getSottounita()) {
            UnitaOrganizzativa risultato = trovaUnitaPerNomeRicorsivo(sottounita, nomeUnita);
            if (risultato != null) {
                return risultato;
            }
        }

        return null;
    }

    public void eliminaUnita(UnitaOrganizzativa unitaDaEliminare) {
        eliminaUnitaRicorsivo(radice, unitaDaEliminare);
    }

    private void eliminaUnitaRicorsivo(UnitaOrganizzativa unitaCorrente, UnitaOrganizzativa unitaDaEliminare) {
        unitaCorrente.rimuoviSottounita(unitaDaEliminare);

        for (UnitaOrganizzativa sottounita : new ArrayList<>(unitaCorrente.getSottounita())) {
            if (sottounita.equals(unitaDaEliminare)) {
                unitaCorrente.rimuoviSottounita(sottounita);
            } else {
                eliminaUnitaRicorsivo(sottounita, unitaDaEliminare);
            }
        }
    }



    public UnitaOrganizzativa getRadice() {
            return radice;
        }

    public List<UnitaOrganizzativa> getOrganigrammi(UnitaOrganizzativa radice) {
        List<UnitaOrganizzativa> organigrammi = new ArrayList<>();
        organigrammi.add(radice);
        getOrganigrammiRicorsivo(radice, organigrammi);
        return organigrammi;
    }

    private void getOrganigrammiRicorsivo(UnitaOrganizzativa unita, List<UnitaOrganizzativa> organigrammi) {
        for (UnitaOrganizzativa sottounita : unita.getSottounita()) {
            organigrammi.add(sottounita);
            getOrganigrammiRicorsivo(sottounita, organigrammi);
        }
    }

    public List<String> getNomiOrganigramma(UnitaOrganizzativa radice) {
        List<String> nomiOrganigramma = new ArrayList<>();
        getNomiOrganigrammaRicorsivo(radice, nomiOrganigramma);
        return nomiOrganigramma;
    }

    private void getNomiOrganigrammaRicorsivo(UnitaOrganizzativa unita, List<String> nomiOrganigramma) {
        nomiOrganigramma.add(unita.getNome());
        for (UnitaOrganizzativa sottounita : unita.getSottounita()) {
            getNomiOrganigrammaRicorsivo(sottounita, nomiOrganigramma);
        }
    }



    private void stampaOrganigramma(UnitaOrganizzativa unita, int livello) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < livello; i++) {
                sb.append("\t");
            }
            sb.append(unita.getNome());
            System.out.println(sb.toString());

            for (UnitaOrganizzativa sottounita : unita.getSottounita()) {
                stampaOrganigramma(sottounita, livello + 1);
            }
        }
    }
