package com.imran.foodie.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imran.foodie.R;
import com.imran.foodie.models.Story;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private Context context ;
    private ArrayList<Story> storyArrayList;

    public StoryAdapter(Context context, ArrayList<Story> storyArrayList) {
        this.context = context;
        this.storyArrayList = storyArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story model = storyArrayList.get(position);

        String storyImage = model.getStoryImage();

        Picasso.get()
                .load(storyImage)
                .into(holder.story_iv);
     //   //Log.d("TAG2","Image is "+storyImage);
    }


    @Override
    public int getItemCount() {
        return storyArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView story_iv ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            story_iv = itemView.findViewById(R.id.story_iv);

        }
    }
}