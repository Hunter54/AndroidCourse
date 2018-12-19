package com.example.ionut.androidcourse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ionut.androidcourse.dummy.DummyContent;
import com.example.ionut.androidcourse.ui.fragment.MovieDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MovieListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private ArrayList<Movie> mDataset;
    private AdapterClickListener clickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mDataset= new DummyContent().getMoviesList();
        if (findViewById(R.id.movie_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.movie_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new MovieItemRecyclerViewAdapter(this, mDataset, mTwoPane));
    }

    public static class MovieItemRecyclerViewAdapter extends RecyclerView.Adapter<MovieItemRecyclerViewAdapter.MovieViewHolder> {

        private final MovieListActivity mParentActivity;
        private final List<Movie> mMovies;
        private final boolean mTwoPane;
        private final AdapterClickListener mOnClickListener = new AdapterClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie item = mMovies.get(position);
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(Constants.MOVIE_NAME, item.getName());
                    arguments.putString(Constants.MOVIE_DESCRIPTION, item.getShortDescription());
                    arguments.putFloat(Constants.MOVIE_RATING, item.getRating());
                    arguments.putString(Constants.MOVIE_GENRE, item.getGenre());
                    arguments.putString(Constants.MOVIE_LINK, item.getLink());
                    arguments.putString(Constants.MOVIE_PHOTO, item.getPhotoBase64());
                    MovieDetailFragment fragment = new MovieDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.movie_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra(Constants.MOVIE_NAME, item.getName());
                    intent.putExtra(Constants.MOVIE_DESCRIPTION, item.getShortDescription());
                    intent.putExtra(Constants.MOVIE_RATING, item.getRating());
                    intent.putExtra(Constants.MOVIE_GENRE, item.getGenre());
                    intent.putExtra(Constants.MOVIE_LINK, item.getLink());
                    intent.putExtra(Constants.MOVIE_PHOTO, item.getPhotoBase64());
                    context.startActivity(intent);
                }
            }
        };

        MovieItemRecyclerViewAdapter(MovieListActivity parent, List<Movie> items, boolean twoPane) {
            this.mMovies = items;
            this.mParentActivity = parent;
            this.mTwoPane = twoPane;
        }

        @Override
        public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movies, parent, false);
            MovieViewHolder vh = new MovieViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MovieViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            Movie movie = mMovies.get(position);
            holder.tvMovieName.setText(movie.getName());
            holder.tvMovieGenre.setText(movie.getGenre());
            holder.rbMovieRating.setRating(movie.getRating());
            holder.ivMovieIcon.setImageBitmap(decodeImageFromString(movie.getPhotoBase64()));
            holder.tvMovieDescription.setText(movie.getShortDescription());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onItemClick(v, position);
                }
            });

        }
        private Bitmap decodeImageFromString(String base64) {
            byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
            Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodeByte;

        }

        @Override
        public int getItemCount() {
            return mMovies.size();
        }

        public class MovieViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView tvMovieName;
            public TextView tvMovieGenre;
            public RatingBar rbMovieRating;
            public ImageView ivMovieIcon;
            public TextView tvMovieDescription;

            public MovieViewHolder(RelativeLayout v) {
                super(v);
//                v.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        clickListener.onItemClick(v, getAdapterPosition());
//                    }
//                });
                tvMovieName = v.findViewById(R.id.tvName);
                tvMovieGenre = v.findViewById(R.id.tvMovieGenre);
                rbMovieRating = v.findViewById(R.id.rbRating);
                ivMovieIcon = v.findViewById(R.id.ivMovieIcon);
                tvMovieDescription = v.findViewById(R.id.tvDescription);
            }
        }
    }
}
