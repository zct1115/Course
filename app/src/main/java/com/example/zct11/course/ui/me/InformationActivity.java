package com.example.zct11.course.ui.me;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.zct11.course.R;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener{


    private Toolbar toolbar;
    private LinearLayout head;
    private LinearLayout name;
    private LinearLayout sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

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

        head.setOnClickListener(this);
        name.setOnClickListener(this);
        sex.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head:
                break;
            case R.id.name:
                break;
            case R.id.sex:
                break;
        }
    }
}
