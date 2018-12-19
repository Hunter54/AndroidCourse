package com.example.ionut.androidcourse.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ionut.androidcourse.Constants;
import com.example.ionut.androidcourse.R;

public class WebActivity extends AppCompatActivity {
    private WebView wvLink;
    private String movieLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wvLink = findViewById(R.id.wvLink);
        wvLink.setWebViewClient(new WebViewClient());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movieLink = bundle.getString(Constants.MOVIE_LINK);
        }
        wvLink.loadUrl(movieLink);

    }
}
