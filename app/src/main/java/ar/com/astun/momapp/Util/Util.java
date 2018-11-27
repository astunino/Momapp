package ar.com.astun.momapp.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

public class Util {


    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static String getExtension(Uri uri){
        String fileName = uri.getLastPathSegment();
        Integer startExtension = fileName.indexOf(".");
        String extension = fileName.substring(startExtension);
        return extension;
    }
}
