package com.example.shaharmedina.pictureapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingActivity extends AppCompatActivity {

    Button btnApply;
    EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        txt = (EditText) findViewById(R.id.num_input);
        txt.setText(getDefaults("size", this));

        btnApply = (Button) findViewById(R.id.btn_apply);

        //// Listener for "Apply button" include onClick method
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = txt.getText().toString();
                Intent i = new Intent();
                setDefaults("size", input, getApplicationContext());
                i.putExtra("size", input);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    //set shared preference value
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //return the shares preference value
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

}
