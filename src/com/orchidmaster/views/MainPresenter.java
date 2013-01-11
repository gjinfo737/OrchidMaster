package com.orchidmaster.views;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.orchidmaster.http.HtmlParser;
import com.orchidmaster.http.HtmlParser.IHtmlParserListener;

public class MainPresenter implements ComboBoxModel<String>,
	IHtmlParserListener {
    private static HtmlParser htmlParser;
    private static final File DATA_FOLDER = new File("C:" + File.separator
	    + "OrchidMaster" + File.separator + "Data");
    private static final File DATA_File = new File(
	    DATA_FOLDER.getAbsolutePath() + File.separator + "data.txt");
    private MainWindow window;
    private List<String> urls;
    private int selectedIndex;

    public MainPresenter() {
	this.window = new MainWindow(this);
	setupMainDirectory();
	load();

	this.window.setVisible(true);
	htmlParser = new HtmlParser();
    }

    public void load() {
	urls = new ArrayList<String>();
	window.refresh();
    }

    public void onAddUrl() {
	String url = window.getUrlText();
	if (url == null)
	    return;
	if (url.length() == 0)
	    return;

	if (!urls.contains(url))
	    urls.add(url);
	window.refresh();
	save();
    }

    public void onGetSite() {
	String url = window.getCurrentSelectedUrl();
	if (url == null)
	    return;
	if (url.length() == 0)
	    return;

	htmlParser.parseSite(url, this);
	window.refresh();
    }

    public void onRemoveUrl() {
	String url = window.getCurrentSelectedUrl();
	if (url == null)
	    return;
	if (url.length() == 0)
	    return;
	urls.remove(url);
	window.refresh();
    }

    private void save() {
	// TODO Auto-generated method stub

    }

    private void setupMainDirectory() {
	if (!DATA_FOLDER.exists()) {
	    if (!DATA_FOLDER.mkdirs())
		System.err.println("failed to make directory "
			+ DATA_FOLDER.getAbsolutePath());
	}
	if (!DATA_File.exists())
	    try {
		DATA_File.createNewFile();
	    } catch (IOException e) {
		e.printStackTrace();
		System.err.println("failed to make file!");
	    }
    }

    @Override
    public void addListDataListener(ListDataListener arg0) {
    }

    @Override
    public String getElementAt(int index) {
	return urls.get(index);
    }

    @Override
    public int getSize() {
	return urls.size();
    }

    @Override
    public void removeListDataListener(ListDataListener arg0) {
    }

    @Override
    public String getSelectedItem() {
	try {
	    return urls.get(selectedIndex);
	} catch (NullPointerException e) {
	    e.printStackTrace();
	    return "";
	} catch (IndexOutOfBoundsException e) {
	    e.printStackTrace();
	    return "";
	}
    }

    @Override
    public void setSelectedItem(Object anItem) {
	selectedIndex = urls.indexOf(anItem);
    }

    @Override
    public void onParsed(String content) {
	window.setContent(content);

    }

    @Override
    public void onMalformedURLException(MalformedURLException e) {
	// TODO Auto-generated method stub

    }
}
