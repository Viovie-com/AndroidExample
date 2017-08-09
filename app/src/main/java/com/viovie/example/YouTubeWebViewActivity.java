package com.viovie.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
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

        mHeaderMap = new HashMap<>();
        mHeaderMap.put("Access-Control-Allow-Origin", "*");
        mHeaderMap.put("Referer", "https://www.google.com.tw");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.startsWith("https://www.youtube.com/embed")) {
                    try {
                        URI uri = URI.create(url);
                        URL url1 = uri.toURL();
                        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                        conn.setRequestProperty("Access-Control-Allow-Origin", "*");
                        conn.setRequestProperty("Referer", "https://www.google.com.tw");

                        DataInputStream dis = new DataInputStream(conn.getInputStream());
                        return new WebResourceResponse("text/html", "UTF-8", dis);
                    } catch (Exception e) {

                    }
                }

                return super.shouldInterceptRequest(view, url);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String filename = "youtube.html";

        File f = new File(getCacheDir() + "/" + filename);
        f.delete();
        if (!f.exists()) {
            try {
                InputStream is = getAssets().open(filename);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                FileOutputStream fos = new FileOutputStream(f);
                fos.write(buffer);
                fos.close();
            } catch (Exception e) { throw new RuntimeException(e); }
        }

        mWebView.loadUrl("file://" + f.getPath(), mHeaderMap);
        //mWebView.loadUrl("https://www.youtube.com/embed/zc5lhK00GSg?list=PLOEZfHSao19UGDhI5evWOAOi0oZXCh8jg&origin=https://www.google.com.tw", mHeaderMap);
        //mWebView.loadUrl("https://www.youtube.com/watch?v=zc5lhK00GSg&index=8&list=PLOEZfHSao19UGDhI5evWOAOi0oZXCh8jg");
    }
}
