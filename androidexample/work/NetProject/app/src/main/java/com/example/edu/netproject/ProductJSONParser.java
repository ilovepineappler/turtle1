package com.example.edu.netproject;

import com.google.gson.Gson;

public class ProductJSONParser {
    private static final String TAG = "NetApp";
    public static ProductList listParser(String s){
        return new Gson().fromJson(s, ProductList.class);
    }

    public static Product parser(String s){

//        Gson gson = new Gson();
        return  new Gson().fromJson(s, Product.class);
//        Product product = new Product();
////        JSONObject jsonObject = null;
////        try{
////            jsonObject = new JSONObject(s);
////            product.setName(jsonObject.getString("name"));
////            product.setPrice(jsonObject.getInt("price"));
////        }catch(JSONException e){
////            Log.v(TAG, "parse e : " + e);
////        }
//        return product;
    }
}
