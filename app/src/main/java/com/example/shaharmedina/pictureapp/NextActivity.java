package com.example.shaharmedina.pictureapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

public class NextActivity extends AppCompatActivity {

    ImageView image;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        image = (ImageView) findViewById(R.id.bigImageView);
        Bitmap bitmap = (Bitmap)this.getIntent().getParcelableExtra("bitmap");
        image.setImageBitmap(bitmap);

        rb = (RatingBar) findViewById(R.id.bigRatingBar);
        rb.setRating(getIntent().getExtras().getFloat("rating"));
        rb.setIsIndicator(true);
        rb.setActivated(true);

        // Listener for "image touch" include onClick method
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//return to the prev activity
            }
        });
    }

}
