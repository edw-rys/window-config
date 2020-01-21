package com.example.visualwindow;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.visualwindow.SettingsClass.ChangeSettings;

public class Settings extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    Switch switch_theme;
    RadioGroup fontSizeGroup;
    RadioButton btnRdSmall, btnRdMediun,btnRdBig;
    Spinner spiner;
    Spinner spinerColor;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        ActionBar action = getSupportActionBar();
        action.setDisplayHomeAsUpEnabled(true);
        ChangeSettings.getDataTheme(this);

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        switch_theme = (Switch) findViewById(R.id.switchChangeTheme);
        switch_theme.setChecked(ChangeSettings.getValueDataTheme(this));

        switch_theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String primary =  getString(R.string.value_first_color);
                if(isChecked){
                    primary = getString(R.string.value_second_color);
                }
                getWindow().setStatusBarColor(Color.parseColor(primary));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(primary)));
                //getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bck)));

                getWindow().setNavigationBarColor(Color.parseColor(primary));
            }
        });

        // font size
        btnRdSmall = (RadioButton) findViewById(R.id.shortFont);
        btnRdMediun = (RadioButton) findViewById(R.id.mediunFont);
        btnRdBig = (RadioButton) findViewById(R.id.bigFont);

//        Spinner
        spiner = findViewById(R.id.fontFamily);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(adapter);
        spiner.setOnItemSelectedListener(this);


        spinerColor = findViewById(R.id.colorSpinner);
        ArrayAdapter<CharSequence> adapter_color = ArrayAdapter.createFromResource(this, R.array.colors,android.R.layout.simple_spinner_item);
        adapter_color.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerColor.setAdapter(adapter_color);
        spinerColor.setOnItemSelectedListener(this);
    }
    private void saveChanges(){

        ChangeSettings.saveSettingTheme(this, switch_theme.isChecked()?"1":"0");
        Toast.makeText(Settings.this, "Guardado", Toast.LENGTH_SHORT).show();
        int fontId=1;
        //   font
        if(btnRdSmall.isChecked()){
            fontId=1;
        }else if (btnRdMediun.isChecked()){
            fontId=2;
        }else if (btnRdBig.isChecked()){
            fontId=3;
        }
        ChangeSettings.saveFontSize(fontId,this);
        Log.d("Save",""+switch_theme.isChecked());
//        Spinner
        String item = spiner.getSelectedItem().toString();
        ChangeSettings.saveFontFamily(item,this);
//        Color
        item = spinerColor.getSelectedItem().toString();
        ChangeSettings.saveFontColor(item,this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}