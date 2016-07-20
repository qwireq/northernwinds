package com.northernwinds.northernwinds;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.webkit.WebView;
import android.widget.TextView;

public class FeedbackActivity extends AppCompatActivity {

    private WebView feedbackWebView;
    private WebView spasibWebView;
    private TextView emailTextView;
    private WebView takjeWebView;
    private WebView contactsWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/PlayfairDisplay-Italic.ttf");

        feedbackWebView = (WebView) findViewById(R.id.feedbackWebView);
        feedbackWebView.setBackgroundColor(0);

        spasibWebView = (WebView) findViewById(R.id.spasibWebView);
        spasibWebView.setBackgroundColor(0);

        takjeWebView = (WebView) findViewById(R.id.takjeWebView);
        takjeWebView.setBackgroundColor(0);

        contactsWebView = (WebView) findViewById(R.id.contactsWebView);
        contactsWebView.setBackgroundColor(0);

        emailTextView = (TextView) findViewById(R.id.emailTextView);

        String pish = "<html><head><style type=\"text/css\">@font-face {font-family: PlayfairDisplay-Italic;src: url(\"file:///android_asset/fonts/PlayfairDisplay-Italic.ttf\")}body {line-height:40px; font-family: PlayfairDisplay-Italic;font-size: 22px;text-align: justify;}</style></head><body>";
        String pas = "</body></html>";

        String myHtmlString = pish + "Команда разработчиков открыта критике и идеям со стороны игроков. " +
                "Все свои пожелания и рекомендации вы можете отправлять по адресу:" + pas;
        feedbackWebView.loadDataWithBaseURL(null,myHtmlString, "text/html", "UTF-8", null);

        String myHtmlString1=pish + "Спасибо за интерес к нашей первой игре." + pas;
        spasibWebView.loadDataWithBaseURL(null,myHtmlString1, "text/html", "UTF-8", null);

        String myHtmlString2=pish + "Присоединяйтесь и подписывайтесь на наши страницы где вы сможете " +
                "найти много интересного и следить за процессом создания игры:" + pas;
        takjeWebView.loadDataWithBaseURL(null,myHtmlString2, "text/html", "UTF-8", null);


        emailTextView.setTypeface(custom_font);


        String facebook = "<a href=\"http://www.facebook.com/Northern-Winds-286192815063298/\">Facebook</a>";
        String vk = "<a href=\"http://vk.com/northern.winds/\">VK</a>";
        String insta = "<a href=\"http://www.instagram.com/feel.the.northern.winds/\">Instagram</a>";
        String text = pish + facebook + "<br>" + vk + "<br>" + insta + pas;

        contactsWebView.loadDataWithBaseURL(null,text, "text/html", "UTF-8", null);
    }
}
