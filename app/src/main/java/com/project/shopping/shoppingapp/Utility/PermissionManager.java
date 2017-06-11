package com.project.shopping.shoppingapp.Utility;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.requestPermissions;

/**
 * Created by mohan on 06/02/17.
 */

public class PermissionManager {

    public static final int PERMISSION_REQUEST_READ_SMS = 102;
    public static final int REQUEST_ASK_EXTERNAL_STORAGE_ND_CAMERA = 105;
    public static final int PERMISSION_REQUEST_WRITE_STORAGE = 103;
    public static final int PERMISSION_REQUEST_ACCESS_LOCATION = 111;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean requestReadSmsPermission(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String readSmsPermission = Manifest.permission.READ_SMS;
            int hasPermission = ContextCompat.checkSelfPermission(context, readSmsPermission);
            final String[] permissions = new String[]{readSmsPermission};
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions((Activity) context, permissions, PERMISSION_REQUEST_READ_SMS);
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean requestLocationPermission(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String locationPermission = Manifest.permission.ACCESS_FINE_LOCATION;
            int hasPermission = ContextCompat.checkSelfPermission(context, locationPermission);
            final String[] permissions = new String[]{locationPermission};
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions((Activity) context, permissions, PERMISSION_REQUEST_ACCESS_LOCATION);
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean requestReadStoragePermission(final Context context, android.app.Fragment fragment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String writesPermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
            int hasPermission = ContextCompat.checkSelfPermission(context, writesPermission);
            final String[] permissions = new String[]{writesPermission};
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
               // FragmentCompat.requestPermissions(fragment, permissions, PERMISSION_REQUEST_WRITE_STORAGE);
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public static boolean requestImagePickerPermissions(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionReadExternalStorage = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
            int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
            List<String> listPermissionsNeeded = new ArrayList<>();
            if (permissionReadExternalStorage != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.CAMERA);
            }
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(getActivityLocal(context), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ASK_EXTERNAL_STORAGE_ND_CAMERA);
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    private static AppCompatActivity getActivityLocal(Context context) {
        //Context context = this.context;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (AppCompatActivity) context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

}
