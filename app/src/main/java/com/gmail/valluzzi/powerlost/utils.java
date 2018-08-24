package com.gmail.valluzzi.powerlost;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class utils {

    public static void printf(Object o){

        Log.e("PowerLost",""+o);

    }

    public static void SendSMS(String number,String text){


        try{
            number = normalizeNumber(number);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number,null,text,null,null);
        }
        catch(Exception ex){
            utils.printf(ex);
        }
    }

    public static String normalizeNumber(String text){
        if (text==null)
            return "";

        //Cause wathapp 39 mustbe rmoved
        text  = text.replaceAll("[ \\-#._?]", "");
        if (!text.startsWith("+") && text.length()==10){
            text = "+39"+text;
        }

        return text;
    }
}//end class
