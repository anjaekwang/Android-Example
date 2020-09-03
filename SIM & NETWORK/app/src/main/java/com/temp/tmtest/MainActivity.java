package com.temp.tmtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText text;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn = findViewById(R.id.btn);
        text = findViewById(R.id.text);
        mContext = this;

        if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){                     // 권한체크, Activity에 쓴다.
            Toast.makeText(getApplicationContext(), "READ_PHONE_STATE 권한 있음.",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "SREAD_PHONE_STATE 권한 없음.",Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},1);

        }

        btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                if(checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){            // tm : sim관련 정보를 가져오는 매니져
                    String msisdn = tm.getLine1Number();
                    String netOperName = tm.getNetworkOperatorName();
                    String netOper = tm.getNetworkOperator();
                    int simState = tm.getSimState();
                    String netType = getNetworkType();

                    text.setText("전화번호 : " + msisdn + "\n통신사업자 : " +  netOperName + "\nnetOper : " + netOper + "\n네트워크타입 : " + netType + "\nSIM : " + simState );
                }
                else{
                    text.setText("권한거부");
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {              //퍼미션 결과
        if(requestCode ==1 ){
            if (grantResults.length>0) {
                grantResults[0] = PackageManager.PERMISSION_GRANTED;
                Toast.makeText(getApplicationContext(), "READ_PHONE_STATE 권한을 사용자가 승인함.",Toast.LENGTH_LONG).show();
            } else{
                Toast.makeText(getApplicationContext(), "READ_PHONE_STATE 권한을 사용자가 거부함.",Toast.LENGTH_LONG).show();
            }
        }

    }

    public String getNetworkType(){
        String net = null;
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);                      // CM은 네트워크 상태확인.
        NetworkInfo network = cm.getActiveNetworkInfo();                                                                             // MObile, wifi, null 확인
        if(network != null){
            if(network.getType() == ConnectivityManager.TYPE_WIFI){
                net = "WiFi";
            }else if(network.getType() == ConnectivityManager.TYPE_MOBILE){
                net = "mobile";
            }
        }
        return net;
    }

}
