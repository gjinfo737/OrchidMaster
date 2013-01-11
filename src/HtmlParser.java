import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HtmlParser implements IHttpResponseListener {

    public static final String HTTP_COL_SLASHSLASH = "http://";

    public boolean parseSite(String url) {
	final WebRequestCodeSample webRequestCodeSample = new WebRequestCodeSample();

	try {
	    webRequestCodeSample.getData(new URL(url), this);
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	    return false;
	} catch (IOException e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    @Override
    public void onResponse(String content) {
	System.out.println(content.length() + " !!!");
    }

    @Override
    public void onException(Exception e) {
	System.err.println("ERROR 938409!!!");
    }
}
