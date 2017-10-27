package me.niklas.rechner.gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import me.niklas.rechner.listener.CalculateListener;
import me.niklas.rechner.listener.CancelListener;
import me.niklas.rechner.listener.DeleteListener;
import me.niklas.rechner.listener.EditListener;
import me.niklas.rechner.listener.ReconnectListener;
import me.niklas.rechner.listener.SafeListener;

import javax.swing.ListSelectionModel;
import java.awt.Color;

@SuppressWarnings("serial")
public class RechnerGui extends JFrame {

	public JPanel contentPane;
	public JPanel mainPane;
	public JPanel editPane;
	public JPanel editMainPane;
	public JPanel errorPane;
	public JMenuItem mntmBearbeiten;
	public JMenuItem mntmRechnen;
	public JTextField txtAmount;
	public JTextField txtName;
	public JTextField txtEditAmount;
	@SuppressWarnings("rawtypes")
	public JList firstCurrency;
	@SuppressWarnings("rawtypes")
	public JList secondCurrency;
	@SuppressWarnings("rawtypes")
	public JList editList;
	public JLabel lblEditError;
	public JLabel lblEditState;
	public JLabel lblOutput;
	
	@SuppressWarnings("rawtypes")
	public RechnerGui() {
		setTitle("W\u00E4hrungsrecher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 838, 539);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		mntmRechnen = new JMenuItem("Rechnen");
		mntmRechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtName.setText("");
				txtEditAmount.setText("");
				lblEditError.setText("");
				lblEditState.setText("");
				
				WindowManager.setWindow(Window.MAIN);
				mntmRechnen.setSelected(true);
				mntmBearbeiten.setSelected(false);
			}
		});
		menuBar.add(mntmRechnen);
		
		mntmBearbeiten = new JMenuItem("Bearbeiten");
		mntmBearbeiten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtName.setText("");
				txtEditAmount.setText("");
				lblEditError.setText("");
				lblEditState.setText("");
				
				WindowManager.setWindow(Window.EDITMAIN);
				mntmRechnen.setSelected(false);
				mntmBearbeiten.setSelected(true);
			}
		});
		menuBar.add(mntmBearbeiten);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		mainPane = new JPanel();
		contentPane.add(mainPane, "name_1075669868890975");
		mainPane.setLayout(null);
		
		firstCurrency = new JList();
		firstCurrency.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		firstCurrency.setBounds(260, 149, 281, 69);
		mainPane.add(firstCurrency);
		
		secondCurrency = new JList();
		secondCurrency.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		secondCurrency.setBounds(260, 248, 281, 69);
		mainPane.add(secondCurrency);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(260, 78, 281, 44);
		mainPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		JLabel lblAmount = new JLabel("Betrag:");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAmount.setBounds(99, 84, 100, 30);
		mainPane.add(lblAmount);
		
		JLabel lblFirstCurrency = new JLabel("W\u00E4hrung 1:");
		lblFirstCurrency.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFirstCurrency.setBounds(99, 150, 89, 30);
		mainPane.add(lblFirstCurrency);
		
		JLabel lblSecondCurrency = new JLabel("W\u00E4hrung 2:");
		lblSecondCurrency.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSecondCurrency.setBounds(99, 261, 100, 24);
		mainPane.add(lblSecondCurrency);
		
		JButton btnRechnen = new JButton("Rechnen");
		btnRechnen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalculateListener.calculate();
			}
		});
		btnRechnen.setBounds(91, 407, 97, 25);
		mainPane.add(btnRechnen);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(NORMAL);;
			}
		});
		btnExit.setBounds(686, 407, 97, 25);
		mainPane.add(btnExit);
		
		JLabel lblStand1 = new JLabel("");
		lblStand1.setBounds(572, 78, 126, 16);
		mainPane.add(lblStand1);
		
		JLabel lblStand2 = new JLabel("");
		lblStand2.setBounds(572, 106, 126, 16);
		mainPane.add(lblStand2);
		
		lblOutput = new JLabel("");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOutput.setBounds(99, 359, 583, 30);
		mainPane.add(lblOutput);
		
		editPane = new JPanel();
		contentPane.add(editPane, "name_1164041675900729");
		editPane.setLayout(null);
		
		editList = new JList();
		editList.setBounds(215, 147, 317, 100);
		editPane.add(editList);
		
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(NORMAL);
			}
		});
		btnExit_1.setBounds(664, 406, 97, 25);
		editPane.add(btnExit_1);
		
		JButton btnEdit = new JButton("Bearbeiten");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EditListener.edit();
				
				WindowManager.setWindow(Window.EDIT);
			}
		});
		btnEdit.setBounds(68, 406, 97, 25);
		editPane.add(btnEdit);
		
		JButton btnAdd = new JButton("Hinzuf\u00FCgen");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblEditState.setText("Neue Währung hinzufügen");				
				WindowManager.setWindow(Window.EDIT);
			}
		});
		btnAdd.setBounds(189, 406, 97, 25);
		editPane.add(btnAdd);
		
		JButton btnLschen = new JButton("L\u00F6schen");
		btnLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteListener.delete();
			}
		});
		btnLschen.setBounds(308, 407, 89, 23);
		editPane.add(btnLschen);
		
		editMainPane = new JPanel();
		contentPane.add(editMainPane, "name_1165768060986940");
		editMainPane.setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(187, 116, 425, 34);
		editMainPane.add(txtName);
		txtName.setColumns(10);
		
		txtEditAmount = new JTextField();
		txtEditAmount.setBounds(187, 176, 425, 34);
		editMainPane.add(txtEditAmount);
		txtEditAmount.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblName.setBounds(54, 111, 136, 39);
		editMainPane.add(lblName);
		
		JLabel lblBetrag = new JLabel("Betrag:");
		lblBetrag.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblBetrag.setBounds(54, 176, 86, 25);
		editMainPane.add(lblBetrag);
		
		JButton btnSave = new JButton("Speichern");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SafeListener.save();
			}
		});
		btnSave.setBounds(54, 394, 97, 25);
		editMainPane.add(btnSave);
		
		JButton btnCancel = new JButton("Abbrechen");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CancelListener.cancel();
			}
		});
		btnCancel.setBounds(187, 394, 97, 25);
		editMainPane.add(btnCancel);
		
		JButton btnExit_2 = new JButton("Exit");
		btnExit_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(NORMAL);
			}
		});
		btnExit_2.setBounds(659, 394, 97, 25);
		editMainPane.add(btnExit_2);
		
		JLabel lblAmountHelp = new JLabel("Bitte verwende den Betrag, den 1 Dollar in dieser W\u00E4hrung wert ist");
		lblAmountHelp.setBounds(187, 223, 425, 16);
		editMainPane.add(lblAmountHelp);
		
		lblEditError = new JLabel("");
		lblEditError.setForeground(Color.RED);
		lblEditError.setBounds(187, 280, 425, 14);
		editMainPane.add(lblEditError);
		
		lblEditState = new JLabel("");
		lblEditState.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEditState.setBounds(54, 51, 689, 34);
		editMainPane.add(lblEditState);
		
		errorPane = new JPanel();
		contentPane.add(errorPane, "name_9126908101736120");
		errorPane.setLayout(null);
		
		JLabel lblEsKonnteKeine = new JLabel("Es konnte keine Verbindung zur Datenbank aufgebaut werden.");
		lblEsKonnteKeine.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEsKonnteKeine.setBounds(112, 102, 563, 248);
		errorPane.add(lblEsKonnteKeine);
		
		JButton btnReconnect = new JButton("Reconnect");
		btnReconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReconnectListener.reconnect();
			}
		});
		btnReconnect.setBounds(40, 410, 115, 23);
		errorPane.add(btnReconnect);
		
		JButton btnExit_3 = new JButton("Exit");
		btnExit_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(NORMAL);
			}
		});
		btnExit_3.setBounds(669, 410, 89, 23);
		errorPane.add(btnExit_3);
	}
}
