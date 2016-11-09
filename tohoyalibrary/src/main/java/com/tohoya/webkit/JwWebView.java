package com.tohoya.webkit;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Map;

/**
 * Created by jinyoungho on 2016. 11. 8..
 */

public class JwWebView extends WebView {

    private WebSettings webSettings;
    private JwWebViewListener webViewListener;

    /**
     *
     * @param context
     * @param webChromeClient
     */
    public JwWebView(Context context, JwWebChromeClient webChromeClient) {

        super(context);

        setupWebViewDefaults();

        if (Build.VERSION.SDK_INT >= 19) {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        setWebViewClient(new WebViewClient() {});

        if(webChromeClient == null) {
            webChromeClient = new JwWebChromeClient();
            webChromeClient.setEventListener((JwWebChromeClientListener) context);
        }

        setWebChromeClient(webChromeClient);

        webSettings = getSettings();
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
    }

    /**
     *
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupWebViewDefaults() {
        WebSettings settings = getSettings();
        // Enable Javascript
        //settings.setJavaScriptEnabled(true);

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        // Enable pinch to zoom without the zoom buttons
        //settings.setBuiltInZoomControls(true);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            settings.setDisplayZoomControls(false);
        }

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
        }

        // Enable remote debugging via chrome://inspect
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    /**
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(getContext())
                    .setTitle("타이틀")
                    .setMessage("앱을 종료하시겠습니까?")
                    .setPositiveButton("네",
                            new AlertDialog.OnClickListener() {
                                public void onClick(
                                        DialogInterface idalog, int which) {
                                    System.exit(0);
                                }
                            })
                    .setNegativeButton("아니요",
                            new AlertDialog.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog,
                                        int which) {
                                }
                            })
                    .setCancelable(false)
                    .create()
                    .show();
        }

        return true;
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (webViewListener != null) {
            webViewListener.onScroll(l, t, oldl, oldt);
        }
    }


    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void setNetworkAvailable(boolean networkUp) {
        super.setNetworkAvailable(networkUp);
    }

    @Override
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        super.loadUrl(url, additionalHttpHeaders);
    }

    @Override
    public void loadUrl(String url) {
        super.loadUrl(url);
    }

    @Override
    public void postUrl(String url, byte[] postData) {
        super.postUrl(url, postData);
    }

    @Override
    public void stopLoading() {
        super.stopLoading();
    }

    @Override
    public void reload() {
        super.reload();
    }

    @Override
    public boolean canGoBack() {
        return super.canGoBack();
    }

    @Override
    public void goBack() {
        super.goBack();
    }

    @Override
    public boolean canGoForward() {
        return super.canGoForward();
    }

    @Override
    public void goForward() {
        super.goForward();
    }
}
