package com.example.visualwindow.SettingsClass;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.visualwindow.R;

public class ChangeSettings {
    public static void saveFontColor( String color,AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("DataSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("FONTCOLOR",color);
        editor.commit();
    }
    public static int getDataFontColor(AppCompatActivity activity){
        SharedPreferences prefThemes = activity.getSharedPreferences("DataSettings", Context.MODE_PRIVATE);
        String color = prefThemes.getString("FONTCOLOR","BLUE");
        switch (color){
            case "CYAN":
                return Color.CYAN;
            case "LTGRAY":
                return Color.LTGRAY;
            case "BLUE":
                return Color.BLUE;
            case "YELLOW":
                return Color.YELLOW;
            case "BLACK":
                return Color.BLACK;
        }
        return Color.BLACK;
    }

    public static void saveFontSize( int size,AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("DataSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("FONTSIZE",size);
        editor.commit();
    }

    public static void saveFontFamily( String family,AppCompatActivity activity){
        SharedPreferences preferences = activity.getSharedPreferences("DataSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("FONTFAMILY",family);
        editor.commit();
    }
    public static String getDataFontFamily(AppCompatActivity activity){
        SharedPreferences prefThemes = activity.getSharedPreferences("DataSettings", Context.MODE_PRIVATE);
        String font = prefThemes.getString("FONTFAMILY","none");
        Log.d(" FONTFAMILY", "" + font);

        return font;
    }
    public static int getDataFont(AppCompatActivity activity){
        SharedPreferences prefThemes = activity.getSharedPreferences("DataSettings", Context.MODE_PRIVATE);
        int font = prefThemes.getInt("FONTSIZE",1);
        Log.d("Font Size", "" + font);
        switch (font){
            case 1:
                return 14;
            case 2:
                return 24;
            case 3:
                return 36;
        }
        return 14;
    }
//    Save and get color theme
    public static void getDataTheme(AppCompatActivity activity){
        SharedPreferences prefThemes = activity.getSharedPreferences("DataSettings", Context.MODE_PRIVATE);
        String valueTheme = prefThemes.getString("Theme","0");
        Log.d("Valor Theme", valueTheme);
        String color = valueTheme.equals("0")? activity.getString(R.string.value_first_color) : activity.getString(R.string.value_second_color);

        activity.getWindow().setStatusBarColor(Color.parseColor(color));
        activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor(bck)));
        activity.getWindow().setNavigationBarColor(Color.parseColor(color));
    }
    public static void saveSettingTheme(AppCompatActivity activity, String value){
        SharedPreferences preferences = activity.getSharedPreferences("DataSettings",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Theme",value);
        editor.commit();
    }
    public static boolean getValueDataTheme(AppCompatActivity activity){
        SharedPreferences prefThemes = activity.getSharedPreferences("DataSettings", Context.MODE_PRIVATE);
        String valueTheme = prefThemes.getString("Theme","0");
        return valueTheme.equals("1")? true: false;
    }
}
