package GUI;

import IDE.Client;
import IDE.StocarePersoane;

import javax.swing.*;
import java.awt.*;

public class TransferNumerar {

    private JFrame frameTN;
    private JTextField textField;
    private JTextField textField_1;

    public TransferNumerar(String nume, StocarePersoane persoane) {
        initialize(nume, persoane);
    }

    public boolean contineNumaiCifre(String passwordTest) {
        return passwordTest.chars().allMatch(Character::isDigit);
    }

    private void labels(JPanel panel, String nume, StocarePersoane persoane) {
        JLabel label = new JLabel("Suma din cont disponibila este de " + persoane.getSuma(nume) + " lei");
        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(45, 34, 383, 46);
        panel.add(label);

        JLabel label_1 = new JLabel("Suma dorita pentru transfer:");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_1.setBounds(45, 117, 221, 39);
        panel.add(label_1);

        JLabel label_2 = new JLabel("Numele contului in care se doreste transferul:");
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label_2.setBounds(0, 193, 295, 39);
        panel.add(label_2);
    }

    private void textFields(JPanel panel) {
        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField.setBounds(288, 119, 140, 39);
        panel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_1.setColumns(10);
        textField_1.setBounds(288, 193, 140, 39);
        panel.add(textField_1);
    }

    private void buttons(JPanel panel, String nume, StocarePersoane persoane) {
        JButton buttonE = new JButton("EXIT");
        buttonE.setBounds(10, 378, 110, 46);
        buttonE.addActionListener(e -> {
            frameTN.dispose();
            JOptionPane.showMessageDialog(null, "La revedere", "EXIT", JOptionPane.PLAIN_MESSAGE);
        });
        panel.add(buttonE);

        JButton buttonAltaTranzactie = new JButton("Alta tanzactie");
        buttonAltaTranzactie.setBounds(274, 378, 200, 46);
        buttonAltaTranzactie.addActionListener(e -> {
            frameTN.dispose();
            new Meniu(nume, persoane);
        });
        panel.add(buttonAltaTranzactie);

        JButton buttonTransfer = new JButton("Transferati");
        buttonTransfer.setBounds(288, 277, 110, 46);
        buttonTransfer.addActionListener(e -> {
            // conditie pentru atunci cand nu toate campurile sunt completate
            if (textField.getText().equals("") || textField_1.getText().equals(""))
                JOptionPane.showMessageDialog(null, "Nu toate campurile au fost completate", "", JOptionPane.WARNING_MESSAGE);
            else {
                // suma trebuie sa contina numai cifre
                if (!contineNumaiCifre(textField.getText()))
                    JOptionPane.showMessageDialog(null, "Suma introdusa nu contine NUMAI cifre", "", JOptionPane.WARNING_MESSAGE);
                else {
                    // daca se doreste transferul unei sume mai mari decat soldul curent al contului din care se doreste transferul
                    if (Integer.parseInt(textField.getText()) > persoane.getSuma(nume))
                        JOptionPane.showMessageDialog(null, "Suma de transfer este mai mare decat cea disponibila in cont", "", JOptionPane.WARNING_MESSAGE);
                    else {
                        // daca persoana catre care se doreste transferul nu exista in baza de date
                        if (!persoane.existaPersoana(textField_1.getText()))
                            JOptionPane.showMessageDialog(null, "Contul introdus nu exista in baza noastra de date", "", JOptionPane.WARNING_MESSAGE);
                        else {
								Client c = new Client(nume, persoane.getPin(nume), persoane.getSuma(nume)-Integer.parseInt(textField.getText()));
								persoane.actualizareClient(c);

								c = new Client(textField_1.getText(), persoane.getPin(textField_1.getText()), persoane.getSuma(textField_1.getText())+Integer.parseInt(textField.getText()));
								persoane.actualizareClient(c);

								persoane.afisareFisier();

								JOptionPane.showMessageDialog(null, "Transfer efectuat", "", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });
        panel.add(buttonTransfer);
    }

    private void initialize(String nume, StocarePersoane persoane) {
        frameTN = new JFrame("Transfer numerar");
        frameTN.setBounds(1000, 200, 500, 500);
        frameTN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frameTN.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        frameTN.setVisible(true);

        labels(panel, nume, persoane);
        textFields(panel);
        buttons(panel, nume, persoane);

        frameTN.setVisible(true);
    }

}
