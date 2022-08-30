package com.tqs.mtds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.wbvw)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initview();

    }

    private void initview() {

        webView.clearHistory();
        webView.loadUrl("http://rhs.merrytextech.com/business-intelligence/yyksh/bi/view/8c02c9130ceb4a5f0bb2977fd6d6c660");

//        webView.loadUrl("http://mtds.texeasy.net:9180/view/7081d9dc8deb445c10fe20955230729d");
//        webView.loadUrl("http://mtds.texeasy.net:9180/view/75b029390eff9f24769b818af4866017");
//        webView.loadUrl("http://mtds.texeasy.net:9180/view/c7adc057a9753648edeb75c92f001116");
//        webView.loadUrl("http://mtds.texeasy.net:9180/view/c9648705d7a770b3f27206025c39fda6");

    }


}