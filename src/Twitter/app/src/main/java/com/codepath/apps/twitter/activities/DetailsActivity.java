package com.codepath.apps.twitter.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TwitterApp;
import com.codepath.apps.twitter.TwitterClient;
import com.codepath.apps.twitter.databinding.ActivityDetailsBinding;
import com.codepath.apps.twitter.models.Tweet;
import com.codepath.apps.twitter.models.TweetExtended;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.codepath.apps.twitter.R.id.swipeContainer;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "TwitterClient";
    private ActivityDetailsBinding mBinding;
    private TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
        populateView();
    }

    private void initialize() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/HelveticaNeue.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        client = TwitterApp.getRestClient();
    }

    private void populateView() {
        Tweet tweet = (Tweet)Parcels.unwrap(getIntent().getParcelableExtra("tweet_details"));

        Log.d(TAG, "Getting tweet by Id");
        client.getTweetById(tweet.getUuid(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(TAG, Integer.toString(statusCode));
                Log.d(TAG, response.toString());
                TweetExtended tweetExtended = new TweetExtended();
                try {
                    tweetExtended = TweetExtended.fromJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mBinding.setTweet(tweetExtended);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseArray) {
                Log.d(TAG, responseArray.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d(TAG, errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d(TAG, errorResponse.toString());
                throwable.printStackTrace();
            }
        });
    }
}
