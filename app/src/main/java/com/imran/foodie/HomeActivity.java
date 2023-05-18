package com.imran.foodie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.imran.foodie.adapters.CategoryAdapter;
import com.imran.foodie.adapters.RestaurantsAdapter;
import com.imran.foodie.adapters.StoryAdapter;
import com.imran.foodie.api.ApiClient;
import com.imran.foodie.api.ApiSet;
import com.imran.foodie.models.Category;
import com.imran.foodie.models.RestaurantData;
import com.imran.foodie.models.RestaurantsModel;
import com.imran.foodie.models.Story;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION = 987;
    private static final String TAG = "TAG2";
    public ArrayList<Story> stories;
    public ArrayList<Category> categories;
    public  static ArrayList<RestaurantData> restaurants;

    public StoryAdapter storyAdapter;
    public CategoryAdapter categoryAdapter;
    public static RestaurantsAdapter restaurantsAdapter;
    public RecyclerView storiesRv, categoriesRv, restaurantsRv;

    public LinearLayoutManager linearLayoutManager;
    public android.widget.SearchView searchView;

    String formattedLat,formattedLng;
    String searched_txt; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        stories = new ArrayList<>();
        categories = new ArrayList<>();
        restaurants = new ArrayList<>();

        storiesRv = findViewById(R.id.story_rv);
        categoriesRv = findViewById(R.id.category_rv);
        restaurantsRv = findViewById(R.id.restaurants_rv);
        searchView = findViewById(R.id.search_view);
        

        requestLocationPermission();

        populateStories();
        populateCategories();
        populateRestaurants();

        setStoriesRv();
        setCategoriesRv();
        setRestaurantsRv();

        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searched_txt =s;

                filterSearch(s); // this method is static because
                // we use it in restrauntAdapter and categoryAdapter to filter the list
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searched_txt =s;

                filterSearch(s);
                // this method is static because
                // we use it in restrauntAdapter and categoryAdapter to filter the list
                return true;
            }
        });


    }

    public static void filterSearch(String s) {
        //here the items are filtered according to search
        //and its a static method so we are calling it in product adapter
        //to filter the list directly from adapter

        ArrayList<RestaurantData> filteredlist = new ArrayList<>();

        for (RestaurantData item : restaurants) {
            if (item.getTags().toLowerCase().contains(s.toLowerCase())
                    ||item.getName().contains(s)) {
                filteredlist.add(0,item);
            }
        }
        if (filteredlist.isEmpty()) {

        } else {
            restaurantsAdapter.filteringList(filteredlist);
        }

    }
    private void populateStories() {

        stories.add(new Story("https://cdn.pixabay.com/photo/2022/05/22/13/21/healthy-7213383_640.jpg"));
        stories.add(new Story("https://cdn.pixabay.com/photo/2017/07/15/13/45/french-restaurant-2506490_640.jpg"));
        stories.add(new Story("https://cdn.pixabay.com/photo/2020/02/01/06/13/vegan-4809593_640.jpg"));
        stories.add(new Story("https://cdn.pixabay.com/photo/2016/08/23/23/10/breakfast-1615784_640.jpg"));
        stories.add(new Story("https://cdn.pixabay.com/photo/2017/08/06/20/36/green-2596087_640.jpg"));
        stories.add(new Story("https://cdn.pixabay.com/photo/2017/02/06/19/38/hamburger-2044036_640.jpg"));
        stories.add(new Story("https://cdn.pixabay.com/photo/2016/06/26/22/46/india-1481504_640.jpg"));

    }

    private void populateCategories() {

        categories.add(new Category("https://cdn-icons-png.flaticon.com/128/1404/1404945.png", "Pizza"));
        categories.add(new Category("https://cdn-icons-png.flaticon.com/128/3075/3075977.png", "Burger"));
        categories.add(new Category("https://cdn-icons-png.flaticon.com/128/2912/2912292.png", "Fries"));
        categories.add(new Category("https://cdn-icons-png.flaticon.com/128/3496/3496508.png", "Donut"));
        categories.add(new Category("https://cdn-icons-png.flaticon.com/128/3041/3041130.png", "Noodles"));
        categories.add(new Category("https://cdn-icons-png.flaticon.com/128/7627/7627769.png", "Sandwich"));
        categories.add(new Category("https://cdn-icons-png.flaticon.com/128/5062/5062567.png", "Chicken"));
        categories.add(new Category("https://cdn-icons-png.flaticon.com/128/4329/4329534.png", "Juice"));
        categories.add(new Category("https://cdn-icons-png.flaticon.com/128/541/541769.png", "Shawarma"));

    }

//    private void populateRestaurants(double latitude , double longitude) {
    private void populateRestaurants() {
        startLocationUpdates();
        Log.d(TAG, "populateRestaurants: "+formattedLat+" "+formattedLng);

            Retrofit retrofit = ApiClient.getClient();
            ApiSet apiSet = retrofit.create(ApiSet.class);

        RequestBody requestBody = new FormBody.Builder()
                .add("lat", "22.66")
                .add("lng", "59.66")
                //any value we pass the response is same
                .build();

            apiSet.getResturants(requestBody).enqueue(new Callback<RestaurantsModel>() {
                @Override
                public void onResponse(Call<RestaurantsModel> call, Response<RestaurantsModel> response) {
                    if (response.body()!=null){
                        Toast.makeText(HomeActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: "+response.body().getStatus());
                        restaurants.addAll(response.body().getData());
                        restaurantsAdapter.notifyDataSetChanged();

                    }else{
                        Toast.makeText(HomeActivity.this, "its null body", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: its null");
                    }
                }

                @Override
                public void onFailure(Call<RestaurantsModel> call, Throwable t) {
                    Toast.makeText(HomeActivity.this, "error is "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onFail "+t.getLocalizedMessage());
                     // if api fails
                }
            });

        }


    private void setStoriesRv() {

        storyAdapter = new StoryAdapter(this, stories);
        storiesRv.setHasFixedSize(true);
        storiesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        storiesRv.setAdapter(storyAdapter);

    }

    private void setCategoriesRv() {
        categoryAdapter = new CategoryAdapter(this, categories);
        categoriesRv.setHasFixedSize(true);
        categoriesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRv.setAdapter(categoryAdapter);
    }

    private void setRestaurantsRv() {
        restaurantsAdapter = new RestaurantsAdapter(this, restaurants);
        restaurantsRv.setHasFixedSize(true);
        restaurantsRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        restaurantsRv.setAdapter(restaurantsAdapter);
    }

    private void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            startLocationUpdates();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                // Permission denied
                // Handle accordingly (e.g., show an error message or disable location-based features)
            }
        }
    }

    private void startLocationUpdates() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude= location.getLatitude();
                    double longitude= location.getLongitude();

                 formattedLat = String.format("%.2f", latitude);
                 formattedLng = String.format("%.2f", longitude);

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    Log.d(TAG, "onStatusChanged: "+status);
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                }
            };

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            // GPS is disabled
            // Handle accordingly (e.g., show an error message or prompt the user to enable GPS)
        }
    }



}