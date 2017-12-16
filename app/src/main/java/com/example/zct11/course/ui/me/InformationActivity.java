package com.example.zct11.course.ui.me;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loading.SweetAlertDialog;
import com.example.zct11.course.R;
import com.example.zct11.course.app.CourseApplication;
import com.example.zct11.course.message.InformationLogin;
import com.example.zct11.course.message.InformationMessage;
import com.example.zct11.course.message.InformationSexMessage;
import com.example.zct11.course.message.Informationimg;
import com.example.zct11.course.utils.BitmapUtils;
import com.example.zct11.course.utils.CapturePhotoHelper;
import com.example.zct11.course.utils.FolderManager;
import com.example.zct11.course.utils.ImageLoaderUtils;
import com.example.zct11.course.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener{


    private Toolbar toolbar;
    private LinearLayout head;
    private LinearLayout name;
    private LinearLayout sex;
    private TextView name_text;
    private TextView sex_text;
    private ImageView img;
    private ImageView save;

    private File getFile;

    //请求开启相册
    private static final int REQUEST_PICK_IMAGE = 3;
    //显示图像
    private static final int REQUEST_PICKER_AND_CROP_2 = 4;
    private final static int REQUEST_PICKER_AND_CROP = 2;
    //用于拍照的类
    private CapturePhotoHelper mCapturePhotoHelper;
    //照片文件
    private File mRestorePhotoFile;
    //文件用于保存拍照后图片
    private File tempFile = new File(Environment.getExternalStorageDirectory(), SPUtils.getSharedStringData(CourseApplication.getAppContext(), "userid") + ".jpg");
    //保存文件目录
    private final static String EXTRA_RESTORE_PHOTO = "extra_restore_photo";
    /**
     * 运行时权限申请码
     */
    private final static int RUNTIME_PERMISSION_REQUEST_CODE = 0x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        EventBus.getDefault().register(this);
        name_text= (TextView) findViewById(R.id.name_text);
        sex_text= (TextView) findViewById(R.id.sex_text);
        img= (ImageView) findViewById(R.id.img);
        save= (ImageView) findViewById(R.id.save);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("完善你的信息");
           /*回退键触发事件*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        head= (LinearLayout) findViewById(R.id.head);
        name= (LinearLayout) findViewById(R.id.name);
        sex= (LinearLayout) findViewById(R.id.sex);
        save.setOnClickListener(this);
        head.setOnClickListener(this);
        name.setOnClickListener(this);
        sex.setOnClickListener(this);

        Bitmap bitmap=BitmapUtils.decodeBitmapFromFile(SPUtils.getSharedStringData(CourseApplication.getAppContext(),"file"),img.getDrawable().getBounds().width(),img.getDrawable().getBounds().height());
        if(bitmap!=null){
            img.setImageBitmap(bitmap);
        }
        name_text.setText(SPUtils.getSharedStringData(CourseApplication.getAppContext(),"name"));
        sex_text.setText(SPUtils.getSharedStringData(CourseApplication.getAppContext(),"sex"));
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("选择图片");
                LayoutInflater inflater=getLayoutInflater();
                View view=inflater.inflate(R.layout.item_photo_select,(ViewGroup)findViewById(R.id.ll_root) ,false);
                TextView mTv_take_photo = (TextView) view.findViewById(R.id.tv_take_photo);
                TextView mTv_select_photo = (TextView) view.findViewById(R.id.tv_select_photo);
                builder.setView(view, 100, 30, 100, 30);
                final AlertDialog dialog = builder.create();
                //拍照
                mTv_take_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //首先获取权限，android6.0后需要获取权限才能拍照
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //Android M 处理Runtime Permission
                            if (ActivityCompat.checkSelfPermission(InformationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {//检查是否有写入SD卡的授权
                                Log.d("MyInformationActivity", "granted permission!");
                                turnOnCamera();
                            } else {
                                Log.i("MyInformationActivity", "denied permission!");
                                if (ActivityCompat.shouldShowRequestPermissionRationale(InformationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                    Log.i("MyInformationActivity", "should show request permission rationale!");
                                }
                                requestPermission();
                            }
                        } else {
                            turnOnCamera();
                        }
                        if (dialog != null) {
                            dialog.dismiss();
                        }

                    }
                });
                //从本地相册中获取图片
                mTv_select_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //首先获取权限
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //Android M 处理Runtime Permission
                            if (ActivityCompat.checkSelfPermission(InformationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {//检查是否有写入SD卡的授权
                                Log.i("MyInformationActivity", "granted permission!");
                                turnOnAlbum();
                            } else {
                                Log.i("MyInformationActivity", "denied permission!");
                                if (ActivityCompat.shouldShowRequestPermissionRationale(InformationActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                    Log.i("MyInformationActivity", "should show request permission rationale!");
                                }
                                requestPermission();
                            }
                        } else {
                            turnOnAlbum();
                        }
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });
                dialog.show();
                break;
            case R.id.name:
                //弹出框设置
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                //弹出框设置标题
                builder1.setTitle("修改用户名");
                //弹出框自定义布局
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.item_edit, (ViewGroup) findViewById(R.id.ll_root), false);
                final EditText editText = (EditText) view1.findViewById(R.id.ed_content);
                //设置用户名
                editText.setText(name_text.getText().toString());
                //显示光标
                editText.setSelection(editText.getText().length());
                builder1.setView(view1, 100, 30, 100, 30);
                final AlertDialog dialog1 = builder1.create();
                Button bt_positive = (Button) view1.findViewById(R.id.bt_positive);
                Button bt_negative = (Button) view1.findViewById(R.id.bt_negative);
                //弹出框点击事件的设置
                bt_positive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //判断当前用户名是否为空或者是未设置
                        if (!editText.getText().toString().isEmpty() && !editText.getText().toString().equals("未设置")) {
                            EventBus.getDefault().post(new InformationMessage(editText.getText().toString()));
                        }
                        dialog1.dismiss();
                    }
                });
                bt_negative.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                dialog1.show();
                break;
            case R.id.sex:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("修改性别");
                LayoutInflater inflater2 = getLayoutInflater();
                View view2 = inflater2.inflate(R.layout.item_gender_select, (ViewGroup) findViewById(R.id.rg_root), false);
                //用单选择控件
                final RadioButton rb_male = (RadioButton) view2.findViewById(R.id.rb_male);
                final RadioButton rb_female = (RadioButton) view2.findViewById(R.id.rb_female);
                if(sex_text.getText().equals("男")){
                    rb_male.setChecked(true);
                }else if (sex_text.getText().equals("女")){
                    rb_female.setChecked(true);
                }
                builder2.setView(view2, 100, 30, 100, 30);
                final AlertDialog dialog2 = builder2.create();
                Button bt_positive1 = (Button) view2.findViewById(R.id.bt_positive);
                Button bt_negative1 = (Button) view2.findViewById(R.id.bt_negative);
                bt_positive1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String sex="";
                        if (rb_female.isChecked()) {
                            sex="女";
                        }
                        if (rb_male.isChecked()) {
                            sex="男";
                        }
                        EventBus.getDefault().post(new InformationSexMessage(sex));
                        dialog2.dismiss();
                    }
                });
                bt_negative1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                dialog2.show();

                break;
            case R.id.save:
                new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("保存成功！")
                        .setConfirmText("OK")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                if(getFile!=null){
                                    SPUtils.setSharedStringData(CourseApplication.getAppContext(),"file",getFile.toString());
                                }else {
                                    SPUtils.setSharedStringData(CourseApplication.getAppContext(),"file",SPUtils.getSharedStringData(CourseApplication.getAppContext(),"file"));
                                }
                                SPUtils.setSharedStringData(CourseApplication.getAppContext(),"name",name_text.getText().toString());
                                SPUtils.setSharedStringData(CourseApplication.getAppContext(),"sex",sex_text.getText().toString());
                                EventBus.getDefault().post(new InformationLogin(getFile,name_text.getText().toString(),sex_text.getText().toString()));
                                sDialog.dismiss();
                            }
                        })
                        .show();
                break;
        }
    }



    //拍照完成后 获取目标文件 跳转到裁剪页面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CapturePhotoHelper.CAPTURE_PHOTO_REQUEST_CODE) {
            //获取拍照后图片路径
            File photoFile = mCapturePhotoHelper.getPhoto();

            if (photoFile != null) {
                if (resultCode == RESULT_OK) {
                    Uri uri = Uri.fromFile(photoFile);
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(uri, "image/*");
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    intent.putExtra("outputX", 300);
                    intent.putExtra("outputY", 300);
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    intent.putExtra("return-data", false);
                    intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                    intent.putExtra("noFaceDetection", true); // no face detection
                    intent = Intent.createChooser(intent, "裁剪图片");
                    startActivityForResult(intent, REQUEST_PICKER_AND_CROP);
                } else {
                    if (photoFile.exists()) {
                        photoFile.delete();
                    }
                }
            }

        } else if (requestCode == REQUEST_PICKER_AND_CROP) {
            File photoFile = mCapturePhotoHelper.getPhoto();
            Log.d("InformationActivity", "photoFile:" + photoFile);
            //BitmapUtils.displayToGallery(this, photoFile);
            getFile=photoFile;
            EventBus.getDefault().post(new Informationimg(photoFile));

        } else if (requestCode == REQUEST_PICK_IMAGE) {
            //获取选择图片后图片路径

            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(uri, "image/*");
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 200);
                intent.putExtra("scale", true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                intent.putExtra("return-data", false);
                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                intent.putExtra("noFaceDetection", true); // no face detection
                intent = Intent.createChooser(intent, "裁剪图片");
                startActivityForResult(intent, REQUEST_PICKER_AND_CROP_2);
            }
        } else if (requestCode == REQUEST_PICKER_AND_CROP_2) {
            getFile=tempFile.getAbsoluteFile();
            EventBus.getDefault().post(new Informationimg(tempFile.getAbsoluteFile()));

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    /**
     * 开启相册
     */
    private void turnOnAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_IMAGE);

    }

    /**
     * 开启相机
     */
    private void turnOnCamera() {
        if (mCapturePhotoHelper == null) {
            mCapturePhotoHelper = new CapturePhotoHelper(this, FolderManager.getPhotoFolder());
        }
        mCapturePhotoHelper.capture();
    }

    /**
     * 申请写入sd卡的权限
     */
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RUNTIME_PERMISSION_REQUEST_CODE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //应用恢复时 获取拍照保存的文件目录
        if (mCapturePhotoHelper != null) {
            mRestorePhotoFile = (File) savedInstanceState.getSerializable(EXTRA_RESTORE_PHOTO);
            mCapturePhotoHelper.setPhoto(mRestorePhotoFile);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //应用闪退时，保留拍照保存的文件目录
        if (mCapturePhotoHelper != null) {
            mRestorePhotoFile = mCapturePhotoHelper.getPhoto();
            if (mRestorePhotoFile != null) {
                outState.putSerializable(EXTRA_RESTORE_PHOTO, mRestorePhotoFile);
            }
        }
    }

    /**
     * 权限返回结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RUNTIME_PERMISSION_REQUEST_CODE) {
            for (int index = 0; index < permissions.length; index++) {
                String permission = permissions[index];
                if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
                    if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                        turnOnCamera();

                    } else {
                        showMissingPermissionDialog();

                    }
                }
            }
        }
    }


    /**
     * 显示打开权限提示的对话框
     */
    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("帮助");
        builder.setMessage("当前权限被禁用，建议到设置界面开启权限!");

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(InformationActivity.this, "启动相机失败", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                turnOnSettings();
            }
        });

        builder.show();
    }

    /**
     * 启动系统权限设置界面
     */
    private void turnOnSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    @Subscribe
    public void change(InformationMessage message){
        if(!message.getName().equals("")){
            name_text.setText(message.getName());
        }

    }

    @Subscribe
    public void imgchange(Informationimg informationimg){
        //存放到相册
        Bitmap bitmap=BitmapUtils.decodeBitmapFromFile(informationimg.getFile(),img.getDrawable().getBounds().width(),img.getDrawable().getBounds().height());
        if(bitmap!=null){
            img.setImageBitmap(bitmap);
        }
    }

    @Subscribe
    public void changesex(InformationSexMessage informationSexMessage){
        sex_text.setText(informationSexMessage.getSex());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
