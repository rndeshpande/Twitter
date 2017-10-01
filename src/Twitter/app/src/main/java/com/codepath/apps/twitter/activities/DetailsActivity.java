package com.codepath.apps.twitter.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TwitterApp;
import com.codepath.apps.twitter.TwitterClient;
import com.codepath.apps.twitter.databinding.ActivityDetailsBinding;
import com.codepath.apps.twitter.fragments.CreateDialogFragment;
import com.codepath.apps.twitter.models.Tweet;
import com.codepath.apps.twitter.models.TweetExtended;
import com.codepath.apps.twitter.models.TweetRequest;
import com.codepath.apps.twitter.models.VideoVariant;
import com.codepath.apps.twitter.utils.CommonUtils;
import com.codepath.apps.twitter.utils.TestDataHelper;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class DetailsActivity extends AppCompatActivity implements CreateDialogFragment.OnFragmentInteractionListener{

    private static final String TAG = "TwitterClient";
    private ActivityDetailsBinding mBinding;
    ImageView ivComment;
    VideoView vvMediaVideo;
    ImageView ivMediaImage;
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
                .setDefaultFontPath("fonts/Helvetica Neue Bold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        client = TwitterApp.getRestClient();
    }

    public void onCommentClick(View view) {
        TweetRequest tweetRequest = new TweetRequest("", mBinding.getTweet().getUuid(), mBinding.getTweet().getUser().getScreenName());
        CreateDialogFragment dialogFragment = CreateDialogFragment.newInstance(Parcels.wrap(tweetRequest));
        dialogFragment.show(DetailsActivity.this.getSupportFragmentManager(), "fragment_create_dialog");
    }

    private void populateView() {
        Tweet tweet = (Tweet)Parcels.unwrap(getIntent().getParcelableExtra("tweet_details"));
        client.getTweetById(tweet.getUuid(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                TweetExtended tweetExtended = new TweetExtended();
                try {
                    tweetExtended = TweetExtended.fromJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mBinding.setTweet(tweetExtended);
                setupMediaDisplay(tweetExtended);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseArray) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                throwable.printStackTrace();
            }
        });
    }
    @Override
    public void onFragmentInteraction(TweetRequest tweetRequest) {
        postTweet(tweetRequest);
    }

    // TODO : move this to a separate class
    private void postTweet(TweetRequest tweetRequest) {
        client.postTweet(tweetRequest, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                CommonUtils.showMessage(mBinding.getRoot(), getString(R.string.tweet_posted));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseArray) {
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                throwable.printStackTrace();
            }
        });
    }

    private void setupMediaDisplay(TweetExtended tweetExtended) {

        // Play video if available
        if(tweetExtended.entitiesExtended.media != null
                && tweetExtended.entitiesExtended.media.size() > 0
                && tweetExtended.entitiesExtended.media.get(0).videoInfo.getVariants().size() > 0
                && tweetExtended.entitiesExtended.media.get(0).videoInfo.getVariants().get(0).getUrl() != "") {

            ivMediaImage = mBinding.ivMediaImage;
            ivMediaImage.setVisibility(View.GONE);
            vvMediaVideo = mBinding.vvMediaVideo;
            vvMediaVideo.setVideoPath(tweetExtended.entitiesExtended.media.get(0).videoInfo.getVariants().get(0).getUrl());
            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(vvMediaVideo);
            vvMediaVideo.setMediaController(mediaController);
            vvMediaVideo.requestFocus();
            vvMediaVideo.setMediaController(null);
            vvMediaVideo.setOnPreparedListener(mp -> {
                mp.setLooping(true);
                mp.start();
            });

        }
    }

    // TODO: remove below test data helper
    private void populateTestData() {
        TweetExtended tweetExtended = TestDataHelper.getTweetExtended();
        mBinding.setTweet(tweetExtended);
    }
}
