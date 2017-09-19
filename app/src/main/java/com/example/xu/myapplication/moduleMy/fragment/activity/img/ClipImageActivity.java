package com.example.xu.myapplication.moduleMy.fragment.activity.img;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.moduleMy.fragment.view.ClipViewLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;


/**
 * 头像裁剪Activity
 */
public class ClipImageActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ClipImageActivity";
    private ClipViewLayout clipViewLayout;
    private ImageView back;
    private TextView btnCancel;
    private TextView btnOk;
    //类别 1: qq
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_image);
        type = getIntent().getIntExtra("type", 1);
        initView();
    }

    /**
     * 初始化组件
     */
    public void initView() {
        clipViewLayout = (ClipViewLayout) findViewById(R.id.clipViewLayout);
        back = (ImageView) findViewById(R.id.iv_back);
        btnCancel = (TextView) findViewById(R.id.btn_cancel);
        btnOk = (TextView) findViewById(R.id.bt_ok);
        //设置点击事件监听器
        back.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.bt_ok:
                generateUriAndReturn();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (type == 1) {
            clipViewLayout.setImageSrc(getIntent().getData());
        }
    }

    /**
     * 生成Uri并且通过setResult返回给打开的activity
     */
    private void generateUriAndReturn() {
        //调用返回的剪切图
        Bitmap bitmap = null;
        if (type == 1) {
            bitmap = clipViewLayout.clip();
        }

        if (bitmap == null) {
            Log.e("android", "zoomedCropBitmap == null");
            return;
        }

        Uri uri=Uri.fromFile(new File(getCacheDir(),"cropped_"+System.currentTimeMillis()+".jpg"));
        if (uri!=null){
            OutputStream os=null;
            try {
                os=getContentResolver().openOutputStream(uri);
                if (os!=null){
                    bitmap.compress(Bitmap.CompressFormat.JPEG,90,os);
                }
            } catch (FileNotFoundException e) {
                Log.e("android", "Cannot open file: " + uri, e);
                e.printStackTrace();
            }finally {
                if (os!=null){
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            Intent intent=new Intent();
            intent.setData(uri);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
