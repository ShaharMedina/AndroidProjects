package com.example.itai.ex_4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    AssigmmentsDbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new AssigmmentsDbHelper(this);
        db = dbHelper.getWritableDatabase();

        LoadData();
    }


    public void OnInsertClick(View temp){
        String firstName = ((EditText)findViewById(R.id.FirstText)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.LastText)).getText().toString();
        String number = ((EditText)findViewById(R.id.NumberText)).getText().toString();
        dbHelper.InsertNew(db, firstName, lastName, number);
        LoadData();
    }

    private void LoadData(){
        Cursor cursor = dbHelper.allData(db);
        TextView textView = (TextView) findViewById(R.id.allData);
        while (cursor.moveToNext()){
            textView.setText(textView.getText() + "\n" +cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3));
        }
        cursor.close();
    }
}
