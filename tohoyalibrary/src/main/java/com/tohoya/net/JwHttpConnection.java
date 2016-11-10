package com.tohoya.net;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jinyoungho on 2016. 11. 10..
 */

public class JwHttpConnection {
    URL url;
    String url_string;

    public void JwHttpConnection() {

    }

    public void Connection() {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... url_string) {
                String response = null;
                try {
                    url = new URL("11111");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("POST");

                    //conn.setRequestProperty("Cookie", MainActivity.cookieManager.getCookie("app.rhinotv.co.kr"));

                    conn.setDoInput(true);
                    conn.connect();
                    response = conn.getResponseMessage();
                    Log.d("RESPONSE", "The response is: " + response);
                }
                catch (IOException e) {
                }
                return response;
            }
        }.execute(null, null, null);
    }
}
