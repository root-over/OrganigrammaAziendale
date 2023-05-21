import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class OrganigrammaPage extends JFrame { //Pagina visualizzazzione grafico organigramma

    private final mxGraph graph;
    private final Object parent;

    public OrganigrammaPage(Organigramma rootUnit) {
        // Configurazione della finestra
        setTitle("Organigramma Aziendale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        // Creazione del grafo
        graph = new mxGraph();
        parent = graph.getDefaultParent();

        // Disabilita il layout automatico predefinito
        graph.setAutoSizeCells(true);
        graph.getModel().beginUpdate();

        try {
            // Costruzione ricorsiva del grafo
            buildGraph(rootUnit.getRadice(), null);
        } finally {
            graph.getModel().endUpdate();
        }

        // Applica il layout gerarchico al grafo
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(parent);

        // Creazione del componente di visualizzazione del grafo
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setBackground(Color.WHITE);

        //Creazione pulsanti
        JButton aggiungiUnitaButton = new JButton(("Aggiungi Unità"));
        JButton rimuoviUnitaButton = new JButton("Rimuovi Unità");
        JButton modificaUnitaButton = new JButton("Modifica Unità");
        JButton salvaButton = new JButton("Salva");
        salvaButton.setBackground(Color.GREEN);
        JButton indietroButton = new JButton("Indietro");
        indietroButton.setBackground(Color.red);

        //Creazione pannello per contenere i pulsanti
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10)); // Allineamento e spaziatura dei pulsanti
        buttonPanel.add(indietroButton);
        buttonPanel.add(aggiungiUnitaButton);
        buttonPanel.add(rimuoviUnitaButton);
        buttonPanel.add(modificaUnitaButton);
        buttonPanel.add(salvaButton);


        // Aggiunta del componente alla finestra
        add(graphComponent);
        add(buttonPanel, BorderLayout.SOUTH);


        aggiungiUnitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiUnita(rootUnit);
            }
        });

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                indietro(rootUnit);
            }
        });
        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvataggio(rootUnit);
            }

        });
        rimuoviUnitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rimuoviUnita(rootUnit);
            }
        });

        modificaUnitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificaUnita(rootUnit);
            }
        });

        // Visualizzazione della finestra
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void indietro(Organigramma rootUnit){
        int scelta = JOptionPane.showOptionDialog(null,
                "Vuoi salvare prima di uscire? ", "Attenzione",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (scelta == JOptionPane.YES_OPTION) {
            try {
                // Crea un oggetto ObjectOutputStream per scrivere l'oggetto Organigramma su un file
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("organigramma_data.bin"));

                // Scrivi l'oggetto Organigramma nel file
                outputStream.writeObject(rootUnit);

                // Chiudi lo stream di output
                outputStream.close();

                System.out.println("Organigramma salvato correttamente.");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            dispose();
            OrganigrammaGui main = new OrganigrammaGui();
            main.setVisible(true);
        }
    }

    private void rimuoviUnita(Organigramma rootUnit){
        List<String> unitaNomi = rootUnit.getNomiOrganigramma(rootUnit.getRadice());

        String[] campi = unitaNomi.toArray(new String[0]);

        JComboBox<String> campoComboBox = new JComboBox<>(campi);


        //Finestra che contiene combobox e textfileda
        JPanel panel = new JPanel();
        JLabel campoLabel = new JLabel("Seleziona l'unità da eliminare:");
        campoLabel.setForeground(Color.WHITE);
        panel.add(campoLabel);
        panel.add(campoComboBox);

        int result = JOptionPane.showOptionDialog(null, panel, "Selezione Campo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result == JOptionPane.OK_OPTION) {
            String campoSelezionato = (String) campoComboBox.getSelectedItem();
            if (rootUnit.trovaUnitaPerNome(campoSelezionato)==rootUnit.getRadice()){
                JOptionPane.showMessageDialog(null,"Non puoi eliminare la radice dell' organigramma");
            }else {

                int scelta = JOptionPane.showOptionDialog(null,
                        "Cliccando su Yes verrà eliminata l'unità con le sottounità ad essa associate", "Attenzione",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (scelta == JOptionPane.YES_OPTION) {
                    rootUnit.eliminaUnita(rootUnit.trovaUnitaPerNome(campoSelezionato));
                }
            }
        }


        // Ricostruisco il grafico con i dati aggiornati
        graph.getModel().beginUpdate();
        try {
            graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));
            buildGraph(rootUnit.getRadice(), null);
        } finally {
            graph.getModel().endUpdate();
        }
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(parent);
    }

    private void salvataggio(Organigramma rootUnit){
        try {
            // Crea un oggetto ObjectOutputStream per scrivere l'oggetto Organigramma su un file
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("organigramma_data.bin"));

            // Scrivi l'oggetto Organigramma nel file
            outputStream.writeObject(rootUnit);

            // Chiudi lo stream di output
            outputStream.close();

            System.out.println("Organigramma salvato correttamente.");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        // Ricostruisco il grafico con i dati aggiornati
        graph.getModel().beginUpdate();
        try {
            graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));
            buildGraph(rootUnit.getRadice(), null);
        } finally {
            graph.getModel().endUpdate();
        }
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(parent);
    }

    private void aggiungiUnita(Organigramma rootUnit){
        List<String> unitaNomi = rootUnit.getNomiOrganigramma(rootUnit.getRadice());

        String[] campi = unitaNomi.toArray(new String[0]);

        JComboBox<String> campoComboBox = new JComboBox<>(campi);
        JTextField nomeTextField = new JTextField(20);


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

        int result = JOptionPane.showOptionDialog(null, panel, "Selezione Campo e Inserimento Nome",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result == JOptionPane.OK_OPTION) {
            String campoSelezionato = (String) campoComboBox.getSelectedItem();
            String nomeInserito = nomeTextField.getText();
            System.out.println(campoSelezionato);
            rootUnit.trovaUnitaPerNome(campoSelezionato).aggiungiSottounita(new UnitaOrganizzativa(nomeInserito));
        }


        // Ricostruisco il grafico con i dati aggiornatix
        graph.getModel().beginUpdate();
        try {
            graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));
            buildGraph(rootUnit.getRadice(), null);
        } finally {
            graph.getModel().endUpdate();
        }
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(parent);
    }

    private void modificaUnita(Organigramma rootUnit){
        //codice per modificare unità

        List<String> unitaNomi = rootUnit.getNomiOrganigramma(rootUnit.getRadice());

        String[] campi = unitaNomi.toArray(new String[0]);

        JComboBox<String> campoComboBox = new JComboBox<>(campi);
        JTextField nomeTextField = new JTextField(20);


        //Finestra che contiene combobox e textfileda
        JPanel panel = new JPanel();
        JLabel campoLabel = new JLabel("Seleziona campo da modificare:");
        campoLabel.setForeground(Color.WHITE);
        panel.add(campoLabel);
        panel.add(campoComboBox);
        JLabel nomeLabel = new JLabel("Inserisci il nuovo nome:");
        nomeLabel.setForeground(Color.WHITE);
        panel.add(nomeLabel);
        panel.add(nomeTextField);

        int result = JOptionPane.showOptionDialog(null, panel, "Selezione Campo e Inserimento Nome",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if (result == JOptionPane.OK_OPTION) {
            String campoSelezionato = (String) campoComboBox.getSelectedItem();
            String nomeInserito = nomeTextField.getText();
            rootUnit.trovaUnitaPerNome(campoSelezionato).setNome(nomeInserito);
        }


        // Ricostruisco il grafico con i dati aggiornati
        graph.getModel().beginUpdate();
        try {
            graph.removeCells(graph.getChildVertices(graph.getDefaultParent()));
            buildGraph(rootUnit.getRadice(), null);
        } finally {
            graph.getModel().endUpdate();
        }
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.execute(parent);
    }


    private void buildGraph(UnitaOrganizzativa unita, Object parentCell) {
        Object unitCell = graph.insertVertex(parent, null, unita.getNome(), 20, 20, 100, 40);
        if (parentCell != null) {
            graph.insertEdge(parent, null, "", parentCell, unitCell);
        }

        for (UnitaOrganizzativa subUnit : unita.getSottounita()) {
            buildGraph(subUnit, unitCell);
        }

        }
    }
