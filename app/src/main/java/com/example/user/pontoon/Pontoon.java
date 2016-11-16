package com.example.user.pontoon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 12/11/2016.
 */

public class Pontoon extends AppCompatActivity {

    Button mDealButton;
    TextView mAppCards;
    TextView mUserCards;
    TextView mOutcome;
    Button mNewHandButton;
    Button mQuitButton;
    Button mStickButton;
    Button mTwistButton;

    PontoonGame mPontoonGame = new PontoonGame();

    Handler delayHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Pontoon", "Pontoon onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontoon);

        mDealButton = (Button) findViewById(R.id.deal_button);
        mAppCards = (TextView) findViewById(R.id.app_cards);
        mUserCards = (TextView) findViewById(R.id.user_cards);
        mOutcome = (TextView) findViewById(R.id.outcome_text);
        mNewHandButton = (Button) findViewById(R.id.new_hand_button);
        mQuitButton = (Button) findViewById(R.id.quit_button);
        mStickButton = (Button) findViewById(R.id.stick_button);
        mTwistButton = (Button) findViewById(R.id.twist_button);

        mNewHandButton.setVisibility(View.GONE);
        mQuitButton.setVisibility(View.GONE);
        mStickButton.setVisibility(View.GONE);
        mTwistButton.setVisibility(View.GONE);

        mDealButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Deal button clicked");

                // removes button on click
                //ToDo: ?difference between this and .setVisibility?
                ViewGroup parentView = (ViewGroup) view.getParent();
                parentView.removeView(view);

                //ToDo: ?deck setup differs if starting game after a Pontoon...
                mPontoonGame.setUpNewDeck();
                mPontoonGame.deal(2);

                mAppCards.setText(mPontoonGame.hideAppCards());
                mUserCards.setText(mPontoonGame.showUserCards());

                if (mPontoonGame.checkInitialDeal() != null) {

                    delayHandler.postDelayed( new Runnable() {
                        @Override
                        public void run() {
                            mOutcome.setText(mPontoonGame.checkInitialDeal());
                        }
                    }, 2000 );

                    delayHandler.postDelayed( new Runnable() {
                        @Override
                        public void run() {
                            mAppCards.setText(mPontoonGame.showAppCards());
                            mNewHandButton.setVisibility(View.VISIBLE);
                            mQuitButton.setVisibility(View.VISIBLE);
                        }
                    }, 2000 );
                }
                else if (mPontoonGame.checkInitialDeal() == null) {
                    userPlay();
                }
            }
        });

        mNewHandButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "New Hand button clicked");

                mNewHandButton.setVisibility(View.GONE);
                mQuitButton.setVisibility(View.GONE);

                //ToDo: ?use Preferences to provide hands won counters?
                recreate();
            }
        });

        mQuitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Quit button clicked");

                mNewHandButton.setVisibility(View.GONE);
                mQuitButton.setVisibility(View.GONE);

                Intent intent = new Intent(Pontoon.this, Main.class);
                startActivity(intent);
            }
        });

        mStickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Stick button clicked");

                mOutcome.setVisibility(View.INVISIBLE);
                mStickButton.setVisibility(View.GONE);
                mTwistButton.setVisibility(View.GONE);
                mAppCards.setText(mPontoonGame.hideAppCards());
                mUserCards.setText(mPontoonGame.showUserTwists());

                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        appPlay();
                    }
                }, 2000);
            }
        });

        mTwistButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Twist button clicked");

                mOutcome.setVisibility(View.INVISIBLE);
                mStickButton.setVisibility(View.GONE);
                mTwistButton.setVisibility(View.GONE);
                mPontoonGame.userTwist();
                mUserCards.setText(mPontoonGame.showUserCards());
                userPlay();
            }
        });
    }

    public void userPlay() {

        String outcomeUserTurn = mPontoonGame.checkUserHand();
        if ( mPontoonGame.checkUserHand() != null ) {

            mOutcome.setText(outcomeUserTurn);
            mOutcome.setVisibility(View.VISIBLE);

            if ( mPontoonGame.userHasToTwist() ) {

                mStickButton.setVisibility(View.VISIBLE);
                mTwistButton.setVisibility(View.VISIBLE);
                mStickButton.setEnabled(false);
            }
            else if ( mPontoonGame.userCanStickOrTwist() ) {

                mStickButton.setVisibility(View.VISIBLE);
                mTwistButton.setVisibility(View.VISIBLE);
                mStickButton.setEnabled(true);
            }
            else if ( mPontoonGame.checkIfUserBust() ) {

                mAppCards.setText(mPontoonGame.showAppCards());
                mNewHandButton.setVisibility(View.VISIBLE);
                mQuitButton.setVisibility(View.VISIBLE);
            }
            else if ( mPontoonGame.checkForUserFCT() ) {

                mUserCards.setText(mPontoonGame.showUserTwists());

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        appPlay();
                    }
                }, 2000 );
            }
        }
    }

    public void appPlay() {

        String outcomeAppTurn = mPontoonGame.checkAppHand();
        if ( mPontoonGame.checkAppHand() != null ) {

            mOutcome.setText(outcomeAppTurn);
            mOutcome.setVisibility(View.VISIBLE);

            if ( mPontoonGame.appHasToTwist() ) {

                Log.d("Pontoon", "appHasToTwist TRUE; appHandValue: " + mPontoonGame.getAppHandValue() );

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        mPontoonGame.appTwist();
                        mAppCards.setText(mPontoonGame.showAppTwists());
                    }
                }, 2000 );

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        appPlay();
                    }
                }, 2000 );
            }
            else if ( mPontoonGame.checkIfAppBust() ) {

                Log.d("Pontoon", "checkIfAppBust TRUE; appHandValue: " + mPontoonGame.getAppHandValue() );

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        mAppCards.setText(mPontoonGame.showAppCards());
                        mUserCards.setText(mPontoonGame.showUserCards());
                    }
                }, 2000 );

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        mNewHandButton.setVisibility(View.VISIBLE);
                        mQuitButton.setVisibility(View.VISIBLE);
                    }
                }, 2000 );
            }
            else if ( mPontoonGame.checkForAppFCT() ) {

                Log.d("Pontoon", "checkForAppFCT TRUE; appHandValue: " + mPontoonGame.getAppHandValue() );

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        mAppCards.setText(mPontoonGame.showAppCards());
                        mUserCards.setText(mPontoonGame.showUserCards());
                    }
                }, 2000 );

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        endGame();
                    }
                }, 2000 );
            }
            else if ( mPontoonGame.appStrategyTwist() ) {

                // Following two lines just set up values needed for the third, Log line
                Hand userHand = mPontoonGame.getUserPlayer().getHand();
                int userTwistValue = mPontoonGame.getHandValuer().getTwistValue(userHand);
                Log.d("Pontoon", "appStrategyTwist TRUE; appHandValue: " + mPontoonGame.getAppHandValue() + "\nuserTwistValue: " + userTwistValue);

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        mPontoonGame.appTwist();
                        mAppCards.setText(mPontoonGame.showAppTwists());
                    }
                }, 2000 );

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        appPlay();
                    }
                }, 2000 );
            }
            else if ( mPontoonGame.appStrategyStick() ) {

                // Following two lines just set up values needed for the third, Log line
                Hand userHand = mPontoonGame.getUserPlayer().getHand();
                int userTwistValue = mPontoonGame.getHandValuer().getTwistValue(userHand);
                Log.d("Pontoon", "appStrategyStick TRUE; appHandValue: " + mPontoonGame.getAppHandValue() + "\nuserTwistValue: " + userTwistValue);

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        mAppCards.setText(mPontoonGame.showAppCards());
                        mUserCards.setText(mPontoonGame.showUserCards());
                    }
                }, 2000 );

                delayHandler.postDelayed( new Runnable() {
                    @Override
                    public void run() {
                        endGame();
                    }
                }, 2000 );
            }
        }
    }

    public void endGame() {

        String outcomeCompareHands = mPontoonGame.compareHands();
        mOutcome.setText(outcomeCompareHands);

        mNewHandButton.setVisibility(View.VISIBLE);
        mQuitButton.setVisibility(View.VISIBLE);
    }

}