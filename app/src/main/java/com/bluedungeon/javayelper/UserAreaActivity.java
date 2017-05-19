package com.bluedungeon.javayelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        textView = (TextView) findViewById(R.id.etUser);
        imageView = (ImageView) findViewById(R.id.etImage);

        textView.setText("Go eat food");


    }
}
