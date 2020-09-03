package com.temp.test;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.provider.Settings;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView temp  = findViewById(R.id.temp);
        temp.setText(Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
    }
}
