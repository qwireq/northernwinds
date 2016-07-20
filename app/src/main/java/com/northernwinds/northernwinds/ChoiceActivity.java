package com.northernwinds.northernwinds;

import android.content.Intent;
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

        String whereClause = "objectId = "+ "'" + choiceId + "'";
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause(whereClause);

        Backendless.Persistence.of(Choice.class).find(query, new AsyncCallback<BackendlessCollection<Choice>>() {
            @Override
            public void handleResponse(BackendlessCollection<Choice> response) {
                choice = response.getData().get(0);
                Log.d(TAG,"choice nash: "+choice);
                showChoices();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG,"Nihuya ne poluchilos naiti choice "+fault.getMessage());
            }
        });

    }

    private void showChoices() {
        firstChoiceButton.setText(choice.getFirst());
        secondChoiceButton.setText(choice.getSecond());

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
