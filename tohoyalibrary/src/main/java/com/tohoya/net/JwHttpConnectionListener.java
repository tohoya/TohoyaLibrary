package com.tohoya.net;

/**
 * Created by jinyoungho on 2016. 11. 10..
 */

public interface JwHttpConnectionListener {
    void onResponse(String result);
    String getCookieResult();
}
