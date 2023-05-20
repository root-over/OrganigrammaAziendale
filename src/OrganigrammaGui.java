import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganigrammaGui extends JFrame { //Pagina principale dell'organigramma
    private final Organigramma organigramma;

    public OrganigrammaGui() {
        UIManager.put("OptionPane.background", new Color(44, 43, 43));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.buttonForeground", Color.WHITE);
        UIManager.put("Panel.background", new Color(44, 43, 43));
        UIManager.put("Button.background", new Color(92, 132, 248));

        // Creazione dell'organigramma di esempio
        UnitaOrganizzativa radice = new UnitaOrganizzativa("Direzione Generale");
        organigramma = new Organigramma(radice);

        // Configurazione della finestra
        setTitle("Gestione Organigramma");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));
        setResizable(false);

        // Creazione dei pulsanti
        JLabel nome = new JLabel("Gestione Organigramma");
        JButton aggiungiRuoliButton = new JButton(("Aggiungi Ruoli"));
        JButton visualizzaButton = new JButton("Visualizza Organigramma");
        JButton aggiungiDipendentiButton = new JButton("Aggiungi Dipendenti");
        JButton aggiungiUnitaButton = new JButton("Aggiungi Unità");
        JButton esciButton = new JButton("Esci");

        //Posizionamento componenti nel pannello
        nome.setBounds(210,20,300,70);
        nome.setFont(new Font(nome.getName(),Font.PLAIN,25));
        nome.setForeground(Color.WHITE);

        visualizzaButton.setBounds(250,120, 200,40);
        visualizzaButton.setBackground(new Color(92, 132, 248));
        visualizzaButton.setForeground(Color.WHITE);
        //TODO mettere bordi arrotondati pulsanti
        //TODO mettere bordo nero al testo dei pulsanti

        aggiungiDipendentiButton.setBounds(50,200,200,40);
        aggiungiDipendentiButton.setBackground(new Color(92, 132, 248));
        aggiungiDipendentiButton.setForeground(Color.WHITE);

        aggiungiRuoliButton.setBounds(50,300,200,40);
        aggiungiRuoliButton.setBackground(new Color(92, 132, 248));
        aggiungiRuoliButton.setForeground(Color.WHITE);

        aggiungiUnitaButton.setBounds(450,200,200,40);
        aggiungiUnitaButton.setBackground(new Color(92, 132, 248));
        aggiungiUnitaButton.setForeground(Color.WHITE);

        esciButton.setBounds(580,430,100,40);
        esciButton.setBackground(new Color(245, 48, 48));
        esciButton.setForeground(Color.BLACK);


        // Aggiunta dei pulsanti al pannello
        JPanel panel = new JPanel();
        panel.setBackground(new Color(44, 43, 43));
        panel.setLayout(null);
        panel.add(nome);
        panel.add(visualizzaButton);
        panel.add(aggiungiRuoliButton);
        panel.add(aggiungiDipendentiButton);
        panel.add(aggiungiUnitaButton);
        panel.add(esciButton);

        // Aggiunta del pannello alla finestra
        add(panel);

        // Aggiunta dei listener ai pulsanti

        aggiungiRuoliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO codice per aggiungere ruoli
            }
        });
        visualizzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkOrganigramma();
            }
        });

        aggiungiDipendentiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiDipendenti();
                //TODO codice per aggiunfere dipendenti
            }
        });

        aggiungiUnitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiUnita();
                //TODO codice per aggiungere unità
            }
        });

        esciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Visualizzazione della finestra
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void checkOrganigramma(){
        if (true){//TODO se è presente il file dell'organigramma
            //Carica il file
            visualizzaOrganigramma(new UnitaOrganizzativa("Ciao"));//passa la radice
        }else {
            int scelta = JOptionPane.showOptionDialog(null,
                    "Non è presente nessun organigramma. Vuoi crearne uno?", "Attenzione",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (scelta == JOptionPane.YES_OPTION) {
                // L'utente ha scelto di creare un organigramma
                String nomeUnita = JOptionPane.showInputDialog(null, "Inserisci il nome della radice dell'unità organizzativa:");
                UnitaOrganizzativa radice = new UnitaOrganizzativa(nomeUnita);
                JOptionPane.showMessageDialog(null,"unità organizzativa creata");
                //TODO AGGIUNGERE SALVATAGGIO Dell'organigramma
                visualizzaOrganigramma(radice);

            }
        }
    }
    private void visualizzaOrganigramma(UnitaOrganizzativa radice) {
        dispose();
        OrganigrammaPage grafica = new OrganigrammaPage(radice);
        grafica.setVisible(true);
    }

    private void aggiungiDipendenti() {
        // Implementa la logica per aggiungere i dipendenti all'organigramma
        JOptionPane.showMessageDialog(this, "Funzionalità non implementata.", "Aggiungi Dipendenti", JOptionPane.INFORMATION_MESSAGE);
    }

    private void aggiungiUnita() {
        // Implementa la logica per aggiungere le unità organizzative all'organigramma
        JOptionPane.showMessageDialog(this, "Funzionalità non implementata.", "Aggiungi Unità", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        UnitaOrganizzativa u = new UnitaOrganizzativa("Apple");
        Ruolo r = new Ruolo("Capo");
        Ruolo r2= new Ruolo("Apprendista");

        Dipendente d = new Dipendente("Giuseppe");

        u.aggiungiRuolo(r);
        u.aggiungiDipendente(d,r);

        u.aggiungiDipendente(d,r2);
        u.modificaRuoloDipendente(d,r);
        d.getRuoliString();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OrganigrammaGui();
            }
        });
    }
}
