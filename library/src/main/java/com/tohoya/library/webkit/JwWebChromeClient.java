package com.tohoya.library.webkit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Message;
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
        if(myListener != null) {
            return myListener.onAlertEvent(webView, url, message, result);
        }
        return true;
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
        if(myListener != null) {
            return myListener.onConfirmEvent(webView, url, message, result);
        }
        return true;
    }

    /**
     * onProgressChanged
     * @param webView
     * @param newProgress
     */
    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        if(myListener != null) {
            myListener.onProgressEvent(webView, newProgress);
        }
    }
    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, android.os.Message resultMsg) {
        if(myListener != null) {
            return myListener.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
        }
        return true;
    };

    /**
     * setEventListener
     * @param listener
     */
    public void setEventListener(JwWebChromeClientListener listener) {
        myListener = listener;
    }

//    @Override
//    public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg)
//    {
//        WebView.HitTestResult result = view.getHitTestResult();
//        String data = result.getExtra();
//        Context context = view.getContext();
//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
//        context.startActivity(browserIntent);
//        return false;
//    }


}
