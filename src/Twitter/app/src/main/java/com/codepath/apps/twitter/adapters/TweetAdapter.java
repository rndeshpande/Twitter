package com.codepath.apps.twitter.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.activities.DetailsActivity;
import com.codepath.apps.twitter.databinding.ListTweetBinding;
import com.codepath.apps.twitter.models.Tweet;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by rdeshpan on 9/25/2017.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>  {
    private ArrayList<Tweet> mTweets;
    private Context mContext;

    public TweetAdapter(Context context, ArrayList<Tweet> tweets) {
        mContext = context;
        mTweets = tweets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {
        final ViewHolder viewHolder;
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView;

        itemView = inflater.inflate(R.layout.list_tweet, parent, false);
        viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Tweet tweet = mTweets.get(position);
        viewHolder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ListTweetBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = ListTweetBinding.bind(itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(Tweet tweet) {
            binding.setTweet(tweet);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Tweet tweet = mTweets.get(position);
                Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra("tweet_details", Parcels.wrap(Tweet.class, tweet));
                mContext.startActivity(intent);
            }
        }
    }
}
