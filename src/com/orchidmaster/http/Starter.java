package com.orchidmaster.http;

import com.orchidmaster.views.MainPresenter;

public class Starter {

    private static HtmlParser htmlParser;
    private static MainPresenter mainPresenter;

    public static void main(String[] args) throws Exception {
	htmlParser = new HtmlParser();

	// htmlParser.parseSite("http://www.orchidspecies.com/indexa-anat.htm");''

	mainPresenter = new MainPresenter();
    }
}
