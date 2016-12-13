package com.tohoya.library.webkit;

import android.view.KeyEvent;

/**
 * Created by jinyoungho on 2016. 11. 9..
 */

public interface JwWebViewListener {
    /**
     * onScroll
     * @param left
     * @param top
     * @param old_left
     * @param old_top
     */
    void onScroll(int left, int top, int old_left, int old_top);

    /**
     * onFinish
     * @param url
     */
    void onFinish(String url);

    /**
     * onUrlChange
     * @param url
     */
    void onUrlChange(String url);

    /**
     * onProgressChanged
     * @param newProgress
     */
    void onProgressChanged(int newProgress);


    /**
     * onKeyDown
     * @param webView
     * @param keyCode
     * @param event
     * @return
     */
    boolean onWebviewKeyDown(JwWebView webView, int keyCode, KeyEvent event);

}
