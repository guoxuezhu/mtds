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

//        webView.getSettings().setBlockNetworkImage(false);//是否阻止图片网络数据


//
//        webView.getSettings().setPluginsEnabled(true);
//        webView.getSettings().setSaveFormData(false);
//        webView.refreshPlugins(true);
//        webView.getSettings().setLoadsImagesAutomatically(true);



        /* 设置缓存模式,我这里使用的默认 */
//        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        /* 大部分网页需要自己保存一些数据,这个时候就的设置下面这个属性 */
//        webView.getSettings().setDomStorageEnabled(true);
        /* HTML5的地理位置服务,设置为true,启用地理定位 */
//        webView.getSettings().setGeolocationEnabled(true);
        /* 提高网页渲染的优先级 */
//        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        /* 设置显示水平滚动条,就是网页右边的滚动条.我这里设置的不显示 */
//        webView.setHorizontalScrollBarEnabled(false);
        /* 指定垂直滚动条是否有叠加样式 */
//        webView.setVerticalScrollbarOverlay(true);
        /* 设置滚动条的样式 */
//        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        /* 这个不用说了,重写WebChromeClient监听网页加载的进度,从而实现进度条 */
//        webView.setWebChromeClient(new WebChromeClient());
        /* 同上,重写WebViewClient可以监听网页的跳转和资源加载等等... */
        webView.setWebViewClient(new WebViewClient());

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_1:
                webView.clearHistory();
                webView.loadUrl("http://rhs.merrytextech.com/business-intelligence/yyksh/bi/view/8c02c9130ceb4a5f0bb2977fd6d6c660");
                break;
            case R.id.action_menu_2:
                webView.clearHistory();
                webView.loadUrl("http://rhs.merrytextech.com/business-intelligence/yyksh/bi/view/20fd4b0c74be55825f3564940e0f9436");
                break;
            case R.id.action_menu_3:
                webView.clearHistory();
                webView.loadUrl("http://rhs.merrytextech.com/business-intelligence/zcksh/bi/view/1c75d6975dedf3a750252c7c30b986ee");
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.clearHistory();
        webView.destroy();
        mhandler.removeCallbacksAndMessages(null);
        mhandler = null;
    }
}