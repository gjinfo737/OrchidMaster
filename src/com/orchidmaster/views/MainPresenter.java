package com.orchidmaster.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    private static final File DATA_FOLDER = new File("C:" + File.separator
	    + "OrchidMaster" + File.separator + "Data");
    private static final File DATA_File = new File(
	    DATA_FOLDER.getAbsolutePath() + File.separator + "data.txt");
    private MainWindow window;
    private List<String> urls;

    public MainPresenter() {
	setupMainDirectory();
	loadUrls();
	this.window = new MainWindow(this);
	this.window.setUrls(urls);
	this.window.setVisible(true);
    }

    public void loadUrls() {
	urls = new ArrayList<String>();
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
}
