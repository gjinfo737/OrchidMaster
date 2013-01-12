package com.orchidmaster.tagobj;

public class Link {

    private String ParentURL;
    private String LinkContent;
    private String Url;

    public Link(String linkContent, String _url, String ParentURL) {
	this.LinkContent = linkContent;
	this.Url = _url;
	this.ParentURL = ParentURL;
    }

    public String getLinkContent() {
	return LinkContent;
    }

    public void setLinkContent(String linkContent) {
	LinkContent = linkContent;
    }

    public String getUrl() {
	return Url;
    }

    public void setUrl(String url) {
	Url = url;
    }

    public String getParentURL() {
	return ParentURL;
    }

    public void setParentURL(String parentURL) {
	ParentURL = parentURL;
    }

}
