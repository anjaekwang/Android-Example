package com.temp.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaDrm;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import java.io.IOException;
import java.util.UUID;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;


public class MainActivity extends AppCompatActivity {

    private static MediaDrm m_mediaDrm;
    public static String SSAID;
    public static String WideVineId;
    public static String ADID;
    public static StringBuilder outString;                          // TODO : 백그라운드 완료시 완성시키기
    static TextView textView;
    public static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView  = findViewById(R.id.temp);
        mContext = getApplicationContext();
        SSAID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        WideVineId = getWideVineId();

        GoogleAppIdTask test = new GoogleAppIdTask();
        test.execute();

    }

    public static void updateUI(String str){
        textView.setText(str);
    }


    public static String getWideVineId(){
        UUID deviceUuid;
        String deviceId = null;

        deviceUuid = new UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L);
        try{
            // WideVine  모듈이 없을때 넣으면 에러 발생
            m_mediaDrm = new MediaDrm(deviceUuid);
            deviceId = android.util.Base64.encodeToString(m_mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID), 0).trim();
            //deviceId = m_mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID).toString();

        } catch (Exception e){
            return e.toString();
        }
        return deviceId;
    }

    private class GoogleAppIdTask extends AsyncTask<Void, Void, String> {
        protected String doInBackground(final Void... params) {
            String adId = null;
            try {
                adId = AdvertisingIdClient.getAdvertisingIdInfo(mContext).getId();
            } catch (IllegalStateException ex) {
                updateUI(ex.toString());
            } catch (GooglePlayServicesRepairableException ex) {
                updateUI(ex.toString());
            } catch (IOException ex) {
                updateUI(ex.toString());
            } catch (GooglePlayServicesNotAvailableException ex) {
                updateUI(ex.toString());
            }
            return adId;
        }

        protected void onPostExecute(String adId) {
            //TODO::Ad ID를 이용한 작업 수행
            ADID = adId;
            makeOutString();
            updateUI(outString.toString());
        }
    }

    public static void makeOutString(){
        outString = new StringBuilder();
        outString.append("SSAID : ").append(SSAID).append("\n")
                .append("길이 : ").append(SSAID.length()).append("\n\n")
                .append("ADID : ").append(ADID).append("\n")
                .append("길이 : ").append(ADID.length()).append("\n\n")
                .append("WideVineId : ").append(WideVineId).append("\n")
                .append("길이 : ").append(WideVineId.length()).append("\n\n");
    }
}
