package com.imran.foodie.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imran.foodie.R;
import com.imran.foodie.models.RestaurantData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder> {

    private static final String TAG = "TAG2";
    private Context context;
    private List<RestaurantData> restaurantList;

    public RestaurantsAdapter(Context context, List<RestaurantData> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.resturants_rv, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        RestaurantData restaurant = restaurantList.get(position);

        String name = restaurant.getName();
        String tags = formatTags(restaurant.getTags()); // Format tags with bullet points
        String rating = restaurant.getRating();
        String discount = restaurant.getDiscount() + "% off";
        String primaryImage = restaurant.getPrimaryImage();

        holder.resName.setText(name);
        holder.resTags.setText(tags);
        holder.resRating.setText(rating);
        holder.resOffer.setText(discount);

/*        Log.d(TAG, "Name: " + name);
        Log.d(TAG, "Tags: " + tags);
        Log.d(TAG, "Rating: " + rating);
        Log.d(TAG, "Discount: " + discount);
        Log.d(TAG, "Primary Image: " + primaryImage);*/

        Picasso.get()
                .load(primaryImage)
                .into(holder.resImage);
    }

    // Helper method to format tags with bullet points
    private String formatTags(String tags) {
        String[] tagArray = tags.split(", ");
        StringBuilder formattedTags = new StringBuilder();
        for (String tag : tagArray) {
            formattedTags.append("• ").append(tag).append(" ");
        }
        return formattedTags.toString();
    }

    public void  filteringList(ArrayList<RestaurantData> allFilteredNotes){
        restaurantList = allFilteredNotes;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView resName, resTags, resRating, resOffer;
        ImageView resImage;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            resName = itemView.findViewById(R.id.res_name);
            resTags = itemView.findViewById(R.id.res_tags);
            resRating = itemView.findViewById(R.id.res_ratings);
            resOffer = itemView.findViewById(R.id.res_offer);
            resImage = itemView.findViewById(R.id.res_image);
        }
    }
}
