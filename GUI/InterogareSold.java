package GUI;

import IDE.StocarePersoane;
import javax.swing.*;
import java.awt.*;

public class InterogareSold {

	private JFrame frameIS;
	private JPanel panel;

	public InterogareSold(String nume,int x,StocarePersoane persoane) {
		initialize(nume,x,persoane);
	}

	private void labels(int x) {
		JLabel label = new JLabel("Suma din cont este de " + x + " lei");
		label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(88, 66, 295, 221);
		panel.add(label);

	}
	
	private void buttons(String nume, StocarePersoane persoane) {

		// buton de exit
		JButton buttonAT = new JButton("EXIT");
		buttonAT.setBounds(10, 378, 110, 46);
		buttonAT.addActionListener(e->{
			frameIS.dispose();
			JOptionPane.showMessageDialog(null,"La revedere","EXIT",JOptionPane.PLAIN_MESSAGE);
		});
		panel.add(buttonAT);

		// buton ce te duce inapoi la meniu
		JButton buttonAltaTranzactie = new JButton("Alta tanzactie");
		buttonAltaTranzactie.setBounds(274, 378, 200, 46);
		buttonAltaTranzactie.addActionListener(e->{
			frameIS.dispose();
			new Meniu(nume,persoane);
		});
		panel.add(buttonAltaTranzactie);
	}

	private void initialize(String nume, int x,StocarePersoane persoane) {
		frameIS = new JFrame("Interogare Sold");
		frameIS.setBounds(1000, 200, 500, 500);
		frameIS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frameIS.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		labels(x);
		buttons(nume,persoane);
		
		frameIS.setVisible(true);
	}

}
