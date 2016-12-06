package com.tohoya.library.webkit;

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

    /**
     *
     * @param view
     * @param isDialog
     * @param isUserGesture
     * @param resultMsg
     * @return
     */
    boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, android.os.Message resultMsg);
}
