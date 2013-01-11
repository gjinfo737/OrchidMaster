package com.orchidmaster.http;
public class Starter {

    private static HtmlParser htmlParser;

    public static void main(String[] args) throws Exception {
	htmlParser = new HtmlParser();

	htmlParser.parseSite("http://www.orchidspecies.com/indexa-anat.htm");
    }
}
