package com.example.visualwindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.visualwindow.SettingsClass.ChangeSettings;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChangeSettings.getDataTheme(this);
        int sizeFont = ChangeSettings.getDataFont(this);

        TextView text = findViewById(R.id.text);
        text.setTextSize(sizeFont);
        text.setFontFeatureSettings("casual");
        String font = ChangeSettings.getDataFontFamily(this);
        Typeface type= null;
        if (!font.equals("none")){
            switch (font){
                case "Jungle Land":
                    type = Typeface.createFromAsset(getAssets(),"fonts/Jungle Land.ttf");
                    break;
                case "keysha":
                    type = Typeface.createFromAsset(getAssets(),"fonts/keysha.otf");
                    break;
                case "Vesta Night":
                    type = Typeface.createFromAsset(getAssets(),"fonts/Vesta Night.otf");
                    break;
            }
            text.setTypeface(type);
        }
        text.setTextColor(ChangeSettings.getDataFontColor(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        System.out.println(id);
        switch (id){
            case R.id.config:
                this.executeSettings();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    private void executeSettings(){
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
    }
}
