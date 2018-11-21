package com.example.ionut.androidcourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    private WebView wvLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wvLink=findViewById(R.id.wvLink);
        wvLink.setWebViewClient(new WebViewClient());
        wvLink.loadUrl("https://www.imdb.com/title/tt0816692/");

    }
}
