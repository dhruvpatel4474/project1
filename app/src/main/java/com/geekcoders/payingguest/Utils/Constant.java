package com.geekcoders.payingguest.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.geekcoders.payingguest.Objects.PGObject;
import com.geekcoders.payingguest.Objects.Worker;
import com.parse.ParseObject;

/**
 * Created by dhruvpatel on 3/14/2018.
 */

public class Constant {




    public static PGObject  pgObject;
   public static ParseObject PGParseObject;
   public static String recieverId;
   public static String recieverName;
   public  static  int price;
   public static Worker workerObj;



    public static Context mcontext;
    public static String getValueForKeyString(String strKey) {
        SharedPreferences sharedpreferences = mcontext.getSharedPreferences(
                "MyPREFERENCES", Context.MODE_PRIVATE);
        String strValue = "";
        strValue = sharedpreferences.getString(strKey, null);
        return strValue;
    }

    public static void setValueAndKeyString(String strKey, String strValue) {
        SharedPreferences sharedpreferences = mcontext.getSharedPreferences(
                "MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(strKey, strValue);
        editor.commit();
    }

    public static boolean getValueForKeyBoolean(String strKey) {
        SharedPreferences sharedpreferences = mcontext.getSharedPreferences(
                "MyPREFERENCES", Context.MODE_PRIVATE);
        boolean strValue;
        strValue = sharedpreferences.getBoolean(strKey, false);
        return strValue;
    }

    public static void setValueAndKeyBoolean(String strKey, boolean strValue) {
        SharedPreferences sharedpreferences = mcontext.getSharedPreferences(
                "MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(strKey, strValue);
        editor.commit();
    }






}
