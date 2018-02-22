package com.example.jocelyn.mychecklist;


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class APIAdapter {

    private RequestQueue queue;

    public APIAdapter(RequestQueue queue) {
        this.queue = queue;
    }

    public void queryItem(String name, final SearchListener listener) {

        //If we are running in the emulator, just pretend to do stuff
        if (MainActivity.isEmulator()) {
            Price price = new Price(123.45);
            Item item = new Item("SIMULATED: " + name, price);

            listener.onComplete(item);
            return;
        }


        String url = String.format(
                "http://www.mocky.io/v2/5a8c9a783000005700323f9c",
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
