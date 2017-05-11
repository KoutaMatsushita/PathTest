package com.example.k_matsushita.pathtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.davemorrissey.labs.subscaleview.ImageSource;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestView view = (TestView)findViewById(R.id.view);
        view.setImage(ImageSource.resource(R.drawable.img));
    }

}
