package com.imran.foodie.models;

import java.util.ArrayList;
import java.util.List;

public class RestaurantsModel {
    private String status;
    private String code;
    private List<RestaurantData> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public List<RestaurantData> getData() {
        return data;
    }

    public void setData(List<RestaurantData> data) {
        this.data = data;
    }
}
