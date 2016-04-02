package com.example.shaharmedina.pictureapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.system.*;

public class MainActivity extends AppCompatActivity {


    Button btnTakePic , btnNext, btnSetting;
    ImageView image1, image2, image3;
    RatingBar rb1, rb2, rb3;
    Bitmap b1, b2, b3;

    private int counter;
    private float topRating;
    private int bitmapSwitch = 0;
    private static final int DEFAULT_SIZE = 15;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int sizeToInt = DEFAULT_SIZE;
        String settingChange = SettingActivity.getDefaults("size", this);

        try {
            sizeToInt = Integer.parseInt(settingChange);
        } catch (NumberFormatException e){
            System.out.println("Could not parse" + e);
        }

        if(sizeToInt >= 28){
            sizeToInt = 28;
        }

        btnTakePic = (Button) findViewById(R.id.btn_pic);
        btnSetting = (Button) findViewById(R.id.btn_setting);
        btnNext = (Button) findViewById(R.id.btn_next);

        btnNext.setTextSize(sizeToInt);
        btnTakePic.setTextSize(sizeToInt);
        btnSetting.setTextSize(sizeToInt);

        image1 = (ImageView) findViewById(R.id.imageView);
        image2 = (ImageView) findViewById(R.id.imageView2);
        image3 = (ImageView) findViewById(R.id.imageView3);

        rb1 = (RatingBar) findViewById(R.id.ratingBar1);
        rb2 = (RatingBar) findViewById(R.id.ratingBar2);
        rb3 = (RatingBar) findViewById(R.id.ratingBar3);

        rb1.setIsIndicator(true);
        rb2.setIsIndicator(true);
        rb3.setIsIndicator(true);

        counter = 0;
        topRating = 0;

        // Listener for "take a picture button" include onClick method
        btnTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter == 3) {
                    counter = 1;
                } else counter++;

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, counter);

            }
        });

        // Listener for "Next button" include onClick method
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTopRatingImage();
                Intent intent = new Intent(getApplicationContext(), NextActivity.class);
                intent.putExtra("rating", getTopRating());


                if (bitmapSwitch == 1) {
                    intent.putExtra("bitmap", b1);
                } else if (bitmapSwitch == 2) {
                    intent.putExtra("bitmap", b2);
                } else if (bitmapSwitch == 3) {
                    intent.putExtra("bitmap", b3);
                }

                startActivity(intent);
            }
        });

        // Listener for "Setting button" include onClick method
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
                startActivityForResult(intent, 4);
            }
        });
    }

    // Get the results from other activities (Camera & Setting) and handel with the data
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    b1 = (Bitmap) extras.get("data");
                    image1.setImageBitmap(b1);
                    rb1.setRating(0);
                    rb1.setIsIndicator(false);
                    rb1.setActivated(true);
                    break;
                }
            case 2:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    b2 = (Bitmap) extras.get("data");
                    image2.setImageBitmap(b2);
                    rb2.setRating(0);
                    rb2.setIsIndicator(false);
                    rb2.setActivated(true);
                    break;
                }
            case 3:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    b3 = (Bitmap) extras.get("data");
                    image3.setImageBitmap(b3);
                    rb3.setRating(0);
                    rb3.setIsIndicator(false);
                    rb3.setActivated(true);
                    break;
                }
            case 4:
                if (resultCode == RESULT_OK){
                    int sizeToInt = DEFAULT_SIZE;
                    Bundle extras = data.getExtras();
                    String size = extras.getString("size");
                    try {
                        sizeToInt = Integer.parseInt(size);
                    } catch (NumberFormatException e){
                        System.out.println("Could not parse" + e);
                    }
                    if(sizeToInt >= 28){
                        sizeToInt = 28;
                    }
                    btnNext.setTextSize(sizeToInt);
                    btnTakePic.setTextSize(sizeToInt);
                    btnSetting.setTextSize(sizeToInt);
                    break;
                }
        }

    }

    // Method that check witch rating bar have top rating otherwise initialize to the oldest one
    public void getTopRatingImage()
    {
        if(rb1.getRating() > rb2.getRating() && rb1.getRating() > rb3.getRating()) {
            topRating = rb1.getRating();
            bitmapSwitch = 1;
        }
        else if (rb2.getRating() > rb1.getRating() && rb2.getRating() > rb3.getRating()) {
            topRating = rb2.getRating();
            bitmapSwitch = 2;
        }
        else if (rb3.getRating() > rb1.getRating() && rb3.getRating() > rb2.getRating()) {
            topRating = rb3.getRating();
            bitmapSwitch =3;
        }
        else {
            topRating = rb1.getRating();
            bitmapSwitch = 1;
        }

    }

    //Return the number of top rating bar
    public float getTopRating(){
        return topRating;
    }

}
