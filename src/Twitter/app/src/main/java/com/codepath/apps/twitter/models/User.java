package com.codepath.apps.twitter.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rdeshpan on 9/25/2017.
 */

public class User {
    public String name;
    public long uuid;
    public String screenName;
    public String profileImageUrl;
    public boolean verified;

    public static User fromJSON(JSONObject jsonObject) throws JSONException{
        User user = new User();
        user.name = jsonObject.getString("name");
        user.uuid = jsonObject.getLong("id");
        user.screenName = jsonObject.getString("screen_name");
        user.profileImageUrl = jsonObject.getString("profile_image_url");
        user.verified = jsonObject.getBoolean("verified");
        return user;
    }
}
