package com.example.shaharmedina.ex2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.*;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    Button btnPlus, btnMinus;
    TextView txtCounter;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPlus = (Button) findViewById(R.id.buttonPlus);
        btnMinus = (Button) findViewById(R.id.buttonMinus);
        txtCounter = (TextView) findViewById(R.id.textCounter);
        updateCounter();
    }

    public void incrementCounter(View v) {
        counter++;
        updateCounter();
    }

    public void decrementCounter(View v) {
        counter--;
        updateCounter();
    }

    public void resetCounter(View v) {
        counter = 0;
        updateCounter();
    }

    public void updateCounter() {
        if (counter >= 0)
        {
            txtCounter.setBackgroundColor(Color.GREEN);
        }
        else
        {
            txtCounter.setBackgroundColor(Color.RED);
        }
        txtCounter.setText(String.valueOf(counter));
    }
}
