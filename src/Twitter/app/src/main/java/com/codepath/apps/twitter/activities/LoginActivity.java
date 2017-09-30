package com.codepath.apps.twitter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TwitterClient;
import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class LoginActivity extends OAuthLoginActionBarActivity<TwitterClient> {

	TwitterLoginButton btnTwitterLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        Twitter.initialize(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
        setupTwitterLoginButton();
	}

	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"
	@Override
	public void onLoginSuccess() {
        Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);
	}

	// OAuth authentication flow failed, handle the error
	// i.e Display an error dialog or toast
	@Override
	public void onLoginFailure(Exception e) {
		e.printStackTrace();
	}

	// Click handler method for the button used to start OAuth flow
	// Uses the client to initiate OAuth authorization
	// This should be tied to a button used to login
	public void loginToRest(View view) {
		getClient().connect();
	}

	private void setupTwitterLoginButton() {
        Log.d("TWITTERCLIENT", "Initializing");

        btnTwitterLogin = (TwitterLoginButton) findViewById(R.id.btnTwitterLogin);
        btnTwitterLogin.setCallback(new Callback<TwitterSession>() {
			@Override
			public void success(Result<TwitterSession> result) {
                Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_LONG).show();
				// Do something with result, which provides a TwitterSession for making API calls
			}

			@Override
			public void failure(TwitterException exception) {
				// Do something on failure
			}
		});

	}

	public void onClick(View view) {
        Toast.makeText(view.getContext(), "Cllicking", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        btnTwitterLogin.onActivityResult(requestCode, resultCode, data);
    }
}
