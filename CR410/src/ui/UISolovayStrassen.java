package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controllo.TextValidatore;
import primalita.SolovayStrassen;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class UISolovayStrassen {

	private JFrame frmTestSolovaystrassen;
	private JTextField textField_numeroTest;
	private JTextField textField_numeroPassi;
	private JTextArea textField_risultatoTestuale;
	private JScrollPane scroll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UISolovayStrassen window = new UISolovayStrassen();
					window.frmTestSolovaystrassen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UISolovayStrassen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTestSolovaystrassen = new JFrame();
		frmTestSolovaystrassen.setTitle("test Solovay-Strassen");
		frmTestSolovaystrassen.setBounds(100, 100, 649, 395);
		frmTestSolovaystrassen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTestSolovaystrassen.getContentPane().setLayout(null);
		
		JLabel lblNumeroDaTestare = new JLabel("Numero test");
		lblNumeroDaTestare.setBounds(10, 14, 90, 14);
		frmTestSolovaystrassen.getContentPane().add(lblNumeroDaTestare);
		
		textField_numeroTest = new JTextField();
		textField_numeroTest.setBounds(103, 11, 86, 20);
		frmTestSolovaystrassen.getContentPane().add(textField_numeroTest);
		textField_numeroTest.setColumns(10);
		
		JLabel lblNumeroDiPassi = new JLabel("Numero passi");
		lblNumeroDiPassi.setBounds(10, 43, 86, 14);
		frmTestSolovaystrassen.getContentPane().add(lblNumeroDiPassi);
		
		textField_numeroPassi = new JTextField();
		textField_numeroPassi.setColumns(10);
		textField_numeroPassi.setBounds(103, 39, 86, 20);
		frmTestSolovaystrassen.getContentPane().add(textField_numeroPassi);
		
		JButton btnEseguiTest = new JButton("Esegui");
		btnEseguiTest.setBounds(49, 68, 89, 23);
		frmTestSolovaystrassen.getContentPane().add(btnEseguiTest);
		
		textField_risultatoTestuale = new JTextArea();
		textField_risultatoTestuale.setEditable(false);
		textField_risultatoTestuale.setBounds(199, 9, 351, 336);
		
		frmTestSolovaystrassen.getContentPane().add(textField_risultatoTestuale);
		
		btnEseguiTest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				TextValidatore validator = new TextValidatore(textField_numeroTest.getText(), textField_numeroPassi.getText());
				if(validator.validate()){
					SolovayStrassen test = new SolovayStrassen(Integer.valueOf(textField_numeroPassi.getText()));
					test.setNumeroTest(new BigInteger(textField_numeroTest.getText()));
					test.makeTest();
					
					textField_risultatoTestuale.setText(test.getRisultatoTestuale());
				}
				else{
					
					textField_risultatoTestuale.setText("Numeri non accettabili.");
				}
			}
		});
		
		scroll = new JScrollPane(textField_risultatoTestuale);
		frmTestSolovaystrassen.getContentPane().add(scroll);
		scroll.setBounds(199, 9, 424, 336);
	}
}
