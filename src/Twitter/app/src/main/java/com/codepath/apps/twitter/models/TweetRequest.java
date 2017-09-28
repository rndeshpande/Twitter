package com.codepath.apps.twitter.models;

/**
 * Created by rdeshpan on 9/26/2017.
 */

public class TweetRequest {
    public TweetRequest(String status) {
        this.status = status;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
