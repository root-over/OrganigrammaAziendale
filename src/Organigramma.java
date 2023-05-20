import java.util.HashMap;
import java.util.Map;

public class Organigramma {//Rappresenta l'organigramma aziendale, contiene le unità organizzative e gli organi di gestione
        private final UnitaOrganizzativa radice;
        private final Map<String, UnitaOrganizzativa> mappaUnita;

        public Organigramma(UnitaOrganizzativa radice) {
            this.radice = radice;
            this.mappaUnita = new HashMap<>();
            costruisciMappaUnita(radice);
        }

        private void costruisciMappaUnita(UnitaOrganizzativa unita) {
            mappaUnita.put(unita.getNome(), unita);
            for (UnitaOrganizzativa sottounita : unita.getSottounita()) {
                costruisciMappaUnita(sottounita);
            }
        }

        public UnitaOrganizzativa getRadice() {
            return radice;
        }

        public UnitaOrganizzativa getUnitaPerId(String id) {
            return mappaUnita.get(id);
        }

        public void stampaOrganigramma() {
            stampaOrganigramma(radice, 0);
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
