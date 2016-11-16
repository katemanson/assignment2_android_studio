package com.example.user.pontoon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by user on 16/11/2016.
 */

public class Results extends AppCompatActivity {

    TextView mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Pontoon", "Pontoon onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mResults = (TextView) findViewById(R.id.results);
}

}
