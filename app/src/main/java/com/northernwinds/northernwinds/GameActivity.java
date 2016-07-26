package com.northernwinds.northernwinds;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import io.realm.Realm;

public class GameActivity extends FragmentActivity {

    private static final String TAG = "IgraBlyat";
    private static final int REQUEST_CODE = 1 ;
    private WebView webView;

    private ScrollViewExt scrollView;
    private Button nextButton;
    private String nomer="0";
    private Story story;
    private TextView subTitleTextView;
    private Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //MobileAds.initialize(getApplicationContext(), "ca-app-pub-5322008989186540~5484107311");

        webView = (WebView) findViewById(R.id.textBlyat);
        subTitleTextView = (TextView) findViewById(R.id.subTitleTextView);
        webView.setBackgroundColor(0);




        /*String pish = "<html><head><style type=\"text/css\">@font-face {font-family: PlayfairDisplay-Italic;src: url(\"file:///android_asset/fonts/PlayfairDisplay-Italic.ttf\")}body {line-height:40px; font-family: PlayfairDisplay-Italic;font-size: 22px;text-align: justify;}</style></head><body>";
        String pas = "</body></html>";
        String myHtmlString = pish + "Загрузка..." + pas;*/

        //webView.loadDataWithBaseURL(null,myHtmlString, "text/html", "UTF-8", null);*/
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/PlayfairDisplay-Italic.ttf");

        subTitleTextView.setTypeface(custom_font);


        scrollView = (ScrollViewExt) findViewById(R.id.SCROLLER_ID);
        nextButton = (Button) findViewById(R.id.nextButton);

        scrollView.setScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ScrollViewExt scrollView, int x, int y, int oldx, int oldy) {
                // We take the last son in the scrollview
                View view = (View) scrollView.getChildAt(scrollView.getChildCount() - 1);
                int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

                // if diff is zero, then the bottom has been reached
                if (diff == 0) {
                    Log.d("Tuta","Blyat daaa!!!!!!!1");
                }
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextClick();
            }
        });

        nextButton.setTypeface(custom_font);
        nomer = getIntent().getStringExtra("nomer");



        initialize();
    }

    private void initialize() {
        Continue cont = new Continue(nomer);
        DataHolder.getDataHolder().setaContinue(cont);

        realm.beginTransaction();
        Continue contin = realm.copyToRealm(DataHolder.getDataHolder().getaContinue());
        realm.commitTransaction();

        ArrayList <Story> storyArrayList;
        storyArrayList = (ArrayList<Story>) DataHolder.getDataHolder().getStoryList();
        for(int i=0; i<storyArrayList.size();i++){
            if(storyArrayList.get(i).getObjectId().equals(nomer)){
                story = storyArrayList.get(i);
                subTitleTextView.setText(story.getRazdel());
                Log.d(TAG,"Tapti jok story "+story);
                textInit();
                return;
            }
            Log.d(TAG,"Nomer story: "+storyArrayList.get(i).getObjectId() + " vs "+nomer);
        }
        Log.e(TAG,"Nihuya tapkan jok story");

    }

    private void textInit() {
        String url = story.getText();

        Log.d(TAG, "path:" + "file:///android_asset/files/"+url);
        webView.loadUrl("file:///android_asset/files/"+url);

    }

    private void onNextClick() {

        String s=story.getNextChoice();
        Log.d("pidor-","Suka s blyat "+ s.length());
        if(s.length()<5){
            Intent intent = new Intent(this,ThankYouActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this,ChoiceActivity.class);
            intent.putExtra("choiceId",  story.getNextChoice());
            Log.d(TAG,"Suka choice Id "+ story.getNextChoice());

            startActivityForResult(intent,REQUEST_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        scrollView.fullScroll(scrollView.FOCUS_UP);

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                String pish = "<html><head><style type=\"text/css\">@font-face {font-family: PlayfairDisplay-Italic;src: url(\"file:///android_asset/fonts/PlayfairDisplay-Italic.ttf\")}body {line-height:40px; font-family: PlayfairDisplay-Italic;font-size: 22px;text-align: justify;}</style></head><body>";
                String pas = "</body></html>";
                String myHtmlString = pish + "Загрузка..." + pas;

                webView.loadDataWithBaseURL(null,myHtmlString, "text/html", "UTF-8", null);
                nomer = data.getStringExtra("nomer");
                initialize();
            }
        }
    }
}
