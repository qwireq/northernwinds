package com.northernwinds.northernwinds;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MenuActivity extends AppCompatActivity {

    private Button toJourneyButton;
    private Button continueButton;
    private Button feedbackButton;
    private TextView titleTextView,title2TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        //mahalay
        int t = 0;
        t++;
        setContentView(R.layout.activity_menu);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        title2TextView = (TextView) findViewById(R.id.title2TextView);
        toJourneyButton = (Button) findViewById(R.id.toJourneyButton);
       // continueButton = (Button) findViewById(R.id.continueButton);
        feedbackButton = (Button) findViewById(R.id.feedbackButton);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/PlayfairDisplay-BlackItalic.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/PlayfairDisplay-Italic.ttf");

        titleTextView.setTypeface(custom_font);
        title2TextView.setTypeface(custom_font);
        toJourneyButton.setTypeface(custom_font1);
        feedbackButton.setTypeface(custom_font1);
        toJourneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toJourney();
            }
        });
        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toFeedback();
            }
        });
    }

    private void toFeedback() {
        Intent intent = new Intent(this,FeedbackActivity.class);
        startActivity(intent);
    }

    private void toJourney() {

        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("nomer","5B4C337A-5F9B-687F-FF63-2FE072B67F00");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Вы уверены?")
                .setContentText("Вы покинете игру!")
                .setConfirmText("Да, покинуть")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        Intent intent = new Intent(MenuActivity.this, MenuActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();

    }
}
