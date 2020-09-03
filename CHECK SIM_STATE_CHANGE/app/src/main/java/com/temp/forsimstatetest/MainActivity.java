package com.temp.forsimstatetest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView log;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = findViewById(R.id.log);
        log.setMovementMethod(new ScrollingMovementMethod());

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View view) {
                   Logger.d(getClass(), "버튼눌림");
                   StringBuilder msg = new StringBuilder();
                   DBLog.getInstance().query(getApplicationContext(), msg);

                   if(msg.toString().isEmpty()) log.setText("DB내용없음");
                   else log.setText(msg.toString());
               }
           }
        );

        Intent intent = new Intent(getApplicationContext(), MyService.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            getApplicationContext().startForegroundService(intent);
        }else getApplicationContext().startService(intent);
    }
}
