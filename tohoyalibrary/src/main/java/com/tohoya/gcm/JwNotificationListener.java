package com.tohoya.gcm;

/**
 * Created by jinyoungho on 2016. 11. 9..
 */

public interface JwNotificationListener {
    void onTokenResponse(String token);

    void finish();
}
