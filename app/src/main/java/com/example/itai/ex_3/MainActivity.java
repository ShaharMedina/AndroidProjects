package com.example.itai.ex_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    public static int ENGLISH = 0;
    public static int FRENCH = 1;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getPreferences(MODE_PRIVATE);
        EditText txt = (EditText)findViewById(R.id.phraseText);
        txt.setText(prefs.getString("phrase", "No previously saved string."));
        switch (prefs.getInt("lang", ENGLISH))
        {
            case 0:
                ((RadioButton)findViewById(R.id.radioEnglish)).setChecked(true);
                break;
            case 1:
                ((RadioButton)findViewById(R.id.radioFrench)).setChecked(true);
                break;
            default:
                ((RadioButton)findViewById(R.id.radioEnglish)).setChecked(true);
        }
    }


    public void onReadTextClick(View v) {
        EditText txt = (EditText) findViewById(R.id.phraseText);
        String phrase = txt.getText().toString();
        Intent i = new Intent(this, Main2Activity.class);
        // Save prefs
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("phrase", phrase);
        editor.putInt("lang", getLang());
        editor.apply();
        i.putExtra("phrase", phrase);
        i.putExtra("lang", getLang());
        startActivity(i);
    }

    public int getLang() {
        RadioButton english = (RadioButton) findViewById(R.id.radioEnglish);
        if (english.isChecked())
            return ENGLISH;
        return FRENCH;
    }
}
