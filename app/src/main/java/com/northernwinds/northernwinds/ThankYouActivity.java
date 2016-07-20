package com.northernwinds.northernwinds;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class ThankYouActivity extends AppCompatActivity {
    private WebView thanks;
    private Button menuButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/PlayfairDisplay-Italic.ttf");

        thanks = (WebView) findViewById(R.id.thanks);
        thanks.setBackgroundColor(0);
        menuButton = (Button) findViewById(R.id.menuButton);

        String pish = "<html><head><style type=\"text/css\">@font-face {font-family: PlayfairDisplay-Italic;src: url(\"file:///android_asset/fonts/PlayfairDisplay-Italic.ttf\")}body {line-height:40px; font-family: PlayfairDisplay-Italic;font-size: 22px;text-align: justify;}</style></head><body>";
        String pas = "</body></html>";
        String myHtmlString = pish + "Мы хотим поблагодарить вас за то, что скачали альфа-версию нашей игры. " +
                "На этом заканчивается пролог нашей истории. Искренне надеемся, что она вам понравилась, и вы " +
                "захотите продолжить это захватывающее приключение вместе с нами. Наша команда приложит все усилия, " +
                "чтобы улучшить приложение и сделать эту историю по-настоящему интересной. До скорых встреч, команда " +
                "разработчиков Ветра Астаны." + pas;
        thanks.loadDataWithBaseURL(null,myHtmlString, "text/html", "UTF-8", null);
        menuButton.setTypeface(custom_font);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toMenu();
            }
        });

    }

    private void toMenu() {
        Intent intent = new Intent(this,MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
