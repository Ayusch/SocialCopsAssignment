package com.ayusch.ayuschsocialcops.Utils;

import android.app.Activity;
import android.content.DialogInterface;

public class WidgetUtils {
    public static void displayPermissionAlert(Activity activity, DialogInterface.OnClickListener positiveClickListener, DialogInterface.OnClickListener negativeClickListener, boolean setCancelable){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(activity);
        builder.setTitle("Permission Needed");
        builder.setMessage("Storage permission needed to download the video");
        builder.setCancelable(setCancelable);
        builder.setPositiveButton("Grant",positiveClickListener);
        builder.setNegativeButton("Exit",negativeClickListener);
        builder.create().show();
    }
}
