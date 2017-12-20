package com.example.zct11.course.ui.me;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zct11.course.R;
import com.example.zct11.course.databinding.ActivityAboutBinding;
import com.example.zct11.course.utils.BaseTools;

public class AboutActivity extends AppCompatActivity {

    private ActivityAboutBinding mbing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbing= DataBindingUtil.setContentView(this, R.layout.activity_about);
        mbing.toolbar.setTitle("关于V课堂");
        mbing.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mbing.tvVersionName.setText("当前版本："+BaseTools.getVersionName());


    }
}
