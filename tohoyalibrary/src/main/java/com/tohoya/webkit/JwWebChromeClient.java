package com.tohoya.webkit;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by jinyoungho on 2016. 11. 8..
 */

public class JwWebChromeClient extends WebChromeClient {
    private JwWebChromeClientListener myListener;

    /**
     * JwWebChromeClient
     */
    public JwWebChromeClient() {
        super();
    }

    /**
     * onJsAlert
     * @param webView
     * @param url
     * @param message
     * @param result
     * @return
     */
    @Override
    public boolean onJsAlert(WebView webView, String url, String message, final JsResult result) {
        return myListener.onAlertEvent(webView, url, message, result);
    }

    /**
     * onJsConfirm
     * @param webView
     * @param url
     * @param message
     * @param result
     * @return
     */
    @Override
    public boolean onJsConfirm(WebView webView, String url, String message, final JsResult result) {
        return myListener.onConfirmEvent(webView, url, message, result);
    }

    /**
     * onProgressChanged
     * @param webView
     * @param newProgress
     */
    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        myListener.onProgressEvent(webView, newProgress);
    }

    /**
     * setEventListener
     * @param listener
     */
    public void setEventListener(JwWebChromeClientListener listener) {
        myListener = listener;
    }
}
