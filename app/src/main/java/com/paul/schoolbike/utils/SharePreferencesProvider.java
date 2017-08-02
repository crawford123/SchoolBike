package com.paul.schoolbike.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

/**
 * Created by gccd on 2014/9/9.
 */
public class SharePreferencesProvider {

    private final static String CONFIG_NAME = "config";
    private static SharedPreferences preferences = null;

    public static SharedPreferences getMyPreferences(Context context) {

        // TODO Auto-generated method stub
        if (preferences != null)
            return preferences;
        try {
            Context sharedContext = context.createPackageContext(Util.getPackageName(context), Context.CONTEXT_IGNORE_SECURITY);
            preferences = sharedContext.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        } catch (PackageManager.NameNotFoundException e) {
//            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return preferences;
    }
}
