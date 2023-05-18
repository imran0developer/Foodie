package com.imran.foodie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imran.foodie.HomeActivity;
import com.imran.foodie.R;
import com.imran.foodie.models.Category;
import com.imran.foodie.models.Story;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{

    private Context context ;
    private ArrayList<Category> categoryArrayList;

    public CategoryAdapter(Context context, ArrayList<Category> categoryArrayList) {
        this.context = context;
        this.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_rv, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category model = categoryArrayList.get(position);

        String categoryImage = model.getCategoryImage();
        String categoryName = model.getCategoryName();

        holder.category_name.setText(categoryName);

        Picasso.get()
                .load(categoryImage)
                .into(holder.category_iv);

        holder.category_name.setOnClickListener(view -> {
            HomeActivity.filterSearch(categoryName);
        });
        holder.category_iv.setOnClickListener(view -> {
            HomeActivity.filterSearch(categoryName);
        });
        //   //Log.d("TAG2","Image is "+categoryImage);
    }



    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView category_iv ;
        private TextView category_name ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category_iv = itemView.findViewById(R.id.category_iv);
            category_name = itemView.findViewById(R.id.category_name);

        }
    }
}
