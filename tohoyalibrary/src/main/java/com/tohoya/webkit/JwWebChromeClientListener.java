package com.tohoya.webkit;

import android.webkit.JsResult;
import android.webkit.WebView;

/**
 * Created by jinyoungho on 2016. 11. 8..
 */

public interface JwWebChromeClientListener {
    /**
     * onProgressEvent
     * @param webView
     * @param progress
     */
    void onProgressEvent(WebView webView, int progress);

    /**
     * onAlertEvent
     * @param webView
     * @param url
     * @param message
     * @param result
     * @return
     */
    boolean onAlertEvent(WebView webView, String url, String message, final JsResult result);

    /**
     * onConfirmEvent
     * @param webView
     * @param url
     * @param message
     * @param result
     * @return
     */
    boolean onConfirmEvent(WebView webView, String url, String message, final JsResult result);
}
