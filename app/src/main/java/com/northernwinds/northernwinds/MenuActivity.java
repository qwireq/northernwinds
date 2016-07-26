package com.northernwinds.northernwinds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "NachaloSka" ;
    private Button toJourneyButton;
    private Button continueButton;
    private Button feedbackButton;
    private TextView titleTextView,title2TextView;
    private TextView menuSubTextView;

    private BackendlessDataQuery dataQuery = new BackendlessDataQuery();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Backendless.initApp(this, Konst.APP_ID, Konst.CLIENT_ID, Konst.VERSION);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        title2TextView = (TextView) findViewById(R.id.title2TextView);
        toJourneyButton = (Button) findViewById(R.id.toJourneyButton);
        continueButton = (Button) findViewById(R.id.continueButton);
        feedbackButton = (Button) findViewById(R.id.feedbackButton);
        menuSubTextView = (TextView) findViewById(R.id.menuSubTextView);


        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/PlayfairDisplay-BlackItalic.ttf");
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/PlayfairDisplay-Italic.ttf");

        titleTextView.setTypeface(custom_font);
        title2TextView.setTypeface(custom_font);
        toJourneyButton.setTypeface(custom_font1);
        feedbackButton.setTypeface(custom_font1);
        continueButton.setTypeface(custom_font1);
        menuSubTextView.setTypeface(custom_font1);



        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toFeedback();
            }
        });

        RealmConfiguration config = new RealmConfiguration.Builder(this).name("game_saves.realm").deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Story> storyRealmResults  = realm.where(Story.class).findAll();
        RealmResults <Choice> choiceRealmResults = realm.where(Choice.class).findAll();

        int PAGESIZE = 80;
        dataQuery.setPageSize(PAGESIZE);

        if(storyRealmResults.size() < 17 || choiceRealmResults.size() < 10) {
            toJourneyButton.setText("Загрузка Дополнительных Файлов...");
            feedbackButton.setText("");
            continueButton.setText("");
            Log.d(TAG, "Niche net v baze, size " + storyRealmResults.size());


            Backendless.Persistence.of(Choice.class).find(dataQuery,new AsyncCallback<BackendlessCollection<Choice>>() {
                @Override
                public void handleResponse(BackendlessCollection<Choice> response) {
                    DataHolder.getDataHolder().setChoiceList(response.getData());
                    Log.d(TAG, "Skachalas choiceList " + DataHolder.getDataHolder().getChoiceList().size());
                }
                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e(TAG, "Nihuya ne poluchilos skachat choice " + fault.getMessage());
                }
            });
            Backendless.Persistence.of(Story.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Story>>() {
                @Override
                public void handleResponse(BackendlessCollection<Story> response) {
                    DataHolder.getDataHolder().setStoryList(response.getData());
                    Log.d(TAG, "Skachalas storyList " + response.getData().size());
                    getTexts(0);
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Log.e(TAG, "Nihuya ne poluchilos skachat story " + fault.getMessage());
                }
            });
        }
        else{
            List<Story> storyList = new ArrayList<>();
            List<Choice> choiceList = new ArrayList<>();

            for(Story s:storyRealmResults){
                storyList.add(s);
            }
            for (Choice c:choiceRealmResults){
                choiceList.add(c);
            }

            Log.d(TAG, "Realm choice " + choiceList.size());
            Log.d(TAG, "Realm story " + storyList.size());

            DataHolder.getDataHolder().setChoiceList(choiceList);
            DataHolder.getDataHolder().setStoryList(storyList);
            getTexts(1);
        }

    }


    private void getTexts(int code) {

        if(code == 0) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();

                List <Choice> choiceList = realm.copyToRealm(DataHolder.getDataHolder().getChoiceList());
                List <Story> storyList = realm.copyToRealm(DataHolder.getDataHolder().getStoryList());

                Log.d(TAG, "Sohr Realm choice " + choiceList.size());
                Log.d(TAG, "Sohr Realm story " + storyList.size());
            realm.commitTransaction();
        }


        //Buttons
        toJourneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toJourney();
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm = Realm.getDefaultInstance();
                RealmResults<Continue> realmResults = realm.where(Continue.class).findAll();
                Continue cc = new Continue("");
                for(Continue co:realmResults){
                    cc=co;
                }
                Log.d(TAG, "Izvl Continue " + realmResults.size());
                Log.d(TAG, "Izvl Continue " + cc.getId());
                if(realmResults.size()>0){
                    toContinue(cc.getId());
                }
                else toJourney();
            }
        });
        toJourneyButton.setText("Новая Игра");
        feedbackButton.setText("Обратная Связь");
        continueButton.setText("Продолжить");

        feedbackButton.setBackgroundResource(R.drawable.bttn);
        continueButton.setBackgroundResource(R.drawable.bttn);
        //Buttons
    }

    private void toContinue(String id) {
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("nomer",id);
        startActivity(intent);
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
