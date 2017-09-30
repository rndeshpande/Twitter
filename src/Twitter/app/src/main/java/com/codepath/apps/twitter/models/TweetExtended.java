package com.codepath.apps.twitter.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by rdeshpan on 9/29/2017.
 */
@Parcel
public class TweetExtended {
    public String body;
    public long uuid;
    public User user;
    public String createdAt;

    public  TweetExtended() {

    }
    public TweetExtended(String body, long uuid, String createdAt, User user) {
        this.body = body;
        this.uuid = uuid;
        this.createdAt = createdAt;
        this.user = user;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static TweetExtended fromJSON(JSONObject jsonObject) throws JSONException {
        TweetExtended tweetExtended= new TweetExtended();
        tweetExtended.body = jsonObject.getString("full_text");
        tweetExtended.uuid = jsonObject.getLong("id");
        tweetExtended.createdAt = jsonObject.getString("created_at");
        tweetExtended.user = User.fromJSON(jsonObject.getJSONObject("user"));

        return tweetExtended;
    }
}
