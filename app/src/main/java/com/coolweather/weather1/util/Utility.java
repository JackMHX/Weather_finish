package com.coolweather.weather1.util;

import android.text.TextUtils;

import com.coolweather.weather1.db.City;
import com.coolweather.weather1.db.County;
import com.coolweather.weather1.db.Province;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    //deal with json_province
    public static boolean handleProvinceResponse(String response){

        try {
            JSONArray allProvince = new JSONArray(response);
            for(int i =0;i<allProvince.length();i++){
                //JSONObject provinceObject = allProvince.getJSONObject(i);
                String provinceContentfromjson = allProvince.getJSONObject(i).toString();
                Gson gson = new Gson();
                Province province = gson.fromJson(provinceContentfromjson,Province.class);
                province.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    //deal with json of city
    public static boolean handleCityResponse(String response){
        try {
            JSONArray allcity = new JSONArray(response);
            for(int i =0;i<allcity.length();i++){
                String cityContentFromjson = allcity.getJSONObject(i).toString();
                Gson gson = new Gson();
                City city = gson.fromJson(cityContentFromjson,City.class);
                city.save();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //deal with data for county
    public static Boolean handleCountyResponse(String response){
        try {
            JSONArray allcounty = new JSONArray(response);
            for(int i= 0;i<allcounty.length();i++){
                String countyContentFromjson = allcounty.getJSONObject(i).toString();
                Gson gson = new Gson();
                County county = gson.fromJson(countyContentFromjson,County.class);
                county.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);

                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);

                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();

                }

                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public static boolean handleCityResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allcities = new JSONArray(response);

                for (int i = 0; i < allcities.length(); i++) {
                    JSONObject jsonObject = allcities.getJSONObject(i);

                    City city = new City();
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setCityName(jsonObject.getString("name"));
                    city.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public static boolean handleCountyResponse(String response) {

        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties = new JSONArray(response);

                for(int i = 0;i<allCounties.length();i++) {
                    JSONObject jsonObject = allCounties.getJSONObject(i);

                    County county = new County();
                    county.setCountyName(jsonObject.getString("name"));
                    county.setWeatherId(jsonObject.getString("weather_id"));
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false;
    }*/

}