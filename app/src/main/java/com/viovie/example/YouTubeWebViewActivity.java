package com.viovie.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Map;

public class YouTubeWebViewActivity extends AppCompatActivity {

    WebView mWebView;
    private Map<String, String> mHeaderMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);

        mHeaderMap = new HashMap<>();
        mHeaderMap.put("Referer", "https://www.google.com.tw");
    }

    @Override
    protected void onResume() {
        super.onResume();


        mWebView.loadUrl("https://www.youtube.com/embed/zc5lhK00GSg?list=PLOEZfHSao19UGDhI5evWOAOi0oZXCh8jg&origin=https://www.google.com.tw", mHeaderMap);
        //mWebView.loadUrl("https://www.youtube.com/watch?v=zc5lhK00GSg&index=8&list=PLOEZfHSao19UGDhI5evWOAOi0oZXCh8jg");
    }
}
