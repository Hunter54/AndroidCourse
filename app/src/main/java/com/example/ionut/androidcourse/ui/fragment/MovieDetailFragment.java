package com.example.ionut.androidcourse.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ionut.androidcourse.Constants;
import com.example.ionut.androidcourse.MovieDetailActivity;
import com.example.ionut.androidcourse.MovieListActivity;
import com.example.ionut.androidcourse.R;
import com.example.ionut.androidcourse.ui.activity.WebActivity;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {
    private TextView tvMovieLink, tvMovieName, tvDescription, tvGenre;
    private ImageView ivMoviePoster;
    private RatingBar rbMovieRating;
    private String movieName = "";
    private String movieDescription = "";
    private String movieGenre = "";
    private Float movieRating = 0.5f;
    private String moviePhoto = "";
    private String movieLink = "";
    private int movieYear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            movieName = bundle.getString(Constants.MOVIE_NAME);
            movieDescription = bundle.getString(Constants.MOVIE_DESCRIPTION);
            movieGenre = bundle.getString(Constants.MOVIE_GENRE);
            movieRating = bundle.getFloat(Constants.MOVIE_RATING);
            movieYear = bundle.getInt(Constants.MOVIE_YEAR);
            moviePhoto = bundle.getString(Constants.MOVIE_PHOTO);
            movieLink = bundle.getString(Constants.MOVIE_LINK);
        }
        tvMovieName = view.findViewById(R.id.tvMovieName);
        tvMovieName.setText(movieName);

        tvDescription = view.findViewById(R.id.tvMovieDescription);
        tvDescription.setText(movieDescription);

        tvGenre = view.findViewById(R.id.tvGenre);
        tvGenre.setText(movieGenre);

        rbMovieRating = view.findViewById(R.id.rbMovieRating);
        rbMovieRating.setRating(movieRating);

        ivMoviePoster = view.findViewById(R.id.ivMoviePoster);
        ivMoviePoster.setImageBitmap(decodeImageFromString(moviePhoto));

        tvMovieLink = view.findViewById(R.id.tvMovieLink);
        tvMovieLink.setText("Link IMDb");

        tvMovieLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra(Constants.MOVIE_LINK, movieLink);
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
