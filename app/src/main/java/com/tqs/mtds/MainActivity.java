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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    private Handler mhandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case 1122:
                    setView_Web();
                    initview();
                    break;
                case 1123:
                    setView_tv();
                    break;
            }
            return false;
        }
    });

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
                if (isNetworkAvailable(MainActivity.this)) {
                    mhandler.sendEmptyMessage(1122);
                    stoptimer();
                } else {
                    count++;
                    if (count > 6) {
                        mhandler.sendEmptyMessage(1123);
                    }
                }
            }
        }, 10, 5 * 1000);
    }

    private void setView_tv() {
        webView.setVisibility(View.GONE);
        tv_wltishi.setVisibility(View.VISIBLE);
    }

    private void setView_Web() {
        webView.setVisibility(View.VISIBLE);
        tv_wltishi.setVisibility(View.GONE);
    }

    private void stoptimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void initview() {
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//??????js??????????????????????????????window.open()????????????false
        webView.getSettings().setJavaScriptEnabled(true);//??????????????????js????????????false?????????true???????????????????????????XSS??????
        webView.getSettings().setSupportZoom(true);//???????????????????????????true
        webView.getSettings().setBuiltInZoomControls(true);//?????????????????????????????????false
        webView.getSettings().setUseWideViewPort(true);//?????????????????????????????????????????????????????????
        webView.getSettings().setLoadWithOverviewMode(true);//???setUseWideViewPort(true)?????????????????????????????????
        webView.getSettings().setAppCacheEnabled(true);//??????????????????
        webView.getSettings().setDomStorageEnabled(true);//??????????????????
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);//??????????????????,????????????????????????
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);//??????????????????????????????
        webView.getSettings().setDatabaseEnabled(true);
//        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);//?????????????????????
//        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
//        webView.getSettings().setAppCachePath(appCachePath);
//        webView.getSettings().setDatabasePath(appCachePath);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://rhs.merrytextech.com/business-intelligence/yyksh/bi/view/8c02c9130ceb4a5f0bb2977fd6d6c660");

    }

    /**
     * ??????????????????????????????
     */
    private boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // ????????????????????????????????????????????????wi-fi,net?????????????????????
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        } else {
            // ??????NetworkInfo??????
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
            if (networkInfo != null && networkInfo.length > 0) {
                for (int i = 0; i < networkInfo.length; i++) {
//                    System.out.println(i + "===??????===" + networkInfo[i].getState());
//                    System.out.println(i + "===??????===" + networkInfo[i].getTypeName());
                    // ?????????????????????????????????????????????
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_1:
//                webView.clearHistory();
                webView.loadUrl("http://rhs.merrytextech.com/business-intelligence/yyksh/bi/view/8c02c9130ceb4a5f0bb2977fd6d6c660");
                break;
            case R.id.action_menu_2:
//                webView.clearHistory();
                webView.loadUrl("http://rhs.merrytextech.com/business-intelligence/yyksh/bi/view/20fd4b0c74be55825f3564940e0f9436");
                break;
            case R.id.action_menu_3:
//                webView.clearHistory();
                webView.loadUrl("http://rhs.merrytextech.com/business-intelligence/zcksh/bi/view/1c75d6975dedf3a750252c7c30b986ee");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.clearHistory();
        webView.removeAllViews();
        webView.destroy();
        mhandler.removeCallbacksAndMessages(null);
        mhandler = null;
    }
}