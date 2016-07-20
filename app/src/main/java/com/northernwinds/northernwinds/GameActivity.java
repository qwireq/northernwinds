package com.northernwinds.northernwinds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.util.persistence.AbstractBackendlessDataObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends FragmentActivity {

    private static final String TAG = "IgraBlyat";
    private static final int REQUEST_CODE = 1 ;
    private WebView webView;

    private ScrollViewExt scrollView;
    private Button nextButton;
    private ImageView imageView;
    private String nomer="0";
    private Story story;
    private TextView subTitleTextView;
    private Choice choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Backendless.initApp(this,Konst.APP_ID,Konst.CLIENT_ID,Konst.VERSION);



        webView = (WebView) findViewById(R.id.textBlyat);
        subTitleTextView = (TextView) findViewById(R.id.subTitleTextView);
        webView.setBackgroundColor(0);

        String pish = "<html><head><style type=\"text/css\">@font-face {font-family: PlayfairDisplay-Italic;src: url(\"file:///android_asset/fonts/PlayfairDisplay-Italic.ttf\")}body {line-height:40px; font-family: PlayfairDisplay-Italic;font-size: 22px;text-align: justify;}</style></head><body>";
        String pas = "</body></html>";
        String myHtmlString = pish + "Загрузка..." + pas;

        webView.loadDataWithBaseURL(null,myHtmlString, "text/html", "UTF-8", null);
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

        String whereClause = "objectId = "+ "'" + nomer + "'";
        initialize(whereClause);
    }

    private void initialize(String whereClause) {

        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause(whereClause);

        Backendless.Persistence.of(Story.class).find(query, new AsyncCallback<BackendlessCollection<Story>>() {
            @Override
            public void handleResponse(BackendlessCollection<Story> response) {
                story = response.getData().get(0);

                Log.d(TAG,"story nash: "+story);
                textInit();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e(TAG,"Nihuya ne poluchilos naiti story "+fault.getMessage());
            }
        });

    }

    private void textInit() {
        String url = story.getText();
        Log.d(TAG,"url storyya: "+url);
        webView.loadUrl(url);
        webView.clearFormData();
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
        String pish = "<html><head><style type=\"text/css\">@font-face {font-family: PlayfairDisplay-Italic;src: url(\"file:///android_asset/fonts/PlayfairDisplay-Italic.ttf\")}body {line-height:40px; font-family: PlayfairDisplay-Italic;font-size: 22px;text-align: justify;}</style></head><body>";
        String pas = "</body></html>";
        String myHtmlString = pish + "Загрузка..." + pas;

        webView.loadDataWithBaseURL(null,myHtmlString, "text/html", "UTF-8", null);
        scrollView.fullScroll(scrollView.FOCUS_UP);

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){

                nomer = data.getStringExtra("nomer");

                String whereClause = "objectId = "+ "'" + nomer + "'";

                initialize(whereClause);
            }
        }
    }
}
