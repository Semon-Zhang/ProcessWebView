package com.architect.zhang.web;

public interface IWebClientListener {
    public void onPageStarted(String url);

    public void onPageFinished(String url);

    public void onError();

    public void updateTitle(String title);



}
