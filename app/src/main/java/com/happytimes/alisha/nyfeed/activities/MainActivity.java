package com.happytimes.alisha.nyfeed.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.happytimes.alisha.nyfeed.R;
import com.happytimes.alisha.nyfeed.model.Doc;

import org.parceler.Parcels;

public class MainActivity extends AppCompatActivity {
    public static final String ARG_ITEM = "article";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getIntent().getExtras();
        Doc mArticle = (Doc) Parcels.unwrap(getIntent().getParcelableExtra(ArticleDetailFragment.ARG_ITEM));

        WebView webView = (WebView) findViewById(R.id.wvArticle_detail);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(mArticle.getWebUrl());
    }

}
