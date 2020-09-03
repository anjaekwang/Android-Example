package com.temp.forsimstatetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d(getClass(), "onCreate()");
        registerBR();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            createForegroundServiceNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null ){
            String action = intent.getAction();

            if(Receiver.ACTION_SIM_STATE_CHANGED.equals(action)){
                Logger.d(getClass(), "Service : sim_state Intent 받음");
                String time = intent.getStringExtra("TIME");
                String msg = intent.getStringExtra("RESULT");
                DBLog.getInstance().insert(getApplicationContext(), time, msg);
            }
        }
        return START_STICKY;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void createForegroundServiceNotification()
    {
        createTESTServiceNotificationChannel(getApplicationContext());

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "TestApp")
                .setPriority(NotificationManager.IMPORTANCE_NONE)
                .setCategory(Notification.CATEGORY_SERVICE);

        startForeground(1, notification.build());
    }


    @RequiresApi(Build.VERSION_CODES.O)
    public static void createTESTServiceNotificationChannel(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        /** SDK26 이상부터 Notification 객체를 넣어 줄 Notification Channel 을 생성 **/
        NotificationChannel channel = notificationManager.getNotificationChannel("TestApp");
        if (channel == null) {
            /** Notification Channel 이 없으면 생성해 준다. **/
            channel = new NotificationChannel("TestApp", "TestApp", NotificationManager.IMPORTANCE_NONE);
            channel.setBypassDnd(true);
            channel.enableVibration(false);
            channel.enableLights(false);
            channel.setShowBadge(false);
            channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void registerBR(){
        Logger.d(getClass(), "BR 등록됨");
        Receiver br = new Receiver();
        IntentFilter filter = new IntentFilter();

        filter.addAction(Receiver.ACTION_SIM_STATE_CHANGED);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.addAction(Intent.ACTION_TIME_TICK);

        registerReceiver(br, filter);
    }
}
