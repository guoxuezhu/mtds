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


        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webView.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
//        webView.getSettings().setUserAgentString("User-Agent:Android");//设置用户代理，一般不用

//        webView.clearHistory();


        webView.loadUrl("http://rhs.merrytextech.com/business-intelligence/yyksh/bi/view/8c02c9130ceb4a5f0bb2977fd6d6c660");

//        webView.loadUrl("http://mtds.texeasy.net:9180/view/7081d9dc8deb445c10fe20955230729d");
//        webView.loadUrl("http://mtds.texeasy.net:9180/view/75b029390eff9f24769b818af4866017");
//        webView.loadUrl("http://mtds.texeasy.net:9180/view/c7adc057a9753648edeb75c92f001116");
//        webView.loadUrl("http://mtds.texeasy.net:9180/view/c9648705d7a770b3f27206025c39fda6");

    }


}