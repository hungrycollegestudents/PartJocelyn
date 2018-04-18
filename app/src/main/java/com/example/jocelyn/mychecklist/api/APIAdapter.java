package com.example.jocelyn.mychecklist.api;


import android.os.Build;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.jocelyn.mychecklist.model.Item;
import com.example.jocelyn.mychecklist.model.Price;

import org.json.JSONException;
import org.json.JSONObject;


public class APIAdapter {

    private RequestQueue queue;

    public APIAdapter(RequestQueue queue) {
        this.queue = queue;
    }

    public boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    public void queryItem(String name, final SearchListener listener) {

        //If we are running in the emulator, just pretend to do stuff
        if (isEmulator()) {
            System.out.println("Simulating API query");
            Price price = new Price(123.45);
            Item item = new Item(name, price);

            listener.onComplete(item);
            return;
        }


        String url = String.format(
                "https://grocery-list-cs3354.herokuapp.com/search?name=%s",
                name);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonItem = response.getJSONArray("items").getJSONObject(0);

                            String name = jsonItem.getString("name");
                            Price price = new Price(jsonItem.getDouble("price"));

                            Item item = new Item(name, price);

                            listener.onComplete(item);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("idk", error.toString());

                    }
                });

        queue.add(jsObjRequest);
    }

    public abstract static class SearchListener {

        public abstract void onComplete(Item item);

    }

}
