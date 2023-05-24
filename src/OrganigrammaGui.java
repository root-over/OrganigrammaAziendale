import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class OrganigrammaGui extends JFrame { //Pagina principale dell'organigramma
    private Organigramma organigramma;


//TODO pulsante rimuovi dipendente e rimuovi ruolo
    public OrganigrammaGui() {
        UIManager.put("OptionPane.background", new Color(44, 43, 43));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.buttonForeground", Color.WHITE);
        UIManager.put("Panel.background", new Color(44, 43, 43));
        UIManager.put("Button.background", new Color(92, 132, 248));

        // Configurazione della finestra
        setTitle("Gestione Organigramma");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));
        setResizable(false);

        // Creazione dei pulsanti
        JLabel nome = new JLabel("Gestione Organigramma");
        JButton aggiungiRuoliButton = new JButton("Aggiungi Ruoli");
        JButton rimuoviRuoliButton=new JButton("Rimuovi Ruoli");
        JButton visualizzaButton = new JButton("Visualizza Organigramma");
        JButton aggiungiDipendentiButton = new JButton("Aggiungi Dipendenti");
        JButton rimuoviDipendentiButton=new JButton("Rimuovi Dipendenti");
        JButton esciButton = new JButton("Esci");

        //Posizionamento componenti nel pannello
        nome.setBounds(210,20,300,70);
        nome.setFont(new Font(nome.getName(),Font.PLAIN,25));
        nome.setForeground(Color.WHITE);

        visualizzaButton.setBounds(250,120, 200,40);
        visualizzaButton.setBackground(new Color(92, 132, 248));
        visualizzaButton.setForeground(Color.WHITE);

        aggiungiRuoliButton.setBounds(450,200,200,40);
        aggiungiRuoliButton.setBackground(new Color(92, 132, 248));
        aggiungiRuoliButton.setForeground(Color.WHITE);

        rimuoviRuoliButton.setBounds(450,300,200,40);
        rimuoviRuoliButton.setBackground(new Color(92, 132, 248));
        rimuoviRuoliButton.setForeground(Color.WHITE);

        aggiungiDipendentiButton.setBounds(50,200,200,40);
        aggiungiDipendentiButton.setBackground(new Color(92, 132, 248));
        aggiungiDipendentiButton.setForeground(Color.WHITE);

        rimuoviDipendentiButton.setBounds(50,300,200,40);
        rimuoviDipendentiButton.setBackground(new Color(92, 132, 248));
        rimuoviDipendentiButton.setForeground(Color.WHITE);

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
        panel.add(rimuoviRuoliButton);
        panel.add(aggiungiDipendentiButton);
        panel.add(rimuoviDipendentiButton);
        panel.add(esciButton);

        // Aggiunta del pannello alla finestra
        add(panel);

        // Aggiunta dei listener ai pulsanti

        aggiungiRuoliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkOrganigrammaRuol();
            }
        });

        rimuoviRuoliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO metodo per rimuozione ruoli
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
                checkOrganigramma();
            }
        });

        rimuoviDipendentiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //TODO metodi per rimozione dipendenti;
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
        String filePath = "/home/root_over/Documenti/SynologyDrive/Università/Anno 2/Ingegneria del software/ProgettoNew/untitled/organigramma_data.bin";
        File file = new File(filePath);
        if (file.exists()){
            try {
                // Crea un oggetto ObjectInputStream per leggere l'oggetto Organigramma dal file
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("organigramma_data.bin"));

                // Leggi l'oggetto Organigramma dal file
                Organigramma loadedOrganigramma = (Organigramma) inputStream.readObject();

                // Chiudi lo stream di input
                inputStream.close();

                // Utilizza l'organigramma caricato come desiderato
                // Ad esempio, assegna l'organigramma caricato al tuo oggetto organigramma esistente
                organigramma = loadedOrganigramma;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            visualizzaOrganigramma(organigramma);//passa la radice
        }else {
            int scelta = JOptionPane.showOptionDialog(null,
                    "Non è presente nessun organigramma. Vuoi crearne uno?", "Attenzione",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (scelta == JOptionPane.YES_OPTION) {
                // L'utente ha scelto di creare un organigramma
                String nomeUnita = JOptionPane.showInputDialog(null, "Inserisci il nome della radice dell'unità organizzativa:");
                Organigramma organigramma1 = new Organigramma(new UnitaOrganizzativa(nomeUnita));

                try {
                    // Crea un oggetto ObjectOutputStream per scrivere l'oggetto Organigramma su un file
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("organigramma_data.bin"));

                    // Scrivi l'oggetto Organigramma nel file
                    outputStream.writeObject(organigramma1);

                    // Chiudi lo stream di output
                    outputStream.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                visualizzaOrganigramma(organigramma1);

            }
        }
    }

    private void checkOrganigrammaDip(){

        String filePath = "/home/root_over/Documenti/SynologyDrive/Università/Anno 2/Ingegneria del software/ProgettoNew/untitled/organigramma_data.bin";
        File file = new File(filePath);


        if (file.exists()){
            try {
                // Crea un oggetto ObjectInputStream per leggere l'oggetto Organigramma dal file
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("organigramma_data.bin"));

                // Leggi l'oggetto Organigramma dal file
                Organigramma loadedOrganigramma = (Organigramma) inputStream.readObject();

                // Chiudi lo stream di input
                inputStream.close();

                // Utilizza l'organigramma caricato come desiderato
                // Ad esempio, assegna l'organigramma caricato al tuo oggetto organigramma esistente
                organigramma = loadedOrganigramma;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            aggiungiDipendenti(organigramma);//passa la radice
        }else {
            int scelta = JOptionPane.showOptionDialog(null,
                    "Non è presente nessun organigramma. Vuoi crearne uno?", "Attenzione",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (scelta == JOptionPane.YES_OPTION) {
                // L'utente ha scelto di creare un organigramma
                String nomeUnita = JOptionPane.showInputDialog(null, "Inserisci il nome della radice dell'unità organizzativa:");
                Organigramma organigramma1 = new Organigramma(new UnitaOrganizzativa(nomeUnita));

                try {
                    // Crea un oggetto ObjectOutputStream per scrivere l'oggetto Organigramma su un file
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("organigramma_data.bin"));

                    // Scrivi l'oggetto Organigramma nel file
                    outputStream.writeObject(organigramma1);

                    // Chiudi lo stream di output
                    outputStream.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                aggiungiDipendenti(organigramma1);

            }
        }
    }
//TODO invece di fare un check organigramma per ogni oggetto, farne uno singolo passando come oggetto this e in base all'instanza
//dell'oggetto richiamare la funzione apposita
    private void checkOrganigrammaRuol(){
        String filePath = "/home/root_over/Documenti/SynologyDrive/Università/Anno 2/Ingegneria del software/ProgettoNew/untitled/organigramma_data.bin";
        File file = new File(filePath);


        if (file.exists()){
            try {
                // Crea un oggetto ObjectInputStream per leggere l'oggetto Organigramma dal file
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("organigramma_data.bin"));

                // Leggi l'oggetto Organigramma dal file
                Organigramma loadedOrganigramma = (Organigramma) inputStream.readObject();

                // Chiudi lo stream di input
                inputStream.close();

                // Utilizza l'organigramma caricato come desiderato
                // Ad esempio, assegna l'organigramma caricato al tuo oggetto organigramma esistente
                organigramma = loadedOrganigramma;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            aggiungiRuolo(organigramma);//passa la radice
        }else {
            int scelta = JOptionPane.showOptionDialog(null,
                    "Non è presente nessun organigramma. Vuoi crearne uno?", "Attenzione",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (scelta == JOptionPane.YES_OPTION) {
                // L'utente ha scelto di creare un organigramma
                String nomeUnita = JOptionPane.showInputDialog(null, "Inserisci il nome della radice dell'unità organizzativa:");
                Organigramma organigramma1 = new Organigramma(new UnitaOrganizzativa(nomeUnita));

                try {
                    // Crea un oggetto ObjectOutputStream per scrivere l'oggetto Organigramma su un file
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("organigramma_data.bin"));

                    // Scrivi l'oggetto Organigramma nel file
                    outputStream.writeObject(organigramma1);

                    // Chiudi lo stream di output
                    outputStream.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                aggiungiRuolo(organigramma1);

            }
        }
    }
    private void visualizzaOrganigramma(Organigramma radice) {
        dispose();
        OrganigrammaPage grafica = new OrganigrammaPage(radice);
        grafica.setVisible(true);
    }

    private void aggiungiDipendenti(Organigramma organigramma) {
        List<String> unitaNomi = organigramma.getNomiOrganigramma(organigramma.getRadice());
        List<Ruolo> unitaRuoli;

        String[] campi = unitaNomi.toArray(new String[0]);
        Ruolo[] ruoli = new Ruolo[0];

        JComboBox<String> campoComboBox = new JComboBox<>(campi);
        JTextField nomeTextField = new JTextField(20);
        JComboBox<Ruolo> ruoloComboBox = new JComboBox<>(ruoli);


        //Finestra che contiene combobox e textfileda
        JPanel panel = new JPanel();
        JLabel campoLabel = new JLabel("Seleziona un campo:");
        campoLabel.setForeground(Color.WHITE);
        panel.add(campoLabel);
        panel.add(campoComboBox);
        JLabel nomeLabel = new JLabel("Inserisci un nome:");
        nomeLabel.setForeground(Color.WHITE);
        panel.add(nomeLabel);
        panel.add(nomeTextField);
        JLabel ruoloLabel = new JLabel("Seleziona il ruolo:");
        ruoloLabel.setForeground(Color.WHITE);
        panel.add(ruoloLabel);
        panel.add(ruoloComboBox);

        int result = JOptionPane.showOptionDialog(null, panel, "Selezione Campo e Inserimento Nome",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        //FIXME penso non vada perchè la combo box dei ruoli non viene aggiornata con i ruoli presenti nel unita
        if (result == JOptionPane.OK_OPTION) {
            String campoSelezionato = (String) campoComboBox.getSelectedItem();
            String nomeInserito = nomeTextField.getText();
            Ruolo ruoloSelezionato=(Ruolo) ruoloComboBox.getSelectedItem();
            unitaRuoli=organigramma.trovaUnitaPerNome(campoSelezionato).getRuoli();
            ruoli=unitaRuoli.toArray(new Ruolo[0]);
            organigramma.trovaUnitaPerNome(campoSelezionato).aggiungiDipendente(new Dipendente(nomeInserito),ruoloSelezionato);
        }
    }

    private void aggiungiRuolo(Organigramma organigramma){
        List<String> unitaNomi = organigramma.getNomiOrganigramma(organigramma.getRadice());

        String[] campi = unitaNomi.toArray(new String[0]);

        JComboBox<String> campoComboBox = new JComboBox<>(campi);
        JTextField nomeTextField = new JTextField(20);


        //Finestra che contiene combobox e textfileda
        JPanel panel = new JPanel();
        JLabel campoLabel = new JLabel("Seleziona un campo:");
        campoLabel.setForeground(Color.WHITE);
        panel.add(campoLabel);
        panel.add(campoComboBox);
        JLabel nomeLabel = new JLabel("Inserisci un nome ruolo:");
        nomeLabel.setForeground(Color.WHITE);
        panel.add(nomeLabel);
        panel.add(nomeTextField);

        int result = JOptionPane.showOptionDialog(null, panel, "Selezione Campo e Inserimento Nome",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result == JOptionPane.OK_OPTION) {
            String campoSelezionato = (String) campoComboBox.getSelectedItem();
            String nomeInserito = nomeTextField.getText();
            organigramma.trovaUnitaPerNome(campoSelezionato).aggiungiRuolo(new Ruolo(nomeInserito));
            System.out.println(organigramma.trovaUnitaPerNome(campoSelezionato).getRuoliString());
        }
        //FIXME la lista dei ruoli dell'unita non si aggiorna
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OrganigrammaGui();
            }
        });
    }
}
