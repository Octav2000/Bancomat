package GUI;

import IDE.Client;
import IDE.StocarePersoane;
import javax.swing.*;
import java.awt.*;

public class RetragereNumerar {

	private JFrame frameDN;
	private JPanel panel;
	private JTextField textField;

	public RetragereNumerar(String nume, StocarePersoane persoane) {
		initialize(nume,persoane);
	}

	public boolean contineNumaiCifre(String passwordTest) {
		return passwordTest.chars().allMatch(Character::isDigit);
	}

	private void labels(){
		JLabel label = new JLabel("Suma pe care o retrageti:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(46, 127, 226, 76);
		panel.add(label);
	}

	private void textFields(){
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(273, 140, 158, 50);
		textField.setColumns(10);
		panel.add(textField);
	}

	private void buttons(String nume,StocarePersoane persoane){

		JButton buttonE = new JButton("EXIT");
		buttonE.setBounds(10, 378, 110, 46);
		buttonE.addActionListener(e->{
			frameDN.dispose();
			JOptionPane.showMessageDialog(null,"La revedere","EXIT",JOptionPane.PLAIN_MESSAGE);
		});
		panel.add(buttonE);

		JButton buttonAltaTranzactie = new JButton("Alta tanzactie");
		buttonAltaTranzactie.setBounds(274, 378, 200, 46);
		buttonAltaTranzactie.addActionListener(e->{
			frameDN.dispose();
			new Meniu(nume,persoane);
		});
		panel.add(buttonAltaTranzactie);

		JButton buttonDepuneti = new JButton("Retrageti");
		buttonDepuneti.setFont(new Font("Tahoma", Font.PLAIN, 16));
		buttonDepuneti.setBounds(172, 214, 144, 61);
		buttonDepuneti.addActionListener(e->{
			// daca nu contine numai cifre suma introdusa
			if(!contineNumaiCifre(textField.getText()))
				JOptionPane.showMessageDialog(null,"Trebuie introduse numai cifre","ATENTIE!",JOptionPane.WARNING_MESSAGE);
			else{
				// daca suma ce se doreste a fi retrasa este mai mare decat soldul curent al username-ului
				if(persoane.getSuma(nume) < Integer.parseInt(textField.getText()))
					JOptionPane.showMessageDialog(null,"Suma pe care vreti sa o retrageti e mai mare decat cea din cont","ATENTIE!",JOptionPane.WARNING_MESSAGE);
				else{
					Client c = new Client(nume, persoane.getPin(nume), persoane.getSuma(nume)-Integer.parseInt(textField.getText()));
					persoane.actualizareClient(c);
					persoane.afisareFisier();
					JOptionPane.showMessageDialog(null,"Numerar retras","Gata",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		panel.add(buttonDepuneti);

	}

	private void initialize(String x, StocarePersoane persoane) {
		frameDN = new JFrame("Retragere numerar");
		frameDN.setBounds(1000, 200, 500, 500);
		frameDN.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		frameDN.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		labels();
		textFields();
		buttons(x,persoane);

		frameDN.setVisible(true);
	}

}
