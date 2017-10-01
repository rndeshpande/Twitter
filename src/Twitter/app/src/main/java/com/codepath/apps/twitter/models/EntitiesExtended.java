package com.codepath.apps.twitter.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by rdeshpan on 10/1/2017.
 */

@Parcel
public class EntitiesExtended {
    public ArrayList<Media> media;

    public static EntitiesExtended fromJSON(JSONObject jsonObject) throws JSONException {
        EntitiesExtended entitiesExtended = new EntitiesExtended();
        entitiesExtended.media = new ArrayList<>();
        Log.d("TwitterClient", jsonObject.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("media");

        for(int i=0; i< jsonArray.length();i++) {
            try {
                Media media = Media.fromJSON(jsonArray.getJSONObject(i));
                Log.d("TwitterClient", jsonArray.getJSONObject(i).toString());
                entitiesExtended.media.add(media);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return entitiesExtended;
    }
}
