package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import controllo.TextValidatore;
import primalita.SolovayStrassen;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.CardLayout;
public class UI {

	private JFrame frmCr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmCr.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCr = new JFrame();
		frmCr.setIconImage(Toolkit.getDefaultToolkit().getImage(UI.class.getResource("/ui/icona.png")));
		frmCr.setTitle("CR410");
		frmCr.setBounds(100, 100, 648, 396);
		frmCr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCr.getContentPane().setLayout(new CardLayout(0, 0));
		
		//inizializzazione pannelli
		JPanel SolovayStrassen = new JPanel();
		JPanel MainMenuPanel = new JPanel();
		
		//inizializzazione pannello del menù principale
		frmCr.getContentPane().add(MainMenuPanel, "name_670921225034624");
		MainMenuPanel.setLayout(null);

		
		JLabel lblTestEMetodi = new JLabel("Test e metodi per la crittografia a chiave pubblica");
		lblTestEMetodi.setBounds(10, 0, 612, 24);
		MainMenuPanel.add(lblTestEMetodi);
		lblTestEMetodi.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestEMetodi.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		
		JTextArea txtrQuestaApplicazione = new JTextArea();
		txtrQuestaApplicazione.setBounds(10, 91, 234, 185);
		MainMenuPanel.add(txtrQuestaApplicazione);
		txtrQuestaApplicazione.setWrapStyleWord(true);
		txtrQuestaApplicazione.setFont(new Font("Lucida Bright", Font.PLAIN, 13));
		txtrQuestaApplicazione.setLineWrap(true);
		txtrQuestaApplicazione.setText("Questa applicazione \u00E8 stata scritta e costruita al fine di compiere nella pratica metodi di fattorizzazione e test di primalit\u00E0 su numeri interi positivi. \r\nTuttavia, tenendo conto delle limitazioni hardware, alcuni di questi sopracitati, in casi particolari, possono impiegare diversi secondi prima di compiere il loro lavoro. ");
		txtrQuestaApplicazione.setBackground(SystemColor.menu);
		txtrQuestaApplicazione.setEditable(false);
		
		JLabel lblInformazioniDiSistema = new JLabel("Informazioni di sistema:");
		lblInformazioniDiSistema.setBounds(10, 68, 244, 19);
		MainMenuPanel.add(lblInformazioniDiSistema);
		lblInformazioniDiSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformazioniDiSistema.setFont(new Font("Lucida Bright", Font.PLAIN, 17));
		
		JLabel lblOperazioniDisponibili = new JLabel("Operazioni disponibili");
		lblOperazioniDisponibili.setBounds(271, 57, 351, 27);
		MainMenuPanel.add(lblOperazioniDisponibili);
		lblOperazioniDisponibili.setHorizontalAlignment(SwingConstants.CENTER);
		lblOperazioniDisponibili.setFont(new Font("Lucida Bright", Font.PLAIN, 17));
		
		JLabel lblTestDiPrimalit = new JLabel("Test di primalit\u00E0:");
		lblTestDiPrimalit.setBounds(306, 91, 316, 14);
		MainMenuPanel.add(lblTestDiPrimalit);
		lblTestDiPrimalit.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		
		JButton btnSolovaystrassen = new JButton("Solovay-Strassen");
		btnSolovaystrassen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenuPanel.setEnabled(false);
				MainMenuPanel.setVisible(false);
				SolovayStrassen.setEnabled(true);
				SolovayStrassen.setVisible(true);
			}
		});
		btnSolovaystrassen.setBounds(318, 112, 139, 23);
		MainMenuPanel.add(btnSolovaystrassen);
		
		JButton btnMillerrabin = new JButton("Miller-Rabin");
		btnMillerrabin.setBounds(464, 112, 139, 23);
		MainMenuPanel.add(btnMillerrabin);
		
		JLabel lblFattorizzazione = new JLabel("Fattorizzazione:");
		lblFattorizzazione.setBounds(306, 154, 316, 14);
		MainMenuPanel.add(lblFattorizzazione);
		lblFattorizzazione.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		
		JButton metodopmenouno = new JButton("p-1");
		metodopmenouno.setBounds(318, 175, 139, 23);
		MainMenuPanel.add(metodopmenouno);
		
		//pannello per test di solovay-strassen
		frmCr.getContentPane().add(SolovayStrassen, "name_670921231410741");
		SolovayStrassen.setLayout(null);
		metodopmenouno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNumeroDaTestare = new JLabel("Numero test");
		lblNumeroDaTestare.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		lblNumeroDaTestare.setBounds(10, 50, 90, 14);
		SolovayStrassen.add(lblNumeroDaTestare);
		
		JTextField textField_numeroTest = new JTextField();
		textField_numeroTest.setBounds(103, 47, 86, 20);
		SolovayStrassen.add(textField_numeroTest);
		textField_numeroTest.setColumns(10);
		
		JLabel lblNumeroDiPassi = new JLabel("Numero passi");
		lblNumeroDiPassi.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		lblNumeroDiPassi.setBounds(10, 79, 86, 14);
		SolovayStrassen.add(lblNumeroDiPassi);
		
		JTextField textField_numeroPassi = new JTextField();
		textField_numeroPassi.setColumns(10);
		textField_numeroPassi.setBounds(103, 75, 86, 20);
		SolovayStrassen.add(textField_numeroPassi);
		
		JButton btnEseguiTest = new JButton("Esegui");
		btnEseguiTest.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		btnEseguiTest.setBounds(49, 104, 89, 23);
		SolovayStrassen.add(btnEseguiTest);
		
		JTextArea textField_risultatoTestuale = new JTextArea();
		textField_risultatoTestuale.setEditable(false);
		textField_risultatoTestuale.setBounds(199, 9, 351, 336);
		
		SolovayStrassen.add(textField_risultatoTestuale);
		
		btnEseguiTest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				TextValidatore validator = new TextValidatore(textField_numeroTest.getText(), textField_numeroPassi.getText());
				boolean canGoOn = validator.validate();
				if(canGoOn){
					SolovayStrassen test = new SolovayStrassen(Integer.valueOf(textField_numeroPassi.getText()));
					test.setNumeroTest(new BigInteger(textField_numeroTest.getText()));
					test.makeTest();
					
					textField_risultatoTestuale.setText(test.getRisultatoTestuale());
				}
				else{
					if(validator.controlEmpty()){
						textField_risultatoTestuale.setText("Numeri mancanti.");
					}
					else if(!validator.validateLimits()){
						textField_risultatoTestuale.setText("Il numero di passi non può essere maggior del numero da testare.");
					}
					else{
						textField_risultatoTestuale.setText("Numeri non accettabili.");
					}
				}
			}
		});
		
		JScrollPane scroll = new JScrollPane(textField_risultatoTestuale);
		SolovayStrassen.add(scroll);
		scroll.setBounds(199, 9, 424, 336);
		
		JLabel lblTestDiSolovaystrassen = new JLabel("Test di Solovay-Strassen");
		lblTestDiSolovaystrassen.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		lblTestDiSolovaystrassen.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestDiSolovaystrassen.setBounds(10, 15, 179, 14);
		SolovayStrassen.add(lblTestDiSolovaystrassen);
		
		JButton btnTornaAlMen = new JButton("Torna al men\u00F9");
		btnTornaAlMen.setFont(new Font("Lucida Bright", Font.PLAIN, 11));
		btnTornaAlMen.setBounds(37, 311, 115, 23);
		SolovayStrassen.add(btnTornaAlMen);
		btnTornaAlMen.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				SolovayStrassen.setVisible(false);
				SolovayStrassen.setEnabled(false);
				MainMenuPanel.setVisible(true);
				MainMenuPanel.setEnabled(true);	
			}
		});
	}
}
