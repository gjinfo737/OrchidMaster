package com.orchidmaster.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainWindow extends JFrame {
    private JTextField textFieldUrlEntry;
    private JComboBox<String> comboBoxLinks;
    private MainPresenter presenter;
    private JTextArea textAreaContent;

    public MainWindow(MainPresenter _presenter) {
	this.presenter = _presenter;
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(601, 432);
	getContentPane().setLayout(
		new FormLayout(new ColumnSpec[] {
			FormFactory.UNRELATED_GAP_COLSPEC,
			ColumnSpec.decode("358px:grow"),
			FormFactory.UNRELATED_GAP_COLSPEC,
			ColumnSpec.decode("96px"),
			FormFactory.UNRELATED_GAP_COLSPEC,
			ColumnSpec.decode("89px"), }, new RowSpec[] {
			FormFactory.UNRELATED_GAP_ROWSPEC,
			RowSpec.decode("23px"),
			FormFactory.RELATED_GAP_ROWSPEC,
			RowSpec.decode("23px"),
			FormFactory.RELATED_GAP_ROWSPEC,
			RowSpec.decode("311px:grow"), }));

	textFieldUrlEntry = new JTextField();
	getContentPane().add(textFieldUrlEntry, "2, 2, fill, center");
	textFieldUrlEntry.setColumns(10);

	comboBoxLinks = new JComboBox();

	getContentPane().add(comboBoxLinks, "2, 4, fill, center");

	JButton btnAdd = new JButton("Add");
	btnAdd.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		presenter.onAddUrl();
	    }
	});
	getContentPane().add(btnAdd, "4, 2, fill, top");

	JButton btnGet = new JButton("Get");
	btnGet.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		presenter.onGetSite();
	    }
	});
	getContentPane().add(btnGet, "6, 2, 1, 3, fill, fill");

	JButton btnRemove = new JButton("Remove");
	btnRemove.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		presenter.onRemoveUrl();
	    }
	});
	getContentPane().add(btnRemove, "4, 4, fill, top");
	comboBoxLinks.setModel(this.presenter);

	textAreaContent = new JTextArea();
	getContentPane().add(textAreaContent, "2, 6, 5, 1, fill, fill");
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
	textAreaContent.setText(content);
    }
}
