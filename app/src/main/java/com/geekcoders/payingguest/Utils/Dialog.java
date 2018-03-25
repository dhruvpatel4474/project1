package com.geekcoders.payingguest.Utils;

import android.content.Context;

import com.geekcoders.payingguest.R;

import dmax.dialog.SpotsDialog;

/**
 * Created by raj15 on 25-Mar-18.
 */

public class Dialog {
    private static SpotsDialog progressDialog;

    public static void showDialog(Context context)
    {
        progressDialog = new SpotsDialog(context, R.style.Custom);
        try {
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            if (!progressDialog.isShowing())
            {
                progressDialog.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void closeDialog()
    {
        try {
            if (progressDialog.isShowing())
            {
                progressDialog.cancel();
                progressDialog.dismiss();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
