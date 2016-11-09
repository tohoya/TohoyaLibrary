package com.tohoya.gcm;

/**
 * Created by jinyoungho on 2016. 11. 9..
 */

public interface JwNotificationListener {

    /**
     * onTokenResponse
     * @param token
     */
    void onTokenResponse(String token);

    /**
     * finish
     */
    void finish();
}
