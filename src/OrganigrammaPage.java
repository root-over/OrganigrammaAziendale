import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganigrammaPage extends JFrame { //Pagina visualizzazzione grafico organigramma
    private mxGraph graph;
    private Object parent;

    public OrganigrammaPage(UnitaOrganizzativa rootUnit) { //TODO invece di ricevere l'unità riceve deve ricevere l'organigramma che contiene l'unità
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
            buildGraph(rootUnit, null);
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
                //TODO codice per aggiungere unità

            }
        });

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                OrganigrammaGui main = new OrganigrammaGui();
                main.setVisible(true);
            }
        });

        salvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO codice per salvare modifiche
            }
        });

        rimuoviUnitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO codice per rimuovere unità
            }
        });

        modificaUnitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO codice per modificare unità
            }
        });

        // Visualizzazione della finestra
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
