package com.tqs.mtds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.wbvw)
    WebView webView;
    @BindView(R.id.tv_wltishi)
    TextView tv_wltishi;


    private Timer timer;
    private int count = 0;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1122:
                    initview();
                    break;
                case 1123:
                    setView();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        timertishi();
    }

    private void timertishi() {
        count = 0;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                if (count > 6) {
                    mhandler.sendEmptyMessage(1123);
                    stoptimer();
                } else {
                    if (isNetworkAvailable(MainActivity.this)) {
                        mhandler.sendEmptyMessage(1122);
                        stoptimer();
                    }
                }
            }
        }, 10, 1 * 60 * 1000);
    }

    private void setView() {
        webView.setVisibility(View.GONE);
        tv_wltishi.setVisibility(View.VISIBLE);
    }

    private void stoptimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
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

    /**
     * 检查当前网络是否可用
     */
    private boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
//                    System.out.println(i + "===状态===" + networkInfo[i].getState());
//                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}