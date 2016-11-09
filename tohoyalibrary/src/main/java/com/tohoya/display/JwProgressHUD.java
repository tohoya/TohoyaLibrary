package com.tohoya.display;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.tohoya.R;

/**
 * Created by iLains on 2016. 11. 9..
 */

public class JwProgressHUD extends Dialog {
    public JwProgressHUD(Context context) {
        super(context);
    }

    public JwProgressHUD(Context context, int theme) { super(context, theme); }

    public void onWindowFocusChanged(boolean hasFocus) {
        ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

    public void setMessage(CharSequence message) {
        TextView myMessage = (TextView)findViewById(R.id.message);
        if(message != null && message.length() > 0) {
            myMessage.setVisibility(View.VISIBLE);
            myMessage.setText(message);
            myMessage.invalidate();
        } else {
            myMessage.setVisibility(View.GONE);
        }
    }

    public static JwProgressHUD show(Context context, CharSequence message, boolean indeterminate, boolean cancelable, OnCancelListener cancelListener) {
        JwProgressHUD progress = new JwProgressHUD(context, R.style.ProgressHUD);
        progress.setTitle("");
        progress.setContentView(R.layout.progress_hud);
        progress.setMessage(message);
        progress.setOnCancelListener(cancelListener);
        progress.getWindow().getAttributes().gravity = Gravity.CENTER;
        WindowManager.LayoutParams layoutParams = progress.getWindow().getAttributes();
        layoutParams.dimAmount = 0.2f;
        progress.getWindow().setAttributes(layoutParams);
        progress.show();
        return progress;
    }
}
