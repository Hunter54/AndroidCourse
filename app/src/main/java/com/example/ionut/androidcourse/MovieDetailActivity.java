package com.example.ionut.androidcourse;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ionut.androidcourse.databinding.ActivityMainBinding;
import com.example.ionut.androidcourse.ui.activity.WebActivity;

/**
 * An activity representing a single Movie detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MovieListActivity}.
 */
public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();
    private TextView tvMovieLink;
    private TextView tvMovieName;
    private TextView tvGenre;
    private TextView tvDescription;
    private ImageView ivMoviePoster;
    private RatingBar rbMovieRating;

    private String movieName = "";
    private String movieGenre = "";
    private String moviePhotoBase64 = "";
    private float movieRating = 0;
    private String movieDescription = "";
    private String movieLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding;
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setContentView(R.layout.activity_main);
        Bundle bundle= getIntent().getExtras();
        if(bundle != null)
        {
            try{
                movieName = bundle.getString(Constants.MOVIE_NAME);
                movieGenre = bundle.getString(Constants.MOVIE_GENRE);
                moviePhotoBase64 = bundle.getString(Constants.MOVIE_PHOTO);
                movieRating = bundle.getFloat(Constants.MOVIE_RATING);
                movieDescription = bundle.getString(Constants.MOVIE_DESCRIPTION);
                movieLink = bundle.getString(Constants.MOVIE_LINK);
            } catch (Exception ex){
                ex.printStackTrace();
                Toast.makeText(this,"Crashed and died", Toast.LENGTH_LONG).show();
            }
        }
        tvDescription=binding.tvMovieDescription;
        tvMovieName=binding.tvMovieName;
        ivMoviePoster=binding.ivMoviePoster;
        tvGenre=binding.tvGenre;
        tvMovieLink=binding.tvMovieLink;
        rbMovieRating=binding.rbMovieRating;

        tvDescription.setText(movieDescription);
        tvMovieName.setText(movieName);
        tvGenre.setText(movieGenre);
        tvMovieLink.setText(movieLink);
        rbMovieRating.setRating(movieRating);
        ivMoviePoster.setImageBitmap(decodeImageFromString(moviePhotoBase64));

        tvMovieLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MovieDetailActivity.this, WebActivity.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private Bitmap decodeImageFromString(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodeByte;

    }
}
