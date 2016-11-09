package com.tohoya.webkit;

/**
 * Created by jinyoungho on 2016. 11. 9..
 */

public interface JwWebViewListener {
    void onScroll(int left, int top, int old_left, int old_top);
    void onFinish(String url);
    void onUrlChange(String url);
    void onProgressChanged(int newProgress);
}
