package GUI;

import IDE.StocarePersoane;
import javax.swing.*;
import java.awt.*;

public class Meniu {

	private JFrame frameM;
	
	private JPanel panel;

	public Meniu(String x,StocarePersoane persoane) {
		initialize(x,persoane);
	}

	private void buttons(String x, StocarePersoane persoane){
		// buton ce te duce in frame-ul pentru interogare sold
		JButton buttonIS = new JButton("Interogare Sold");
		buttonIS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonIS.setBounds(10, 104, 170, 57);
		buttonIS.setFocusable(false);
		buttonIS.addActionListener(e->{
			frameM.dispose();
			new InterogareSold(x,persoane.getSuma(x),persoane);
		});
		panel.add(buttonIS);

		// buton ce te duce in frame-ul pentru schimbare PIN
		JButton buttonSP = new JButton("Schimbare PIN");
		buttonSP.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonSP.setBounds(10, 251, 170, 57);
		buttonSP.setFocusable(false);
		buttonSP.addActionListener(e->{
			frameM.dispose();
			new SchimbarePIN(x,persoane);
		});
		panel.add(buttonSP);

		// buton ce te duce in frame-ul pentru depunere numerar
		JButton buttonDN = new JButton("Depunere numerar");
		buttonDN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonDN.setBounds(10, 393, 170, 57);
		buttonDN.setFocusable(false);
		buttonDN.addActionListener(e->{
			frameM.dispose();
			new DepunereNumerar(x,persoane);
		});
		panel.add(buttonDN);

		// buton ce te duce in frame-ul pentru retragere numerar
		JButton buttonRN = new JButton("Retragere numerar");
		buttonRN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonRN.setBounds(304, 104, 170, 57);
		buttonRN.setFocusable(false);
		buttonRN.addActionListener(e -> {
			frameM.dispose();
			new RetragereNumerar(x,persoane);
		});
		panel.add(buttonRN);

		// buton ce te duce in frame-ul pentru transfer numerar
		JButton buttonTN = new JButton("Transfer numerar");
		buttonTN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonTN.setBounds(304, 251, 170, 57);
		buttonTN.setFocusable(false);
		buttonTN.addActionListener(e->{
			frameM.dispose();
			new TransferNumerar(x,persoane);
		});
		panel.add(buttonTN);

		// buton de exit
		JButton buttonE = new JButton("Exit");
		buttonE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonE.setBounds(304, 393, 170, 57);
		buttonE.setFocusable(false);
		buttonE.addActionListener(e->{
			frameM.dispose();
			JOptionPane.showMessageDialog(null,"La revedere","EXIT",JOptionPane.PLAIN_MESSAGE);
		});
		panel.add(buttonE);
	}

	private void labels(JPanel panel, String x) {
		JLabel label = new JLabel("Bine ai venit " + x);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 464, 82);
		panel.add(label);
	}
	
	private void initialize(String x,StocarePersoane persoane) {
		frameM = new JFrame("Meniu");
		frameM.setBounds(1000, 200, 500, 500);
		frameM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		frameM.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		buttons(x,persoane);
		labels(panel,x);

		frameM.setVisible(true);
	}
}
