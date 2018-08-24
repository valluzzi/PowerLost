package com.gmail.valluzzi.powerlost.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gmail.valluzzi.powerlost.AppCompatPreferenceActivity;
import com.gmail.valluzzi.powerlost.R;
import com.gmail.valluzzi.powerlost.SettingsActivity;
import com.gmail.valluzzi.powerlost.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Valerio on 18/12/2015.
 */
public class PowerConnection extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Date currentTime = Calendar.getInstance().getTime();
        //format: "yyyy-MM-dd HH:mm:ss"
        String now = new SimpleDateFormat("HH:mm").format(currentTime);
        String message= "";
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);

        if (action.equalsIgnoreCase(Intent.ACTION_POWER_CONNECTED)){

            message = SP.getString("resume","");

        }

        if (action.equalsIgnoreCase(Intent.ACTION_POWER_DISCONNECTED)){

            message = SP.getString("blackout","");
        }

        if (message!=""){
            message = "Alle "+now+" "+message;
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            //SMS Alarm!

            String dest1 = SP.getString("dest1", "");
            String dest2 = SP.getString("dest2", "");
            Boolean sms_enabled = SP.getBoolean("sms_enabled", false);
            if (sms_enabled) {
                if (dest1.length()>=10)
                    utils.SendSMS(dest1, message);
                if (dest2.length()>=10 && !dest1.equals(dest2))
                    utils.SendSMS(dest2, message);
            }else{
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }




        //
    }
}//end class