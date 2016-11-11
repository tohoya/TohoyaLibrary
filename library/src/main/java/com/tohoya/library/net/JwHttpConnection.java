package com.tohoya.library.net;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by jinyoungho on 2016. 11. 10..
 */

public class JwHttpConnection {
    String TAG = "# JwHttpConnection #";
    URL url;
    String url_string;
    ConnectionTask task;

    JwHttpConnectionListener myListener;

    public JwHttpConnection(Context context) {
        setListener((JwHttpConnectionListener) context);
    }

    public JwHttpConnection(Context context, String url_string) {
        setListener((JwHttpConnectionListener) context);
        if(!url_string.isEmpty()) {
            urlConnection(url_string, null);
        }
    }

    public void urlConnection(String url) {
        if(task == null) task = new ConnectionTask();
        task.execute(url, "");
    }

    public void urlConnection(String url, HashMap<String, String> params) {
        if(task == null) task = new ConnectionTask();
        task.execute(url, getPostParams(params));
    }

    public void setListener(JwHttpConnectionListener listener) {
        myListener = listener;
    }

    public String setHashMap(String key, String value) {
        try {
            return URLEncoder.encode(key, "UTF-8") + "=" + URLEncoder.encode(value, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            new AlertDialog.Builder(null)
                    .setTitle("Error")
                    .setMessage(e.getMessage())
                    .setPositiveButton(android.R.string.ok,
                            new AlertDialog.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                    .setCancelable(false)
                    .create()
                    .show();
        }
        return "";
    }

    public String getPostParams(HashMap<String, String> params) {
        if(params != null) return "";

        String data = "";

        Set key_set = params.keySet();
        Iterator<String> it = key_set.iterator();
        while(it.hasNext()) {
            String key = it.next();
            if(!data.isEmpty()) data += "&";
            data += "&" + setHashMap(key, params.get(key));
        }

        //Log.d(TAG, data);

        return data;
    }

    public class ConnectionTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String response = null;
            try {
                url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");

                conn.setRequestProperty("Cookie", myListener.getCookieResult());
                conn.setDoOutput(true);

                //conn.setDoInput(true);
                if(!params[1].isEmpty() && params[1] != null) {
                    OutputStreamWriter wt = new OutputStreamWriter(conn.getOutputStream());
                    wt.write(params[1]);
                    wt.flush();
                    wt.close();
                }

                conn.connect();
                StringBuilder builder = new StringBuilder();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                while((line = rd.readLine()) != null) {
                    builder.append(line);
                }
                response = builder.toString();
                rd.close();
                //response = conn.getResponseMessage();
                //Log.d("RESPONSE", "The response is: " + response);
            }
            catch (IOException e) {
                new AlertDialog.Builder(null)
                        .setTitle("Error")
                        .setMessage(e.getMessage())
                        .setPositiveButton(android.R.string.ok,
                                new AlertDialog.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            myListener.onResponse(result);
            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
