package lx.base.apphall.permission;

import android.text.TextUtils;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import lx.base.apphall.R;
import lx_base.mybase.common.base.BaseActionBarActivity;
import lx_base.mybase.common.util.ImageLoaderUtils;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Administrator on 2016/7/22.
 */
public class ShowPicActivity extends BaseActionBarActivity {
    public static final String ARG_IMAGE_PATH = "image_path";//图片路径
    private View mRootView;
    private String mPath;

    @BindView(R.id.pv_image)
    PhotoView pvImage;


    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.show_picture_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        setMyActionBarTitle("已选图片");
        if (getIntent() != null) {
            mPath = getIntent().getStringExtra(ARG_IMAGE_PATH);
            if (TextUtils.isEmpty(mPath)) {
                showToast("图片路径错误");
                finish();
                return;
            }
            ImageLoaderUtils.display(this, pvImage, mPath);
//            Glide.with(this).load(R.mipmap.ic_test).placeholder(lx_base.mybase.R.mipmap.ic_image_loading)
//                    .error(lx_base.mybase.R.mipmap.ic_image_loadfail).crossFade().into(pvImage);
        }
    }
}
