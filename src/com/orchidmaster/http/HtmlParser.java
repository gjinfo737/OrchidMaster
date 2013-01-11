package com.orchidmaster.http;

import java.net.MalformedURLException;
import java.net.URL;

public class HtmlParser implements IHttpResponseListener {

    public static final String HTTP_COL_SLASHSLASH = "http://";

    public interface IHtmlParserListener {
	public void onParsed(String content);

	public void onMalformedURLException(MalformedURLException e);

    }

    private IHtmlParserListener htmlParserListener;

    public void parseSite(String url, IHtmlParserListener htmlParserListener) {
	this.htmlParserListener = htmlParserListener;
	final WebRequestCodeSample webRequestCodeSample = new WebRequestCodeSample();

	try {
	    webRequestCodeSample.getData(new URL(url), this);
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	    htmlParserListener.onMalformedURLException(e);
	}

    }

    @Override
    public void onResponse(String content) {
	System.out.println(content.length() + " !!!");
	htmlParserListener.onParsed(content);
    }

    @Override
    public void onException(Exception e) {
	System.err.println("ERROR 938409!!!");
    }
}
