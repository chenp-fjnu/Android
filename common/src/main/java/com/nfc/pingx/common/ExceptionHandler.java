package com.nfc.pingx.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    public ExceptionHandler(Activity activity) {
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("EXCEPTION: ",  Log.getStackTraceString(e));
    }
}
