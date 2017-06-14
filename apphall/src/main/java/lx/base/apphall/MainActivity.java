package lx.base.apphall;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.ExpandableListView.ExpandableMain;
import lx.base.apphall.MPAndroidCharts.MPAndroidActivity;
import lx.base.apphall.canvas.CanvasActivity;
import lx.base.apphall.custom_view.ViewActivity;
import lx.base.apphall.custom_view.search_view.SearchViewActivity;
import lx.base.apphall.event_bus.event1.EventFirstActvity;
import lx.base.apphall.fusionCharts.SampleActivity;
import lx.base.apphall.gridview.GridCheckActivity;
import lx.base.apphall.listview.NewsActivity;
import lx.base.apphall.listview.sample_news.view.NewsView;
import lx.base.apphall.login.Splash;
import lx.base.apphall.permission.PermissionReviewActivity;
import lx.base.apphall.popwindow.PopWindowActivity;
import lx.base.apphall.progress.Progress;
import lx.base.apphall.qr_code.QrCodeActivity;
import lx.base.apphall.recyclierview.RecyclerMainActivity;
import lx.base.apphall.rxjava.RxJavaTestActivity;
import lx.base.apphall.sweet_alert_dialog.DialogMainActivity;
import lx.base.apphall.timepicker.TimePickerActivity;
import lx.base.apphall.viewpager_fragment.FragActivity;
import lx.base.apphall.weixin.view.ViewImpl;
import lx_base.mybase.common.base.BaseActionBarActivity;

public class MainActivity extends BaseActionBarActivity implements View.OnClickListener {
    @BindView(R.id.permission)
    TextView permission;
    @BindView(R.id.recyclerView)
    TextView recyclerView;
    @BindView(R.id.tv_fragment)
    TextView tvfragment;
    @BindView(R.id.splash)
    TextView splash;
    @BindView(R.id.listview)
    TextView listview;
    @BindView(R.id.tv_mvp)
    TextView mvp;
    @BindView(R.id.view)
    TextView view;
    @BindView(R.id.tv_chose_pic)
    TextView choosePic;
    @BindView(R.id.custom_style)
    TextView customStyle;
    @BindView(R.id.searchView)
    TextView searchView;
    @BindView(R.id.qr_code)
    TextView qrCode;
    @BindView(R.id.timepicker)
    TextView timepicker;
    @BindView(R.id.rxjava)
    TextView rxjava;
    @BindView(R.id.sweetalert)
    TextView sweetalert;
    @BindView(R.id.canvas)
    TextView canvas;
    @BindView(R.id.weixin)
    TextView weixin;
    @BindView(R.id.expandableListView)
    TextView expandableListView;
    @BindView(R.id.event_bus)
    TextView eventBus;
    @BindView(R.id.fusionCharts)
    TextView fusionCharts;
    @BindView(R.id.mpAndroidCharts)
    TextView mpAndroidCharts;
    private View mRootView;
    private int ranColor;
    private TextView grid;
    private Random mRandom;

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.activity_main, null);
        mRandom = new Random();
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        hideMyLeftView();
        grid = (TextView) findViewById(R.id.tv_grid);
//        ranColor = 0xff000000 | mRandom.nextInt(0x00ffffff);
//        grid.setBackgroundColor(ranColor);
        grid.setOnClickListener(this);
        ranColor = 0xff000000 | mRandom.nextInt(0x00ffffff);
        permission.setBackgroundColor(ranColor);
        permission.setOnClickListener(this);
        ranColor = 0xff000000 | mRandom.nextInt(0x00ffffff);
        recyclerView.setBackgroundColor(ranColor);
        recyclerView.setOnClickListener(this);
        tvfragment.setOnClickListener(this);
        splash.setOnClickListener(this);
        listview.setOnClickListener(this);
        mvp.setOnClickListener(this);
        view.setOnClickListener(this);
        choosePic.setOnClickListener(this);
        customStyle.setOnClickListener(this);
        searchView.setOnClickListener(this);
        qrCode.setOnClickListener(this);
        timepicker.setOnClickListener(this);
        rxjava.setOnClickListener(this);
        sweetalert.setOnClickListener(this);
        canvas.setOnClickListener(this);
        weixin.setOnClickListener(this);
        expandableListView.setOnClickListener(this);
        eventBus.setOnClickListener(this);
        fusionCharts.setOnClickListener(this);
        mpAndroidCharts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_grid:
                intent.setClass(MainActivity.this, GridCheckActivity.class);
                startActivity(intent);
                break;
            case R.id.permission:
                intent.setClass(MainActivity.this, PermissionReviewActivity.class);
                startActivity(intent);
                break;
            case R.id.recyclerView:
                intent.setClass(MainActivity.this, RecyclerMainActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_fragment:
                intent.setClass(MainActivity.this, FragActivity.class);
                startActivity(intent);
                break;
            case R.id.splash:
                intent.setClass(MainActivity.this, Splash.class);
                startActivity(intent);
                break;
            case R.id.listview:
                intent.setClass(MainActivity.this, NewsActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_mvp:
                intent.setClass(MainActivity.this, NewsView.class);
                startActivity(intent);
                break;
            case R.id.view:
                intent.setClass(MainActivity.this, ViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_chose_pic:
                intent.setClass(MainActivity.this, PopWindowActivity.class);
                startActivity(intent);
                break;
            case R.id.custom_style:
                intent.setClass(MainActivity.this, Progress.class);
                startActivity(intent);
                break;
            case R.id.searchView:
                intent.setClass(MainActivity.this, SearchViewActivity.class);
                startActivity(intent);
                break;
            case R.id.qr_code:
                intent.setClass(MainActivity.this, QrCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.timepicker:
                intent.setClass(MainActivity.this, TimePickerActivity.class);
                startActivity(intent);
                break;
            case R.id.rxjava:
                intent.setClass(MainActivity.this, RxJavaTestActivity.class);
                startActivity(intent);
                break;
            case R.id.sweetalert:
                intent.setClass(MainActivity.this, DialogMainActivity.class);
                startActivity(intent);
                break;
            case R.id.canvas:
                intent.setClass(MainActivity.this, CanvasActivity.class);
                startActivity(intent);
                break;
            case R.id.weixin:
                intent.setClass(MainActivity.this, ViewImpl.class);
                startActivity(intent);
                break;
            case R.id.expandableListView:
                intent.setClass(MainActivity.this, ExpandableMain.class);
                startActivity(intent);
                break;
            case R.id.event_bus:
                intent.setClass(MainActivity.this, EventFirstActvity.class);
                startActivity(intent);
                break;
            case R.id.fusionCharts:
                intent.setClass(MainActivity.this, SampleActivity.class);
                startActivity(intent);
                break;
            case R.id.mpAndroidCharts:
                intent.setClass(MainActivity.this, MPAndroidActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
