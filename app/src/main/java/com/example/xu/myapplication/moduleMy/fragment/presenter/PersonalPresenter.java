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
import android.text.TextUtils;
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
import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.activity.img.ClipImageActivity;
import com.example.xu.myapplication.moduleMy.fragment.view.CircleImageView;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IMyPersonal;
import com.example.xu.myapplication.util.BitmapUtil;
import com.example.xu.myapplication.util.FileUtil;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;
import com.vondear.rxtools.view.dialog.RxDialogEditSureCancel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 逝 on 2017/09/18.
 */

public class PersonalPresenter extends BasePresenter {
    private IMyPersonal iView;
    private SPUtil util;

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
    //请求昵称
    private static final int REQUEST_PET_NAME = 105;
    //请求邮箱
    private static final int REQUEST_EMAIL = 106;
    //调用照相机返回图片文件
    private File tempFile;

    private int type;

    private String old_img = "";
    private String old_nick = "";
    private int old_sex = 0;
    private String old_eamil = "";
    private String old_brith = "";
    private String head_img = "null";


    public PersonalPresenter(IMyPersonal view) {
        this.iView = view;
        util = new SPUtil(this.iView.getCon());
    }

    public void startIntent(Class<?> cls, int requestCode) {
        Intent intent = new Intent(iView.getCon(), cls);
        iView.getAct().startActivityForResult(intent, requestCode);
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
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (window.isShowing()) {
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
                    head_img = bitmapToBase64(BitmapUtil.compressImage(bitmap, 100));
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
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
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

    /**
     * 获得用户信息
     *
     * @param ivMyHead
     * @param tvPersonPetName
     * @param tvPersonSex
     * @param tvPersonBirth
     * @param tvPersonEmail
     */
    public void getUser(final CircleImageView ivMyHead, final TextView tvPersonPetName,
                        final TextView tvPersonSex, final TextView tvPersonBirth,
                        final TextView tvPersonEmail) {
        String phone = util.getString(SPUtil.IS_USER, "");
        if (TextUtils.equals(util.getString(SPUtil.IS_USER, ""), "")) {
            return;
        }
        JSONObject jo = new JSONObject();
        try {
            jo.put("phoneNumber", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(Common.URL_GET_USER, jo, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    String pwd = response.getString("password");
                    old_nick = response.getString("nickName");
                    old_img = response.getString("headImage");
                    old_brith = response.getString("birthday");
                    old_sex = response.getInt("sex");
                    old_eamil = response.getString("email");

                    //保存密码
                    util.putString(SPUtil.USER_PWD, pwd);

                    //头像
                    if (TextUtils.equals("null", head_img)) {
                        if (TextUtils.equals(old_img, "null")) {
                            ivMyHead.setImageDrawable(iView.getCon().getResources().getDrawable(
                                    R.mipmap.img_head));
                        } else {
                            ivMyHead.setImageBitmap(convertStringToIcon(old_img));
                        }
                    }
                    //昵称

                    if (TextUtils.equals(old_nick, "null")) {
                        tvPersonPetName.setText("");
                    } else {
                        tvPersonPetName.setText(old_nick);
                    }

                    //生日
                    if (TextUtils.equals(old_brith, "null")) {
                        tvPersonBirth.setText("");
                    } else {
                        tvPersonBirth.setText(old_brith.substring(0, 10));
                    }
                    //性别
                    switch (old_sex) {
                        case 0:
                            tvPersonSex.setText("男");
                            break;
                        case 1:
                            tvPersonSex.setText("女");
                            break;
                        default:
                            tvPersonSex.setText("保密");
                            break;
                    }
                    //邮箱

                    if (TextUtils.equals(old_eamil, "null")) {
                        tvPersonEmail.setText("");
                    } else {
                        tvPersonEmail.setText(old_eamil);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    /**
     * 邮箱对话框
     */
    public void rxDialog_NickName(final TextView tv) {
        final RxDialogEditSureCancel rxDialogEditSureCancel = new RxDialogEditSureCancel(iView
                .getCon());//提示弹窗
        rxDialogEditSureCancel.getTitleView().setBackgroundResource(R.drawable.logo);
        rxDialogEditSureCancel.getEditText().setHint("昵称(1-6位英文、中文)");
        rxDialogEditSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = "[A-Za-z\\u4e00-\\u9fa5]{1,6}";
                String nickName = rxDialogEditSureCancel.getEditText().getText().toString().trim();
                if (TextUtils.isEmpty(nickName)) {
                    ToastUtils.showToast(iView.getCon(), "昵称不能为空");
                    return;
                }
                Pattern pattern = Pattern.compile(key);
                Matcher matcher = pattern.matcher(nickName);
                if (matcher.matches()) {
                    tv.setText(nickName);
                    Logger.e("nick", nickName);
                    rxDialogEditSureCancel.cancel();
                } else {
                    ToastUtils.showToast(iView.getCon(), "昵称格式不正确");
                }
            }
        });
        rxDialogEditSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogEditSureCancel.cancel();
            }
        });
        rxDialogEditSureCancel.setCancelable(false);
        rxDialogEditSureCancel.show();
    }

    /**
     * 昵称对话框
     */
    public void rxDialog_Email(final TextView tv) {
        final RxDialogEditSureCancel rxDialogEditSureCancel = new RxDialogEditSureCancel(iView
                .getCon());//提示弹窗
        rxDialogEditSureCancel.getTitleView().setBackgroundResource(R.drawable.logo);
        rxDialogEditSureCancel.getEditText().setHint("邮箱");
        rxDialogEditSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
                String email = rxDialogEditSureCancel.getEditText().getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    ToastUtils.showToast(iView.getCon(), "邮箱不能为空");
                    return;
                }
                Pattern pattern = Pattern.compile(key);
                Matcher matcher = pattern.matcher(email);
                if (matcher.matches()) {
                    tv.setText(email);
                    rxDialogEditSureCancel.cancel();
                } else {
                    ToastUtils.showToast(iView.getCon(), "邮箱格式不正确");
                }
            }
        });
        rxDialogEditSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogEditSureCancel.cancel();
            }
        });
        rxDialogEditSureCancel.setCancelable(false);
        rxDialogEditSureCancel.show();
    }

    /**
     * 提交用户信息
     *
     * @param ivMyHead
     * @param tvPersonPetName
     * @param tvPersonSex
     * @param tvPersonBirth
     * @param tvPersonEmail
     */
    public void savePersonal(CircleImageView ivMyHead, TextView tvPersonPetName, TextView
            tvPersonSex, TextView tvPersonBirth, TextView tvPersonEmail) {
        String sex = tvPersonSex.getText().toString();
        int sex_id = 0;
        if (TextUtils.equals("男", sex)) {
            sex_id = 0;
        } else if (TextUtils.equals("女", sex)) {
            sex_id = 1;
        } else {
            sex_id = 2;
        }
        JSONObject jo = new JSONObject();
        try {
            jo.put("id", util.getString(SPUtil.USER_ID, ""));
            jo.put("phoneNumber", util.getString(SPUtil.IS_USER, ""));
            jo.put("password", util.getString(SPUtil.USER_PWD, ""));
            jo.put("nickName", tvPersonPetName.getText().toString());
            jo.put("birthday", tvPersonBirth.getText().toString() + "T10:00:00.000Z");
            jo.put("email", tvPersonEmail.getText().toString());
            jo.put("sex", sex_id);
            if (TextUtils.equals("null", head_img)) {
                jo.put("headImage", old_img);
            } else {
                jo.put("headImage", head_img);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyOkHttp.newInstance().postJson(Common.URL_UPDATE_USER, jo, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                if (statusCode == 200) {
                    ToastUtils.showToast(iView.getCon(), "个人信息修改成功");
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                ToastUtils.showToast(iView.getCon(), "个人信息修改失败，请稍后再试");
            }
        });
    }
}
