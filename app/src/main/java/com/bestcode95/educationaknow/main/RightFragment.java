package com.bestcode95.educationaknow.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bestcode95.educationaknow.R;
import com.bestcode95.educationaknow.utils.view.DealBitmap;
import com.bestcode95.educationaknow.utils.view.PathUtil;
import com.bestcode95.educationaknow.utils.view.RoundHeadView;

import java.io.File;

/**
 * Created by shiweixian on 2016/1/28.
 * 个人主页
 */
public class RightFragment extends Fragment implements View.OnClickListener {

    // 选择头像所用标志
    private static final int PHOTOFLAG = 0;
    private static final int IMAGEFLAG = 1;
    private static final int HEADIMAGEFLAG = 2;
    RoundHeadView headView;
    Button mySchoolBt;
    Button myCollectionBt;
    Button myAddressBt;
    // 创建PathUtil对象
    PathUtil util = new PathUtil();

    // 头像
    Bitmap head = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right, null);
        initView(view);
        return view;
    }

    /**
     * 初始化界面组件
     *
     * @param view
     */
    private void initView(View view) {
        headView = (RoundHeadView) view.findViewById(R.id.round_headView);
        mySchoolBt = (Button) view.findViewById(R.id.my_school_bt);
        myCollectionBt = (Button) view.findViewById(R.id.my_collections_bt);
        myAddressBt = (Button) view.findViewById(R.id.my_address_bt);
        headView.setOnClickListener(this);
        myAddressBt.setOnClickListener(this);
        mySchoolBt.setOnClickListener(this);
        myCollectionBt.setOnClickListener(this);
    }

    /**
     * 组件的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.round_headView:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                        .setTitle("修改头像：").setItems(R.array.head_fix_chose,
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (which) {
                                            // 调用系统相机
                                            case 0:
                                                Intent photoIntent = new Intent(
                                                        MediaStore.ACTION_IMAGE_CAPTURE);
                                                photoIntent.putExtra(
                                                        MediaStore.EXTRA_OUTPUT,
                                                        Uri.fromFile(new File(util
                                                                .getMinePath()
                                                                + "/head_image.jpg")));
                                                startActivityForResult(photoIntent,
                                                        PHOTOFLAG);
                                                break;
                                            // 从相册中选择图片
                                            case 1:
                                                Intent imageIntent = new Intent(
                                                        Intent.ACTION_PICK, null);
                                                imageIntent
                                                        .setDataAndType(
                                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                                                "image/*");
                                                startActivityForResult(imageIntent,
                                                        IMAGEFLAG);
                                                break;

                                            default:
                                                break;
                                        }
                                    }
                                });
                builder.create().show();
                break;
            case R.id.my_school_bt:
                break;
            case R.id.my_collections_bt:
                break;
            case R.id.my_address_bt:
                break;
            default:
                break;
        }
    }

    @SuppressWarnings("static-access")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 相机返回的照片
            case PHOTOFLAG:
                if (resultCode == getActivity().RESULT_OK) {
                    File temp = new File(getHeadFilePath());
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }
                break;
            case IMAGEFLAG:
                cropPhoto(data.getData());
            case HEADIMAGEFLAG:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        saveBitmap(head);// 保存在SD卡中，并同步到用户信息
                        headView.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, HEADIMAGEFLAG);
    }

    private void saveBitmap(Bitmap mBitmap) {
        mBitmap = DealBitmap.zipBitmap(mBitmap, 200, 200, true);// 压缩图片使之小于等于200*200
        DealBitmap.writeToFile(mBitmap, "png", getHeadFilePath());
        // //头像不要用JPEG
    }

    /**
     * 获取头像文件的路径
     *
     * @return
     */
    private String getHeadFilePath() {
        return util.getMinePath() + "/head_image.jpg";
    }

}
