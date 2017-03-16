package lx.base.apphall.weixin.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;

/**
 * 创建时间 2017/2/8
 * Created by linxiao.
 */

public class WebView extends BaseActionBarActivity {
    private View mRootView;
    private android.webkit.WebView mWebView;
    private String url;
    private String title;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.webview, null);
        mWebView = (android.webkit.WebView) mRootView.findViewById(R.id.webview);
        showLoading();
        WebSettings ws = mWebView.getSettings();
        ws.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //设置 缓存模式(true);
        ws.setAppCacheEnabled(true);
        ws.setSupportMultipleWindows(true);
        ws.setBuiltInZoomControls(true);
        ws.setUseWideViewPort(true);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        ws.setLoadWithOverviewMode(true);
        ws.setSupportZoom(false);
        ws.setJavaScriptEnabled(true);
        ws.setLoadsImagesAutomatically(true);//支持自动加载图片
        ws.supportMultipleWindows();//多窗口
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        url = bundle.getString("herf");
        title = bundle.getString("title");
        setMyActionBarTitle(title);
        mWebView.loadUrl(url);
        hideLoading();
        return mRootView;
    }
}
