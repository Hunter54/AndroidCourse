package com.example.ionut.androidcourse;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MovieViewHolder> {
    private ArrayList<Movie> mDataset;
    private AdapterClickListener clickListener;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvMovieName;
        public TextView tvMovieGenre;
        public RatingBar rbMovieRating;
        public ImageView ivMovieIcon;
        public TextView tvMovieDescription;

        public MovieViewHolder(RelativeLayout v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(v, getAdapterPosition());
                }
            });
            tvMovieName = v.findViewById(R.id.tvName);
            tvMovieGenre = v.findViewById(R.id.tvMovieGenre);
            rbMovieRating = v.findViewById(R.id.rbRating);
            ivMovieIcon = v.findViewById(R.id.ivMovieIcon);
            tvMovieDescription = v.findViewById(R.id.tvDescription);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Movie> myDataset, AdapterClickListener clickListener) {
        mDataset = myDataset;
        this.clickListener = clickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movies, parent, false);
        MovieViewHolder vh = new MovieViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Movie movie = mDataset.get(position);
        holder.tvMovieName.setText(movie.getName());
        holder.tvMovieGenre.setText(movie.getGenre());
        holder.rbMovieRating.setRating(movie.getRating());
        holder.ivMovieIcon.setImageBitmap(decodeImageFromString(movie.getPhotoBase64()));
        holder.tvMovieDescription.setText(movie.getShortDescription());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private Bitmap decodeImageFromString(String base64) {
        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodeByte;

    }
}
