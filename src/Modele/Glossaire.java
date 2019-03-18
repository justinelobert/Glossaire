package Modele;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import Contrôle.Controler;

import java.awt.FlowLayout;
import javax.swing.JTextPane;

public class Glossaire {

	private JFrame frame;
	private JTextField textFieldMot;
	private JTable table;
	private JTextField textFieldModif;
	private JTextField textField_4;
	private JTextField textField_5;
	DefaultTableModel modeltableau;
	private JTextPane txtpnSalut;
	private JTextPane textPaneDef = new JTextPane();

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {

		Modele.startConnection();


		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Glossaire window = new Glossaire();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Glossaire() {
		initialize();
		modeltableau= (DefaultTableModel) table.getModel();
		Controler.actualiseListe(modeltableau);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.NORTH);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane_1, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		tabbedPane_1.addTab("Create\n\n", null, panel, null);
		panel.setLayout(null);

		JLabel lblVeuillezEntrerVotre = new JLabel("Veuillez entrer votre mot :");
		lblVeuillezEntrerVotre.setBounds(131, 31, 182, 15);
		panel.add(lblVeuillezEntrerVotre);

		textFieldMot = new JTextField();
		textFieldMot.setBackground(new Color(255, 255, 255));
		textFieldMot.setBounds(160, 49, 124, 19);
		panel.add(textFieldMot);
		textFieldMot.setColumns(10);

		JLabel lblVeuillezEntrerVotre_1 = new JLabel("Définition :");
		lblVeuillezEntrerVotre_1.setBounds(184, 88, 76, 15);
		panel.add(lblVeuillezEntrerVotre_1);

		JButton btnNewButton = new JButton("Insérer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String mot= textFieldMot.getText();
				String def= textPaneDef.getText();
				boolean recup= ModeleDonnee.ajouteLexique(mot, def);
				if (recup == true ) {
					JOptionPane.showMessageDialog(frame, "Succés de l'insertion ! ","Félicitations", JOptionPane.DEFAULT_OPTION);
				
				}else {
					JOptionPane.showMessageDialog(frame, "Erreur d'insertion !", "Attention !", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(165, 199, 114, 25);
		panel.add(btnNewButton);


		textPaneDef.setBackground(new Color(255, 255, 255));
		textPaneDef.setBounds(37, 109, 355, 74);
		panel.add(textPaneDef);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		tabbedPane_1.addTab("Read", null, panel_1, null);
		Object[][] donnees = {{}};
		String[] entete = {"Id", "Mot", "Definition"};
		panel_1.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 12, 210, 119);
		panel_1.add(panel_4);
		table = new JTable(donnees,entete);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				//	System.out.print(i+" = ");
				int id = (int) table.getValueAt(i, 0);
				System.out.println(id);
				String s=ModeleDonnee.afficheDescrib(id);
				txtpnSalut.setText(s);
			}
		});

		panel_4.add(table.getTableHeader());
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_4.add(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{new Integer(1), ""},
					{"", ""},
					{"", null},
					{null, null},
					{null, null},
					{null, null},
				},
				new String[] {
						"Id", "Mot"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});


		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeltableau.setRowCount(0);

				ArrayList<Object[]> rep;

				if (textField_5.getText().equals("")) {
					rep = ModeleDonnee.afficheLexiques();


				}else {
					rep = ModeleDonnee.afficheLexiquesLike(textField_5.getText());
				}

				for ( Object[] local : rep) {
					modeltableau.addRow(local);
				}
			}
		});

		btnValider.setBounds(165, 199, 114, 25);
		panel_1.add(btnValider);

		JLabel lblVeuillezSaisirUn = new JLabel("Veuillez saisir un mot :");
		lblVeuillezSaisirUn.setBounds(141, 143, 162, 15);
		panel_1.add(lblVeuillezSaisirUn);

		textField_5 = new JTextField();
		textField_5.setBackground(new Color(255, 255, 255));
		textField_5.setBounds(160, 168, 124, 19);
		panel_1.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblDfinition_1 = new JLabel("Définition :");
		lblDfinition_1.setBounds(287, 23, 92, 15);
		panel_1.add(lblDfinition_1);

		txtpnSalut = new JTextPane();
		txtpnSalut.setEditable(false);
		txtpnSalut.setBounds(306, 68, 127, 142);
		panel_1.add(txtpnSalut);
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("Update", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblMotModifier = new JLabel("Mot à modifier :");
		lblMotModifier.setBounds(12, 27, 117, 15);
		panel_2.add(lblMotModifier);

		textFieldModif = new JTextField();
		textFieldModif.setBackground(new Color(255, 255, 255));
		textFieldModif.setBounds(133, 25, 124, 19);
		panel_2.add(textFieldModif);
		textFieldModif.setColumns(10);

		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.setBounds(302, 22, 114, 25);
		panel_2.add(btnRecherche);

		JLabel lblDfinition = new JLabel("Définition :");
		lblDfinition.setBounds(22, 80, 87, 15);
		panel_2.add(lblDfinition);



		JTextPane textPaneDef2 = new JTextPane();
		textPaneDef2.setBackground(new Color(255, 255, 255));
		textPaneDef2.setBounds(58, 107, 320, 117);
		panel_2.add(textPaneDef2);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(230, 230, 250));
		tabbedPane_1.addTab("Delete", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblMotSupprimer = new JLabel("Mot à supprimer :");
		lblMotSupprimer.setBounds(12, 48, 137, 15);
		panel_3.add(lblMotSupprimer);

		textField_4 = new JTextField();
		textField_4.setBackground(new Color(255, 255, 255));
		textField_4.setBounds(153, 46, 124, 19);
		panel_3.add(textField_4);
		textField_4.setColumns(10);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(306, 43, 114, 25);
		panel_3.add(btnSupprimer);
	}
}
