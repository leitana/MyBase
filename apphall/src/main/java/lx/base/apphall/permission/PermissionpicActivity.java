package lx.base.apphall.permission;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lx.base.apphall.R;
import lx.base.apphall.widgets.ChoiceView;
import lx_base.mybase.common.base.AdapterBase;
import lx_base.mybase.common.base.BaseActionBarActivity;
import lx_base.mybase.common.util.PermissionsChecker;

/**
 * Created by Administrator on 2016/7/27.
 */
public class PermissionpicActivity extends BaseActionBarActivity {

    private static final int REQUEST_CODE = 0; // 请求码
    private static final int REQUEST_PHOTO = 1;//相机
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private PermissionsChecker mPermissionsChecker;// 权限检测器

    @BindView(R.id.bt_photo)
    Button btPhoto;
    @BindView(R.id.bt_check)
    Button btCheck;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.lv_photo)
    ListView lvPhoto;
    @BindView(R.id.bt_upload)
    Button btUpload;
    @BindView(R.id.bt_return)
    Button btReturn;
    private View mRootView;
    private String theLarge;
    private List<String> nameList = new ArrayList<String>();
    private PhotoAdapter mAdapter;
    String savePath = "";
    String checkPath = "";

    @OnClick(R.id.bt_photo)
    public void onClickPhoto() {
        takePhoto();
    }

    @OnClick(R.id.bt_check)
    public void onClickCheck() {
        if (lvPhoto.getCheckedItemPosition() >= 0) {
            Intent intent = new Intent(this, ShowPicActivity.class);
            checkPath = savePath + nameList.get(lvPhoto.getCheckedItemPosition());
            intent.putExtra(ShowPicActivity.ARG_IMAGE_PATH, checkPath);
            startActivity(intent);
        } else {
            showMaterialDialog("提示", "请选择一张图片", "", "确定");
        }
    }

    @OnClick(R.id.bt_delete)
    public void onClickDelete() {
        if (lvPhoto.getCheckedItemPosition() >= 0) {
            checkPath = savePath + nameList.get(lvPhoto.getCheckedItemPosition());
            File file = new File(checkPath);
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();

                    getPicName(savePath);
                    mAdapter = new PhotoAdapter(nameList, this);
                    lvPhoto.setAdapter(mAdapter);
                }
            }
        } else {
            showMaterialDialog("提示", "请选择一张图片", "", "确定");
        }
    }

    @OnClick(R.id.bt_return)
    public void onClickReturn() {
        finish();
    }

    @Override
    protected View setMyContentView() {
        mRootView = getLayoutInflater().inflate(R.layout.photo_activity, null);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    @Override
    protected void initView() {
        super.initView();
        mPermissionsChecker = new PermissionsChecker(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_OK) {
//            return;
//        }
        switch (requestCode) {
            case REQUEST_PHOTO:
                if (resultCode == RESULT_OK) {//拍照成功resultCode返回-1，失败则为0
                    getPicName(savePath);
//                nameList.add(theLarge)
                    mAdapter = new PhotoAdapter(nameList, this);
                    lvPhoto.setAdapter(mAdapter);
                }
                break;
            case REQUEST_PERMISSION_SETTING:
                isRequireCheck = true;
//                showMissingPermissionDialog();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRequireCheck) {
            if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                requestPermissions(PERMISSIONS);
                isRequireCheck = true;
            } else {
                isRequireCheck = true;
                // 判断是否挂载了SD卡
                String storageState = Environment.getExternalStorageState();
                if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                    savePath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/WLSD/外力三盯图片/";// 存放照片的文件夹
                    File savedir = new File(savePath);
                    if (!savedir.exists()) {
                        savedir.mkdirs();
                    }
                }

                getPicName(savePath);
                if (nameList.size() > 0) {
                    mAdapter = new PhotoAdapter(nameList, this);
                    lvPhoto.setAdapter(mAdapter);
                }
            }
        }

    }

    private void takePhoto() {

        // 没有挂载SD卡，无法保存文件
        if (TextUtils.isEmpty(savePath)) {
            showToast("无法保存照片，请检查SD卡是否挂载");
            return;
        }
        String timeStamp = new SimpleDateFormat(
                "yyyyMMddHHmmss")
                .format(new Date());
        String fileName = "IMG" + timeStamp
                + ".jpg";// 照片命名
        File out = new File(savePath, fileName);
        Uri uri = Uri.fromFile(out);

        theLarge = savePath + fileName;// 该照片的绝对路径

        Intent intent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(
                MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_PHOTO);
    }


    private void getPicName(String fileAbsolutePath) {
//        Vector<String> vecFile = new Vector<String>();
        nameList.clear();
        File file = new File(fileAbsolutePath);
        File[] subFile = file.listFiles();
        for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
            if (!subFile[iFileLength].isDirectory()) {
                String filename = subFile[iFileLength].getName();
                // 判断是否为jpg结尾
                if (filename.trim().toLowerCase().endsWith(".jpg")) {
//                    vecFile.add(filename);
                    nameList.add(filename);
                }
            }
        }
    }

    private class PhotoAdapter extends AdapterBase<String> {

        protected PhotoAdapter(List<String> dataList, Context context) {
            super(dataList, context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChoiceView view;
            if (convertView == null) {
                view = new ChoiceView(PermissionpicActivity.this);
            } else {
                view = (ChoiceView) convertView;
            }
            view.setText(getItem(position));
            return view;
        }
    }
}
