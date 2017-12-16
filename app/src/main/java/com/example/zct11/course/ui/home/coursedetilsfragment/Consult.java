package com.example.zct11.course.ui.home.coursedetilsfragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.app.CourseApplication;
import com.example.zct11.course.message.InformationMessage;
import com.example.zct11.course.message.LoginMessage;
import com.example.zct11.course.ui.login.LoginActivity;
import com.example.zct11.course.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by zct11 on 2017/10/23.
 */

public class Consult extends Fragment implements View.OnClickListener{


    private TextView ask;
    private AlertDialog mDialog;

    public static Consult getInstance(){
        return new Consult();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_consult,null);
        ask=v.findViewById(R.id.ask);
        ask.setOnClickListener(this);
        return v;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ask:
                if(SPUtils.getSharedBooleanData(CourseApplication.getAppContext(),"islogin")){
                    //弹出框设置
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    //弹出框设置标题
                    builder1.setTitle("发布问题");
                    //弹出框自定义布局
                    LayoutInflater inflater1 = getLayoutInflater();
                    View view1 = inflater1.inflate(R.layout.item_edit, (ViewGroup) view.findViewById(R.id.ll_root), false);
                    final EditText editText = (EditText) view1.findViewById(R.id.ed_content);
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
                }else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                    builder1.setTitle("提示：你还没有登录！");
                    builder1.setMessage("前往登录界面？");
                    builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                        }
                    });
                    builder1.setNegativeButton("取消",null);
                    mDialog = builder1.create();
                    mDialog.show();
                }

                break;
        }
    }
}
