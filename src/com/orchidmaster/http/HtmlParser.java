package com.orchidmaster.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.orchidmaster.tagobj.Link;

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
    public void onResponse(String content, String parentURL) {

	String lwrCaseContent = content.toLowerCase().trim();
	int index = lwrCaseContent.indexOf("Type species".toLowerCase());
	String parsedContent = "";
	List<Link> links = parseLinks(parentURL, lwrCaseContent, index);

	for (Link l : links) {
	    parsedContent += "\n" + l.getLinkContent() + "[" + l.getUrl() + "]";
	}

	htmlParserListener.onParsed(parsedContent);
    }

    public List<Link> parseLinks(String parentURL, String lwrCaseContent,
	    int index) {
	List<Integer> indicesOfOccurence = new ArrayList<Integer>();
	// getIndicesOfOccurence(indicesOfOccurence, lwrCaseContent, "img src",
	// index);
	getIndicesOfOccurence(indicesOfOccurence, lwrCaseContent, "a href",
		index);

	List<Link> links = new ArrayList<Link>();
	for (Integer I : indicesOfOccurence) {

	    int end = "img src".length() + I + 1;
	    if (end < lwrCaseContent.length() && I != -1) {
		String substring = lwrCaseContent.substring(I);

		//
		parseLink(substring, links, parentURL);
		//

	    }
	}
	return links;
    }

    private void parseLink(String lwrCaseContent, List<Link> links,
	    String parent_url) {
	int indexOfArrowForward = lwrCaseContent.indexOf(">");
	if (indexOfArrowForward == -1) {
	    return;
	}
	int indexOfArrowBackward = lwrCaseContent.indexOf("<");
	if (indexOfArrowBackward == -1) {
	    return;
	}

	String linkContent = lwrCaseContent.substring(indexOfArrowForward,
		indexOfArrowBackward + 1);
	linkContent = cleanLinkContnet(linkContent);
	if (linkContent.length() == 0)
	    return;

	int indexOfEqual = lwrCaseContent.indexOf("=");

	String url = "!!!";
	if (indexOfEqual != -1)
	    url = lwrCaseContent.substring(indexOfEqual, indexOfArrowForward);

	url = cleanUrl(url);

	parent_url = extractParentFolder(parent_url);

	Link link = new Link(linkContent, url, parent_url);

	links.add(link);
	System.out.println(": " + linkContent + " " + url + " ~" + parent_url);
    }

    private String extractParentFolder(String parent_url) {
	if (parent_url.endsWith("/"))
	    parent_url = parent_url.substring(0, parent_url.length() - 1);

	int indexSlash = parent_url.lastIndexOf("/");

	if (indexSlash == -1)
	    return parent_url;

	parent_url = parent_url.substring(0, indexSlash);
	return parent_url;
    }

    public String cleanLinkContnet(String linkContent) {
	linkContent = linkContent.replace(">", "");
	linkContent = linkContent.replace("<", "");
	linkContent = linkContent.replace("~", "");
	linkContent = linkContent.trim();
	return linkContent;
    }

    private String cleanUrl(String url) {
	url = url.replace("=", "");
	url = url.replace("\"", "");
	url = url.replace("'", "");
	return url.trim();
    }

    public void getIndicesOfOccurence(List<Integer> indicesOfOccurence,
	    String haystack, String searchString, int offset) {
	while (offset != -1) {
	    offset = haystack.indexOf(searchString, searchString.length()
		    + offset);
	    indicesOfOccurence.add(offset);
	}
    }

    @Override
    public void onException(Exception e) {
	System.err.println("ERROR 938409!!!");
    }
}
