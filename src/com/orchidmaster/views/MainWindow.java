package com.orchidmaster.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MainWindow extends JFrame {
    private JTextField textFieldUrlEntry;
    private JComboBox<String> comboBoxLinks;
    private MainPresenter presenter;
    private JTextPane textPane;

    public MainWindow(MainPresenter _presenter) {
	this.presenter = _presenter;
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(601, 432);
	getContentPane().setLayout(null);

	textFieldUrlEntry = new JTextField();
	textFieldUrlEntry.setBounds(10, 11, 358, 20);
	getContentPane().add(textFieldUrlEntry);
	textFieldUrlEntry.setColumns(10);

	comboBoxLinks = new JComboBox();
	comboBoxLinks.setBounds(10, 42, 358, 20);

	getContentPane().add(comboBoxLinks);

	JButton btnAdd = new JButton("Add");
	btnAdd.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		presenter.onAddUrl();
	    }
	});
	btnAdd.setBounds(378, 10, 96, 23);
	getContentPane().add(btnAdd);

	JButton btnGet = new JButton("Get");
	btnGet.setBounds(484, 11, 89, 53);
	btnGet.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		presenter.onGetSite();
	    }
	});
	getContentPane().add(btnGet);

	textPane = new JTextPane();
	textPane.setBounds(10, 72, 563, 311);
	getContentPane().add(textPane);

	JButton btnRemove = new JButton("Remove");
	btnRemove.setBounds(378, 41, 96, 23);
	btnRemove.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		presenter.onRemoveUrl();
	    }
	});
	getContentPane().add(btnRemove);
	comboBoxLinks.setModel(this.presenter);
	comboBoxLinks.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		presenter.setSelectedItem(comboBoxLinks.getSelectedItem());
	    }
	});
    }

    public String getUrlText() {
	return textFieldUrlEntry.getText().toLowerCase().trim();
    }

    public String getCurrentSelectedUrl() {
	return (String) comboBoxLinks.getSelectedItem();
    }

    public void refresh() {
	update(getGraphics());
    }

    public void setContent(String content) {
	textPane.setText(content);
    }

}
