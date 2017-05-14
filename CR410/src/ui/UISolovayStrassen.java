package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UISolovayStrassen {

	private JFrame frmTestSolovaystrassen;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		frmTestSolovaystrassen.setBounds(100, 100, 576, 395);
		frmTestSolovaystrassen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTestSolovaystrassen.getContentPane().setLayout(null);
		
		JLabel lblNumeroDaTestare = new JLabel("Numero test");
		lblNumeroDaTestare.setBounds(10, 14, 90, 14);
		frmTestSolovaystrassen.getContentPane().add(lblNumeroDaTestare);
		
		textField = new JTextField();
		textField.setBounds(103, 11, 86, 20);
		frmTestSolovaystrassen.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNumeroDiPassi = new JLabel("Numero passi");
		lblNumeroDiPassi.setBounds(10, 43, 86, 14);
		frmTestSolovaystrassen.getContentPane().add(lblNumeroDiPassi);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(103, 39, 86, 20);
		frmTestSolovaystrassen.getContentPane().add(textField_1);
		
		JButton btnEseguiTest = new JButton("Esegui");
		btnEseguiTest.setBounds(49, 68, 89, 23);
		frmTestSolovaystrassen.getContentPane().add(btnEseguiTest);
		btnEseguiTest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(204, 11, 346, 334);
		frmTestSolovaystrassen.getContentPane().add(textField_2);
		textField_2.setColumns(10);
	}
}
