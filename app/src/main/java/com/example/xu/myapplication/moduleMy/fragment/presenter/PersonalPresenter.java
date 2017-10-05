package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.example.xu.myapplication.BuildConfig;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleMy.fragment.activity.img.ClipImageActivity;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IMyPersonal;
import com.example.xu.myapplication.util.FileUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 逝 on 2017/09/18.
 */

public class PersonalPresenter extends BasePresenter {
    private IMyPersonal iView;

    private TimePickerView pvTime;
    private OptionsPickerView opSex;
    private List<String> lists;

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片文件
    private File tempFile;

    private int type;

    public PersonalPresenter(IMyPersonal view) {
        this.iView = view;
    }

    public void startIntent(Class<?> cls) {
        Intent intent = new Intent(iView.getCon(), cls);
        iView.getAct().startActivity(intent);
    }

    /**
     * 时间选择器
     */
    public void timePicker(final TextView tv) {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1980, 0, 1);//设置起始时间
        Calendar endDate = Calendar.getInstance();
        endDate.set(2017, 11, 31);//设置末时间
        //时间选择器
        pvTime = new TimePickerView.Builder(iView.getCon(), new TimePickerView
                .OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                tv.setText(getTime(date));
            }
        })
                //年、月、日、时、分、秒  的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .isDialog(true)
                .build();
        pvTime.show();
    }

    //Date转换成String
    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 选择性别
     */
    public void chooseSex(final TextView tv) {
        lists = new ArrayList<String>();
        lists.add("男");
        lists.add("女");
        lists.add("保密");
        opSex = new OptionsPickerView.Builder(iView.getCon(), new OptionsPickerView
                .OnOptionsSelectListener
                () {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv.setText(lists.get(options1));
            }
        })
                .setDividerColor(Color.DKGRAY)
                .setContentTextSize(21)
                .setTextColorCenter(Color.DKGRAY)
                .isCenterLabel(true)
                .setOutSideCancelable(true)
                .setDecorView(null)
                .isDialog(true)
                .setSelectOptions(0)
                .build();
        opSex.setPicker(lists);
        opSex.show();
    }

    /**
     * 上传头像
     */
    public void uploadHeadImage(final Activity activity) {
        type = 1;
        View view = LayoutInflater.from(iView.getCon()).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);

        final PopupWindow window = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(activity.getResources().getDrawable(android.R.color
                .transparent));
        window.setOutsideTouchable(true);//点击PopuWindow外面会关闭PopuWindow

        window.setFocusable(true);
        /*
        解决PopupWindow 与键盘返回键冲突
         */
        view.setFocusable(true);// 设置view从键盘能够获取到焦点
        view.setFocusableInTouchMode(true);//设置view能从触摸获取到焦点
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_BACK){
                    if (window.isShowing()){
                        window.dismiss();
                        return true;
                    }
                }
                return false;
            }
        });


        View parent = LayoutInflater.from(iView.getCon()).inflate(R.layout.activity_main, null);
        window.showAtLocation(parent, Gravity.BOTTOM, 0, 0);

        final WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = 0.5f;
        activity.getWindow().setAttributes(params);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                activity.getWindow().setAttributes(params);
            }
        });


        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //权限判断
                if (ContextCompat.checkSelfPermission(iView.getCon(), Manifest.permission
                        .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest
                                    .permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    gotoCamare();
                }
                window.dismiss();
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //权限判断
                if (ContextCompat.checkSelfPermission(iView.getCon(), Manifest.permission
                        .READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(activity, new String[]{Manifest
                                    .permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相册
                    gotoPhoto();
                }
                window.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });

    }

    /**
     * 调用系统相机
     */
    public void gotoCamare() {
        Log.d("camare", "*****************打开相机********************");
        //创建拍照存储的拍照文件
        tempFile = new File(FileUtil.checkDirPath(Environment.getExternalStorageDirectory()
                .getPath() + "/image/"), System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(iView.getCon(), BuildConfig
                    .APPLICATION_ID + ".fileProvider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        iView.getAct().startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 调用系统相册
     */
    public void gotoPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media
                .EXTERNAL_CONTENT_URI);
        iView.getAct().startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }

    /**
     * 调用onActivityResult()方法 并根据不用的返回码 做出相应的处理
     *
     * @param requestCode 请求码
     * @param resultCode  返回码
     * @param data
     * @param iv          需要填充的iv
     */
    public void getActivityResult(int requestCode, int resultCode, Intent data, ImageView iv) {
        switch (requestCode) {
            case REQUEST_PICK://调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CAPTURE://调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_CROP_PHOTO://剪切图片返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String path = FileUtil.getRealFilePathFromUri(iView.getCon(), uri);
                    Log.e("path", path);
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    if (type == 1) {
                        iv.setImageBitmap(bitmap);
                    }
                    /**
                     * 此处后面可以将bitmap转为二进制上传后台网络
                     */
                    bitmapToBase64(bitmap);
                }
                break;
        }
    }

    /**
     * Bitmap 转 Base64 方法
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                Log.e("bitmapBytes", bitmapBytes.toString());
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * string转成bitmap
     *
     * @param str
     */
    public static Bitmap convertStringToIcon(String str) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(str, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param requestCode
     * @param permissions
     * @param grantResults
     */

    public void getRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                            @NonNull int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gotoCamare();
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gotoPhoto();
            }
        }
    }

    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent(iView.getCon(), ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        iView.getAct().startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }
}
