package com.example.zct11.course.ui.home.coursedetilsfragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zct11.course.R;
import com.example.zct11.course.client.NetworkUtil;
import com.example.zct11.course.ui.download.DownLoadModel;
import com.example.zct11.course.ui.download.DownloadActivity;

/**
 * Created by zct11 on 2017/10/23.
 */

public class Study extends Fragment implements View.OnClickListener{

    private ImageButton down;
    private AlertDialog mDialog;
    private int id=0;

    public static Study getInstance(){
        return new Study();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_study,null);
        down= (ImageButton) v.findViewById(R.id.down);
        down.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {

        id++;
        if(NetworkUtil.isNetworkAvailable(getContext())){
            new DownLoadModel(id).downLoad("http://pic.58pic.com/58pic/13/76/85/69x58PICm2u_1024.jpg","android");
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("提示");
            builder.setMessage("正在为你下载");
            builder.setPositiveButton("前往查看", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mDialog.dismiss();
                    startActivity(new Intent(getActivity(), DownloadActivity.class));
                }
            });
            mDialog = builder.create();
            mDialog.show();
        }else {
            Toast.makeText(getActivity(), "请检查当前的网络是否连接！！！", Toast.LENGTH_SHORT).show();
        }

    }
}
