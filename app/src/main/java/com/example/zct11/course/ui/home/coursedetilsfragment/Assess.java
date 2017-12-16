package com.example.zct11.course.ui.home.coursedetilsfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.message.SupporNum;
import com.example.zct11.course.ui.me.UserInformationActivity;
import com.example.zct11.course.widget.CircleImageView;
import com.example.zct11.course.widget.MyPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by zct11 on 2017/10/23.
 */

public class Assess extends Fragment implements View.OnClickListener {

    private ImageView suppor;
    private boolean tag=false;
    private TextView num_suppor;
    private CircleImageView people;
    private LinearLayout li;
    private int num;

    public static Assess getInstance(){
        return new Assess();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_assess,null);
        suppor=v.findViewById(R.id.suppor);
        li=v.findViewById(R.id.li);
        num_suppor=v.findViewById(R.id.num_suppor);
        people=v.findViewById(R.id.people);
        people.setOnClickListener(this);
        num= Integer.parseInt(num_suppor.getText().toString());
        suppor.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.suppor:
                if(!tag){
                    num++;
                    tag=true;
                    suppor.setImageDrawable(getResources().getDrawable(R.drawable.ic_dianzan_on));
                    num_suppor.setText(num+"");

                }else {
                    tag=false;
                    num--;
                    suppor.setImageDrawable(getResources().getDrawable(R.drawable.ic_dianzan));
                    num_suppor.setText(num+"");
                }

                break;
            case R.id.people:
                startActivity(new Intent(getActivity(), UserInformationActivity.class));
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
