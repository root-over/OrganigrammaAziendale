import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

public class OrganigrammaPage extends JFrame {
    private mxGraph graph;
    private Object parent;

    public OrganigrammaPage(UnitaOrganizzativa rootUnit) {
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

        // Aggiunta del componente alla finestra
        add(graphComponent);



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
