import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
//TODO AGGIUNGERE DIVERSI UTENTI E OGNI UTENTE HA DEI PERMESSI 
    public LoginGUI() {
        // Configurazione della finestra di login
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 150));
        setResizable(false);

        // Creazione dei componenti
        JLabel nome = new JLabel("LOGIN");
        nome.setForeground(Color.WHITE);
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        usernameField = new JTextField(20);
        usernameField.setBackground(new Color(87, 85, 85));
        usernameField.setForeground(Color.WHITE);
        passwordField = new JPasswordField(20);
        passwordField.setForeground(Color.WHITE);
        passwordField.setBackground(new Color(87, 85, 85));
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(44, 143, 245)); //COLORO PULSANTE
        loginButton.setForeground(Color.WHITE);

        // Creazione del pannello di login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(44, 43, 43));

        // Posizionamento dei componenti all'interno del pannello
        nome.setBounds(170,10,50,30);
        usernameLabel.setBounds(50, 50, 80, 20);
        usernameField.setBounds(150, 50, 200, 20);
        passwordLabel.setBounds(50, 80, 80, 20);
        passwordField.setBounds(150, 80, 200, 20);
        loginButton.setBounds(230, 110, 120, 30);

        // Aggiunta dei componenti al pannello
        loginPanel.add(nome);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        // Aggiunta del pannello di login alla finestra
        add(loginPanel);

        // Aggiunta del listener al pulsante di login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                if (effettuaLogin(username, password)) {
                    showMainPage();
                } else {
                    JOptionPane.showMessageDialog(LoginGUI.this, "Login fallito", "Errore", JOptionPane.ERROR_MESSAGE);
                }

                // Pulizia dei campi di input
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        // Visualizzazione della finestra di login
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean effettuaLogin(String username, char[] password) {
        // Implementa la logica di autenticazione qui
        // Restituisci true se il login ha successo, altrimenti false
        // Esempio di login fittizio: username = "admin", password = "password"
        String inputPassword = new String(password);
        return username.equals("admin") && inputPassword.equals("admin");
    }

    private void showMainPage() {
        // Chiudi la finestra di login e apri la pagina principale dell'applicazione
        dispose();
        OrganigrammaGui mainPage = new OrganigrammaGui();
        mainPage.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
    }
}
