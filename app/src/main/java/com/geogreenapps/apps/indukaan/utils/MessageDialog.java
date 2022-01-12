package com.geogreenapps.apps.indukaan.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.geogreenapps.apps.indukaan.R;


/**
 * Created by Droideve on 9/13/2016.
 */

public class MessageDialog {


    private static MessageDialog instance;
    private final Dialog dialog;

    public MessageDialog(Context context) {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_msg_layout);
        dialog.setCancelable(false);


    }

    public static MessageDialog newDialog(Context context) {
        instance = new MessageDialog(context);
        return instance;
    }

    public static MessageDialog getInstance() {
        return instance;
    }

    public boolean isShowen() {


        return instance != null && dialog != null && dialog.isShowing();
    }

    public MessageDialog setContent(String mesg) {

        TextView mesgBox = dialog.findViewById(R.id.msgbox);

        mesgBox.setText(Html.fromHtml(mesg));
        return instance;

    }

    public MessageDialog onOkClick(View.OnClickListener event) {
        Button ok = dialog.findViewById(R.id.ok);

        ok.setText(Translator.print("OK", null));
        ok.setOnClickListener(event);
        return instance;
    }

    public MessageDialog onCancelClick(View.OnClickListener event) {
        Button cancel = dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(event);

        cancel.setText(Translator.print("Cancel", null));
        return instance;
    }

    //fixing bug reported by crashlytics : Unable to add window -- token android.os.BinderProxy@26700fe is not valid; is your activity running?
    public void show() {
        if (instance != null) {
            try {
                dialog.show();
            } catch (WindowManager.BadTokenException e) {
                //use a log message
            }
        }
    }

    public void hide() {

        if (instance != null) {
            try {
                dialog.dismiss();
            } catch (WindowManager.BadTokenException e) {
                //use a log message
            }
        }
    }

}
