package com.coolweather.weather1.util;

import android.support.annotation.Nullable;

import com.coolweather.weather1.db.*;

import com.google.gson.Gson;
import com.coolweather.weather1.gson.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Utility {


    //deal with json_province
    public static boolean handleProvinceResponse(String response){

        try {
            JSONArray allProvince = new JSONArray(response);
            for(int i =0;i<allProvince.length();i++){
                //analysis
                String provinceFromjson = allProvince.getJSONObject(i).toString();
                Gson gson = new Gson();
                provincejson province_gson = gson.fromJson(provinceFromjson, provincejson.class);
                //analysis finish

                //add to database,litepal
                Province province_tmp = new Province();
                province_tmp.setProvinceName(province_gson.getName());
                province_tmp.setProvinceCode(province_gson.getId());
                province_tmp.save();
                //finish
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    //deal with json of city
    public static boolean handleCityResponse(String response,int provinceId){
        try {
            JSONArray allcity = new JSONArray(response);
            for(int i =0;i<allcity.length();i++){
                String cityContentFromjson = allcity.getJSONObject(i).toString();
                Gson gson = new Gson();
                cityjson city_gson = gson.fromJson(cityContentFromjson, cityjson.class);

                City city_tmp = new City();
                city_tmp.setProvinceId(provinceId);
                city_tmp.setCityName(city_gson.getName());
                city_tmp.setCityCode(city_gson.getId());

                city_tmp.save();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //deal with data for county
    public static boolean handleCountyResponse(String response,int cityId){
        try {
            JSONArray allcounty = new JSONArray(response);
            for(int i= 0;i<allcounty.length();i++){
                String countyContentFromjson = allcounty.getJSONObject(i).toString();
                Gson gson = new Gson();
                countyjson county_gson = gson.fromJson(countyContentFromjson, countyjson.class);

                County county_tmp = new County();

                county_tmp.setCityId(cityId);

                county_tmp.setCountyName(county_gson.getName());
                county_tmp.setWeatherId(county_gson.getWeather_id());

                county_tmp.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Nullable
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);

            Gson g=new Gson();
            Weather weather=  g.fromJson(jsonObject.toString(),Weather.class);

           return weather;

        } catch (Exception e) {
            e.printStackTrace();


        }
        return null;

    }


    public static AQI handleAQIResponse(String response) {

        try {


            JSONObject jsonObject = new JSONObject(response);

            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");

            String weatherContent = jsonArray.getJSONObject(0).toString();

            Gson g=new Gson();
            AQI aqi=  g.fromJson(jsonObject.toString(),AQI.class);

            return aqi;

        } catch (Exception e) {
            e.printStackTrace();


        }
        return null;

    }

}
