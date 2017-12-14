package com.example.zct11.course.ui.me;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.app.CourseApplication;
import com.example.zct11.course.message.LoginMessage;
import com.example.zct11.course.ui.download.DownloadActivity;
import com.example.zct11.course.utils.CleanMessageUtil;
import com.example.zct11.course.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private LinearLayout clean;
    private LinearLayout loginout;
    private LinearLayout version;
    private TextView num;

    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("设置");
           /*回退键触发事件*/
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        num= (TextView) findViewById(R.id.num);
        try {
            num.setText(CleanMessageUtil.getTotalCacheSize(CourseApplication.getAppContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        clean= (LinearLayout) findViewById(R.id.clean);
        version= (LinearLayout) findViewById(R.id.version);
        loginout= (LinearLayout) findViewById(R.id.loaded);

        clean.setOnClickListener(this);
        version.setOnClickListener(this);
        loginout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.version:
                break;
            case R.id.loaded:

                final SharedPreferences sharedPreferences=getSharedPreferences("use", Context.MODE_PRIVATE);
                if(!sharedPreferences.getBoolean("islogin",false)){
                    ToastUtil.showToast("你还没有登录！！");
                }else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setTitle("提示");
                    builder1.setMessage("确认退出登录？");
                    builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("islogin",false);
                            editor.commit();
                            EventBus.getDefault().post(new LoginMessage(false));
                        }
                    });
                    builder1.setNegativeButton("取消",null);
                    mDialog = builder1.create();
                    mDialog.show();
                }

                break;
            case R.id.clean:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("确认清除缓存数据");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CleanMessageUtil.clearAllCache(CourseApplication.getAppContext());
                        try {
                            num.setText(CleanMessageUtil.getTotalCacheSize(CourseApplication.getAppContext()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                builder.setNegativeButton("取消",null);
                mDialog = builder.create();
                mDialog.show();
                break;
        }
    }


}
