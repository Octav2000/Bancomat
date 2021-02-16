package GUI;

import IDE.Client;
import IDE.StocarePersoane;

import javax.swing.*;
import java.awt.*;

public class SchimbarePIN {

	private JFrame frameSP;
	private JPanel panel;
	private JPasswordField textField;
	private JPasswordField textField_1;

	public SchimbarePIN(String x, StocarePersoane persoane) {
		initialize(x, persoane);
	}

	private void labels(JPanel panel, String x) {

		JLabel label = new JLabel("Cele 2 campuri trebuie completat cu exact 4 cifre");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(88, 24, 305, 47);
		panel.add(label);

		JLabel label_1 = new JLabel("Noul PIN");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(53, 82, 149, 46);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Confirma PIN");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(53, 153, 149, 46);
		panel.add(label_2);

	}

	private void textFields(JPanel panel) {
		textField = new JPasswordField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(258, 82, 135, 46);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JPasswordField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(258, 153, 135, 46);
		panel.add(textField_1);
	}

	public boolean contineNumaiCifre(String passwordTest) {
		return passwordTest.chars().allMatch(Character::isDigit);
	}

	private void buttons(JPanel panel, String x, StocarePersoane persoane) {
		JButton buttonE = new JButton("EXIT");
		buttonE.setBounds(10, 378, 110, 46);
		buttonE.addActionListener(e -> {
			frameSP.dispose();
			JOptionPane.showMessageDialog(null, "La revedere", "EXIT", JOptionPane.PLAIN_MESSAGE);
		});
		panel.add(buttonE);

		JButton buttonAltaTranzactie = new JButton("Alta tanzactie");
		buttonAltaTranzactie.setBounds(274, 378, 200, 46);
		buttonAltaTranzactie.addActionListener(e -> {
			frameSP.dispose();
			new Meniu(x, persoane);
		});
		panel.add(buttonAltaTranzactie);

		JButton buttonC = new JButton("Confirma");
		buttonC.setBounds(318, 256, 125, 52);
		buttonC.addActionListener(e -> {
			schimbPIN(x, persoane);
		});
		panel.add(buttonC);
	}

	private void schimbPIN(String x, StocarePersoane persoane) {

		// ambele campuri trebuie completate
		if (textField.getPassword().toString().equals("") || textField_1.getPassword().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Nu toate campurile sunt completate", "ATENTIE!",
					JOptionPane.WARNING_MESSAGE);
		} else {

			// cazul in care nu sunt introduse DOAR 4 cifre
			if (!contineNumaiCifre(String.valueOf(textField.getPassword()))
					|| !contineNumaiCifre(String.valueOf(textField_1.getPassword())))
				JOptionPane.showMessageDialog(null, "Unul din campuri nu contine DOAR 4 cifre", "ATENTIE!",
						JOptionPane.WARNING_MESSAGE);
			else {

				// noul pin nu trebuie sa fie cel vechi
				if (Integer.parseInt(String.valueOf(textField.getPassword())) == persoane.getPin(x))
					JOptionPane.showMessageDialog(null, "Noul PIN este vechiul PIN", "ATENTIE!",
							JOptionPane.WARNING_MESSAGE);
				else {

					// cazul in care nu se confirma bine pinul
					if (!String.valueOf(textField_1.getPassword()).equals(String.valueOf(textField.getPassword())))
						JOptionPane.showMessageDialog(null, "Cele doua PIN uri difera", "ATENTIE!",
								JOptionPane.WARNING_MESSAGE);

					else {
						Client c = new Client(x, Integer.parseInt(String.valueOf(textField_1.getPassword())),
								persoane.getSuma(x));
						persoane.actualizareClient(c);
						persoane.afisareFisier();
						JOptionPane.showMessageDialog(null, "PIN SCHIMBAT", "Gata", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	private void initialize(String x, StocarePersoane persoane) {
		frameSP = new JFrame("Schimbare PIN");
		frameSP.setBounds(1000, 200, 500, 500);
		frameSP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frameSP.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		labels(panel, x);
		textFields(panel);
		buttons(panel, x, persoane);

		frameSP.setVisible(true);
	}
}
