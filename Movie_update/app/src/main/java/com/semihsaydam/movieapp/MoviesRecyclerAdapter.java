package com.semihsaydam.movieapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.PostHolder> {

    private ArrayList<String> userEmailList;
    private ArrayList<String> userMovieList;
    private ArrayList<String> userImageList;

    public MoviesRecyclerAdapter(ArrayList<String> userEmailList, ArrayList<String> userMovieList, ArrayList<String> userImageList) {
        this.userEmailList = userEmailList;
        this.userMovieList = userMovieList;
        this.userImageList = userImageList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { ///if you create view holder make this
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()); ///inflate layouts
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);
        return new PostHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) { ////if you connect view holder make this
        holder.userEmailText.setText(userEmailList.get(position));
        holder.movieText.setText(userMovieList.get(position));
        Picasso.get().load(userImageList.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {  ///Recycler View İtem Count
        return userEmailList.size();
    } //PostHolder is Visual Holder<VH>


    class PostHolder extends RecyclerView.ViewHolder{///Define Recycler_row views

        ImageView imageView;
        TextView userEmailText;
        TextView movieText;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recyclerview_row_imageview);   ///Define row views
            userEmailText = itemView.findViewById(R.id.recyclerview_row_useremail_text);
            movieText = itemView.findViewById(R.id.recyclerview_row_movie_text);
        }
    }
}
