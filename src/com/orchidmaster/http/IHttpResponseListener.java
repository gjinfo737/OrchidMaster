package com.orchidmaster.http;

public interface IHttpResponseListener {
    public void onResponse(String content, String parentURL);

    public void onException(Exception e);
}
