package com.codepath.apps.twitter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.databinding.ListTweetBinding;
import com.codepath.apps.twitter.models.Tweet;

import java.util.ArrayList;

/**
 * Created by rdeshpan on 9/25/2017.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ListTweetBinding binding;

        public TextView tvHeadline;

        public TextView tvSnippet;

        public TextView tvSectionName;

        public ImageView ivThumbnail;

        public Button btnShare;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = ListTweetBinding.bind(itemView);
        }

        public void bind(Tweet tweet) {
            binding.setTweet(tweet);
            binding.executePendingBindings();
        }
    }
}
