package com.example.user.pontoon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by user on 12/11/2016.
 */

public class Pontoon extends AppCompatActivity {

    Button mDealButton;
    TextView mAppCards;
    TextView mUserCards;

    PontoonGame mPontoonGame = new PontoonGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Pontoon", "Pontoon onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontoon);

        mDealButton = (Button) findViewById(R.id.deal_button);
        mAppCards = (TextView) findViewById(R.id.app_cards);
        mUserCards = (TextView) findViewById(R.id.user_cards);

        mDealButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Deal button clicked");

                // removes button on click
                ViewGroup parentView = (ViewGroup) view.getParent();
                parentView.removeView(view);

                // ?is setUpNewDeck in the right place? Differs if starting game after a Pontoon...
                mPontoonGame.setUpNewDeck();
                mPontoonGame.deal(2);

                mAppCards.setText(mPontoonGame.showAppHandText());
                mUserCards.setText(mPontoonGame.showUserHandText());

                mPontoonGame.checkInitialDeal();

            }
        });
    }
}