package GUI;

import IDE.StocarePersoane;
import javax.swing.*;
import java.awt.*;

public class Login {

    // variabila pentru a contoriza numarul de introducere gresit a parolei
    int incercari = 3;

    private JFrame frameL;
    private JPanel panel;
    private JLabel label_2;
    private JTextField textField;
    private JPasswordField textField_1;

    public Login(StocarePersoane persoane) {
        initialize(persoane);
    }

    // metoda ce determina daca usernameul contine numai litere
    public boolean contineNumaiLitere(String x) {
        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);
            if (!Character.isLetter(c) && c != ' ')
                return false;
        }
        return true;
    }

    // metoda pentru parola daca contine numai cifre
    public boolean contineNumaiCifre(String passwordTest) {
        return passwordTest.chars().allMatch(Character::isDigit);
    }

    private void labels() {
        JLabel label = new JLabel("Username:");
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBounds(91, 126, 119, 35);
        panel.add(label);

        JLabel label_1 = new JLabel("Password:");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label_1.setHorizontalAlignment(SwingConstants.RIGHT);
        label_1.setBounds(91, 172, 119, 35);
        panel.add(label_1);

        label_2 = new JLabel("Te rugam ca username-ul sa contina doar litere, iar parola doar 4 cifre");
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_2.setBounds(0, 11, 454, 69);
        panel.add(label_2);

    }

    private void textFields() {
        textField = new JTextField();
        textField.setBounds(245, 126, 119, 38);
        textField.setColumns(10);
        panel.add(textField);

        textField_1 = new JPasswordField();
        textField_1.setBounds(245, 172, 119, 38);
        textField_1.setColumns(10);
        panel.add(textField_1);
    }

    private void buttons(StocarePersoane persoane) {
        JButton button = new JButton("EXIT");
        button.addActionListener(e -> frameL.dispose());
        button.setBounds(33, 405, 89, 23);
        panel.add(button);

        JButton button_1 = new JButton("LOGIN");
        button_1.addActionListener(e -> {

            //daca username-ul nu contine numai litere sau nu exista in baza de date
            if (!contineNumaiLitere(textField.getText()) || !persoane.existaPersoana(textField.getText())) {
                JOptionPane.showMessageDialog(null, "Username ul nu a fost introdus corect/Nu existi in baza noastra de date", "ATENTIE!", JOptionPane.WARNING_MESSAGE);
            } else {
                // daca parola nu contine numai cifre sau daca nu are exact 4 litere
                if (!contineNumaiCifre(new String(textField_1.getPassword())) || textField_1.getPassword().length != 4) {
                    JOptionPane.showMessageDialog(null, "Parola ul nu a fost introdusa corect", "ATENTIE!", JOptionPane.WARNING_MESSAGE);
                    incercari--;
                    label_2.setText("Mai ai " + incercari + " incercari de a introduce corect PIN ul");
                    // daca s-a introdus parola gresit de 3 ori atunci esti scos din bancomat
                    if (incercari == 0) {
                        frameL.dispose();
                        JOptionPane.showMessageDialog(null, "Ne pare rau, dar ai introdus gresit parola de 3 ori", "ATENTIE!", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    // daca parola introdusa este pinul corespunzator pentru username
                    if (!persoane.corectitudinePin(textField.getText(), Integer.parseInt(String.valueOf(textField_1.getPassword())))) {
                        JOptionPane.showMessageDialog(null, "Nu este PIN ul corect", "ATENTIE!", JOptionPane.WARNING_MESSAGE);
                        incercari--;
                        label_2.setText("Mai ai " + incercari + " incercari de a introduce corect PIN ul");
                        // daca s-a introdus parola gresit de 3 ori atunci esti scos din bancomat
                        if (incercari == 0) {
                            frameL.dispose();
                            JOptionPane.showMessageDialog(null, "Ne pare rau, dar ai introdus gresit parola de 3 ori", "ATENTIE!", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        frameL.dispose();
                        new Meniu(textField.getText(), persoane);
                    }
                }

            }
        });

        button_1.setBounds(352, 405, 89, 23);
        panel.add(button_1);
    }

    private void initialize(StocarePersoane persoane) {
        frameL = new JFrame();
        frameL.setBounds(1000, 200, 500, 500);
        frameL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameL.getContentPane().setLayout(null);

        panel = new JPanel();
        panel.setBounds(10, 11, 464, 439);
        frameL.getContentPane().add(panel);
        panel.setLayout(null);

        labels();
        textFields();
        buttons(persoane);

        frameL.getContentPane().add(panel);
        frameL.setVisible(true);
    }

}
