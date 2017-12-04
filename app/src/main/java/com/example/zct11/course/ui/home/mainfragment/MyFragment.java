package com.example.zct11.course.ui.home.mainfragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.ui.login.LoginActivity;
import com.example.zct11.course.ui.me.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/10/19.
 */

public class MyFragment extends Fragment implements View.OnClickListener {



    private Toolbar toolbar;
    private TextView nologin;
    private TextView name;
    private ImageView sexImg;
    private TextView studyTime;
    private LinearLayout login;
    private LinearLayout setting;

    public static MyFragment getInstance() {
        MyFragment myFragment = new MyFragment();
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my, null);
        init(v);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("我的空间");
        return v;
    }

    private void init(View v) {
        toolbar= (Toolbar) v.findViewById(R.id.tool);
        nologin= (TextView) v.findViewById(R.id.nologin);
        name= (TextView) v.findViewById(R.id.name);
        sexImg= (ImageView) v.findViewById(R.id.sex_img);
        studyTime= (TextView) v.findViewById(R.id.study_time);
        login= (LinearLayout) v.findViewById(R.id.login);
        setting=(LinearLayout) v.findViewById(R.id.setting);
        setting.setOnClickListener(this);
        nologin.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nologin:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }

    }
}
