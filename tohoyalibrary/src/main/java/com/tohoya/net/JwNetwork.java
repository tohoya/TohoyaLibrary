package com.tohoya.net;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jinyoungho on 2016. 11. 8..
 */

public class JwNetwork {
    /**
     * isService
     * @param myApp
     * @return
     */
    static public boolean isService(Context myApp) {
        ConnectivityManager manager = (ConnectivityManager)myApp.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo cellular = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(wifi.isConnected() != true && cellular.isConnected() != true) {
            new AlertDialog.Builder(myApp)
                    .setTitle("안내")
                    .setMessage("네트워크가 연결되어 있지 않습니다.")
                    .setPositiveButton("OK",
                            new AlertDialog.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                    .setCancelable(false)
                    .create()
                    .show();
            return false;
        }
        return true;
    }
}
