package com.ayusch.ayuschsocialcops.Utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

public class Utils {
    public static String getSdCardDirectory() {
        return Environment.getExternalStorageDirectory().toString() + "/video.mp4";
    }

    public static void openFolder(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().toString());
        intent.setDataAndType(uri, "text/csv");
        activity.startActivity(Intent.createChooser(intent, "Open folder"));
    }
}
