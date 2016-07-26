package com.northernwinds.northernwinds;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.ArrayList;

public class ChoiceActivity extends AppCompatActivity {
    private static final String TAG = "ChoiceBlyat" ;
    private Choice choice;
    private Button firstChoiceButton;
    private Button secondChoiceButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/PlayfairDisplay-Italic.ttf");


        firstChoiceButton = (Button) findViewById(R.id.firstChoiceButton);
        secondChoiceButton = (Button) findViewById(R.id.secondChoiceButton);


        firstChoiceButton.setTypeface(custom_font);
        secondChoiceButton.setTypeface(custom_font);

        String choiceId = "";
        choiceId = getIntent().getStringExtra("choiceId");

        Log.d(TAG,"choiceId: "+choiceId);

        ArrayList<Choice> choices = (ArrayList<Choice>) DataHolder.getDataHolder().getChoiceList();
        int n = choices.size();
        for(int i = 0; i<n; i++){
            if(choices.get(i).getObjectId().equals(choiceId)){
                choice = choices.get(i);
                showChoices();
                break;
            }
        }
    }

    private void showChoices() {
        firstChoiceButton.setText(choice.getFirst());
        secondChoiceButton.setText(choice.getSecond());

        secondChoiceButton.setBackgroundResource(R.drawable.bttn);
        firstChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nazad(choice.getStoryFirst());
            }
        });

        secondChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nazad(choice.getStorySecond());
            }
        });

    }

    private void nazad(String storyId) {

        Intent returnIntent = new Intent();
        Log.d(TAG,"nomer s choica: "+storyId);
        returnIntent.putExtra("nomer", storyId);
        setResult(RESULT_OK,returnIntent);
        finish();

    }
}
