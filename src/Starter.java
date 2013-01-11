public class Starter {

    private static HtmlParser htmlParser;

    public static void main(String[] args) throws Exception {
	htmlParser = new HtmlParser();

	htmlParser
		.parseSite(HtmlParser.HTTP_COL_SLASHSLASH + "allenjames.net/");
    }
}
