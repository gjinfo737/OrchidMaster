package com.orchidmaster.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MainWindow extends JFrame {
    private JTextField textFieldUrlEntry;

    public MainWindow() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(601, 432);
	getContentPane().setLayout(null);

	textFieldUrlEntry = new JTextField();
	textFieldUrlEntry.setBounds(10, 11, 358, 20);
	getContentPane().add(textFieldUrlEntry);
	textFieldUrlEntry.setColumns(10);

	JComboBox comboBoxLinks = new JComboBox();
	comboBoxLinks.setBounds(10, 42, 358, 20);
	getContentPane().add(comboBoxLinks);

	JButton btnAdd = new JButton("Add");
	btnAdd.setBounds(378, 10, 96, 23);
	getContentPane().add(btnAdd);

	JButton btnGet = new JButton("Get");
	btnGet.setBounds(484, 11, 89, 53);
	getContentPane().add(btnGet);

	JTextPane textPane = new JTextPane();
	textPane.setBounds(10, 72, 563, 311);
	getContentPane().add(textPane);

	JButton btnRemove = new JButton("Remove");
	btnRemove.setBounds(378, 41, 96, 23);
	getContentPane().add(btnRemove);
    }
}
