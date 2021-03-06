package com.bluedungeon.javayelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;
    Button skipbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        if(AccessToken.getCurrentAccessToken() != null){
            Intent YelpSwipe2 = new Intent(MainActivity.this,YelpSwipe.class);
            MainActivity.this.startActivity(YelpSwipe2);

        }
        setContentView(R.layout.activity_main);
        skipbutton = (Button)findViewById(R.id.bskip);
        loginButton = (LoginButton)findViewById(R.id.fb_login_bn);
        textView = (TextView)findViewById(R.id.textView);
        callbackManager = CallbackManager.Factory.create();

        skipbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent YelpSwipe = new Intent(MainActivity.this,YelpSwipe.class);
                MainActivity.this.startActivity(YelpSwipe);
            }
        });
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Intent YelpSwipe = new Intent(MainActivity.this,YelpSwipe.class);
                MainActivity.this.startActivity(YelpSwipe);


            }

            @Override
            public void onCancel() {

                textView.setText("Login Cancelled");

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);


    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
