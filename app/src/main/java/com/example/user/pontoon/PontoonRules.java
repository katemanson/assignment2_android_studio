package com.example.user.pontoon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by user on 12/11/2016.
 */

public class PontoonRules extends AppCompatActivity {

    TextView mPontoonRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Pontoon", "Rules onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontoon_rules);

        InputStream input = getResources().openRawResource(R.raw.pontoon_rules);
        RulesReader rulesReader = new RulesReader(input);
        String rulesText = rulesReader.getRules();

        mPontoonRules = (TextView)findViewById(R.id.pontoon_rules);
        mPontoonRules.setText(rulesText);

    }



}
