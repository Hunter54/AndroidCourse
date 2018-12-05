package com.example.ionut.androidcourse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvMovieLink,tvMovieName,tvDescription,tvGenre;
    private ImageView ivMoviePoster;
    private RatingBar rbMovieRating;
    private String movieName="";
    private String movieDescription="";
    private String movieGenre="";
    private Float movieRating= 0f;
    private String moviePhoto="";
    private String movieLink ="";
    private int movieYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Bundle bundle= getIntent().getExtras();
        if(bundle!=null)
        {
            movieName=bundle.getString(Constants.MOVIE_NAME);
            movieDescription=bundle.getString(Constants.MOVIE_DESCRIPTION);
            movieGenre=bundle.getString(Constants.MOVIE_GENRE);
            movieRating=bundle.getFloat(Constants.MOVIE_RATING);
            movieYear=bundle.getInt(Constants.MOVIE_YEAR);
            moviePhoto=bundle.getString(Constants.MOVIE_PHOTO);
            movieLink=bundle.getString(Constants.MOVIE_LINK);
        }
        tvMovieName=findViewById(R.id.tvMovieName);
        tvMovieName.setText(movieName);

        tvDescription=findViewById(R.id.tvMovieDescription);
        tvDescription.setText(movieDescription);

        tvGenre=findViewById(R.id.tvGenre);
        tvGenre.setText(movieGenre);

        rbMovieRating=findViewById(R.id.rbMovieRating);
        rbMovieRating.setRating(movieRating);

        ivMoviePoster=findViewById(R.id.ivMoviePoster);
        ivMoviePoster.setImageBitmap(decodeImageFromString(moviePhoto));

        tvMovieLink=findViewById(R.id.tvMovieLink);
        tvMovieLink.setText(movieLink);

        tvMovieLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra(Constants.MOVIE_LINK,movieLink);
                startActivity(intent);
            }
        });

    }

    private Bitmap decodeImageFromString(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodeByte;

    }
}
