package com.example.zct11.course.ui.home.mainfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zct11.course.R;

/**
 * Created by Administrator on 2017/10/19.
 */

public class MessageFragment extends Fragment {



    public static MessageFragment getInstance (){
        MessageFragment messageFragment=new MessageFragment();
        return  messageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message, null);
        return v;
    }
}
