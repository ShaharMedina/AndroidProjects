package com.example.itai.ex_3;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    TextToSpeech tts;
    int locale = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();
        String phrase = i.getStringExtra("phrase");
        locale = i.getIntExtra("lang",0);
        TextView txt = (TextView) findViewById(R.id.phraseLabelMain2Activity);
        // Create Text to Speech
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
           public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    if (locale == 0)
                        tts.setLanguage(Locale.US);
                    else
                        tts.setLanguage(Locale.FRENCH);
                }
           }
        });
        // Set Text
        if (phrase == null)
        {
            txt.setText("Text is null.");
        }
        else
        {
            txt.setText(phrase);
        }
    }


    public void onTextToSpeechClick(View v) {
        TextView txt = (TextView) findViewById(R.id.phraseLabelMain2Activity);
        tts.speak(txt.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    if (locale == 0)
                        tts.setLanguage(Locale.US);
                    else
                        tts.setLanguage(Locale.FRENCH);
                }
            }
        });
    }

    public void onPause() {
        super.onPause();  // Always call the superclass method first
        tts.stop();
        tts.shutdown();
    }
}

