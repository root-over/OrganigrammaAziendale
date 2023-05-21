import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/*
 * Created by JFormDesigner on Fri May 19 15:12:41 CEST 2023
 */



/**
 * @author root_over
 */
public class OrganigrammaGUI extends JPanel {
    public OrganigrammaGUI() {
        initComponents();
    }

    private void Accedi(ActionEvent e) {
        // TODO add your code here
    }



    private void initComponents() {

        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Giuseppe
        label1 = new JLabel();
        label3 = new JLabel();
        utente = new JFormattedTextField();
        label2 = new JLabel();
        password = new JPasswordField();
        AccediButton = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
        EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing
        . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
        java. awt. Color. red) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( )
        { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () ))
        throw new RuntimeException( ); }} );
        setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Organigramma Aziendale");
        label1.setEnabled(false);
        label1.setFont(new Font("Noto Sans", Font.PLAIN, 24));
        add(label1, "cell 12 3 2 1");

        //---- label3 ----
        label3.setText("Utente");
        add(label3, "cell 10 10");
        add(utente, "cell 12 10 2 1");

        //---- label2 ----
        label2.setText("Password");
        add(label2, "cell 10 12");
        add(password, "cell 12 12 2 1");

        //---- AccediButton ----
        AccediButton.setText("Accedi");
        AccediButton.addActionListener(e -> Accedi(e));
        add(AccediButton, "cell 12 15 2 1");
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Giuseppe
    private JLabel label1;
    private JLabel label3;
    private JFormattedTextField utente;
    private JLabel label2;
    private JPasswordField password;
    private JButton AccediButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OrganigrammaGUI();
            }
        });
    }
}
