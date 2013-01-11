public interface IHttpResponseListener {
    public void onResponse(String content);

    public void onException(Exception e);
}
