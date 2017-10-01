package com.codepath.apps.twitter.providers;

import com.codepath.apps.twitter.models.TweetModel;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

/**
 * Created by rdeshpan on 9/30/2017.
 */

public class DataProvider {

    public ArrayList<TweetModel> readItems() {
        ArrayList<TweetModel> tweetModels = (ArrayList) SQLite.select().from(TweetModel.class).queryList();
        return tweetModels;
    }

    public void saveDraft(long uuid, String status, String userName, String screenName, String createdAt, Boolean verified, Boolean isDraft) {
        TweetModel tweetModel = new TweetModel(uuid, status, userName, screenName, createdAt, verified, isDraft);
        tweetModel.insert();
    }

    public void deleteAll() {
        SQLite.delete().from(TweetModel.class).query();
    }
}
