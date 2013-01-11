package com.orchidmaster.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import com.google.gson.Gson;
import com.orchidmaster.http.HtmlParser;
import com.orchidmaster.http.HtmlParser.IHtmlParserListener;

public class MainPresenter implements ComboBoxModel<String>,
	IHtmlParserListener {
    private static HtmlParser htmlParser;
    private static File DATA_FOLDER;
    private static File DATA_FILE;
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

	String fileContents = new Gson().toJson(urls);
	try {
	    InputStream stream = new FileInputStream(DATA_FILE);
	    byte[] buffer = new byte[(int) DATA_FILE.length()];
	    stream.read(buffer);

	    fileContents = new String(buffer);
	    urls = new Gson().fromJson(fileContents, List.class);
	    if (urls == null) {
		urls = new ArrayList<String>();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}

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
	save();
	window.refresh();
    }

    private void save() {
	String fileContents = new Gson().toJson(urls);
	try {
	    OutputStream streamOut = new FileOutputStream(DATA_FILE);
	    streamOut.write(fileContents.getBytes());
	    streamOut.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void setupMainDirectory() {
	String driveLetter = "c";
	File[] roots = File.listRoots();
	boolean found = false;
	for (int i = 0; i < roots.length; i++) {
	    if (roots[i].getAbsolutePath().substring(0, 1).toLowerCase()
		    .equals(driveLetter))
		found = true;
	}
	driveLetter = found ? driveLetter : roots[0].getAbsolutePath()
		.substring(0, 1).toLowerCase();

	DATA_FOLDER = new File(driveLetter + ":" + File.separator
		+ "OrchidMaster" + File.separator + "Data");

	if (!DATA_FOLDER.exists()) {

	    if (!DATA_FOLDER.mkdirs())
		System.err.println("failed to make directory "
			+ DATA_FOLDER.getAbsolutePath());
	}

	DATA_FILE = new File(DATA_FOLDER.getAbsolutePath() + File.separator
		+ "data.txt");
	if (!DATA_FILE.exists())
	    try {
		DATA_FILE.createNewFile();
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
	window.setContent("Bad URL");
    }
}
