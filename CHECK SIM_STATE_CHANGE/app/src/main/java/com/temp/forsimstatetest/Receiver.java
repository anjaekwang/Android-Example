package com.temp.forsimstatetest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.Date;

// 인텐트를 받으면 앱을 포그라운드로 항상 실행시키게끔..
public class Receiver extends BroadcastReceiver{

    public static final String ACTION_SIM_STATE_CHANGED = "android.intent.action.SIM_STATE_CHANGED";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Logger.d(getClass(), "BR 액션 받음 : " + action);

        Intent newintent = new Intent(context, MyService.class);

        if(ACTION_SIM_STATE_CHANGED.equals(action)){

            newintent.setAction(ACTION_SIM_STATE_CHANGED);

            long time = System.currentTimeMillis();
            SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String str = dayTime.format(new Date(time));
            newintent.putExtra("TIME", str);

            if(isSimStateReady(context)){
                newintent.putExtra("RESULT", "SIM_STATE_READY");
            }else{
                newintent.putExtra("RESULT", "SIM_STATE_ABSENT");
            }
        }
        else if(Intent.ACTION_BOOT_COMPLETED.equals(action)){
            newintent.setAction(Intent.ACTION_BOOT_COMPLETED);
        }
        else if (Intent.ACTION_USER_PRESENT.equals(action)) {
            newintent.setAction(Intent.ACTION_USER_PRESENT);
        }
        else if (Intent.ACTION_TIME_TICK.equals(action)){
            newintent.setAction(Intent.ACTION_TIME_TICK);
        }


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) context.startForegroundService(newintent);
        else context.startService(newintent);
    }

    public static boolean isSimStateReady(Context context) {
        boolean simReady = false;

        TelephonyManager telephoneMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int simState = telephoneMgr.getSimState();

        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT:
                simReady = false;
                break;
            case TelephonyManager.SIM_STATE_READY:
                simReady = true;
        }
        return simReady;
    }
}