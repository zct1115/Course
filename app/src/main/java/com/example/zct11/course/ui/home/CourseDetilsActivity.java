package com.example.zct11.course.ui.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.CoursepagerAdapter;
import com.example.zct11.course.adapter.MypagerAdapter;
import com.example.zct11.course.app.CourseApplication;
import com.example.zct11.course.ui.home.coursedetilsfragment.Assess;
import com.example.zct11.course.ui.home.coursedetilsfragment.Consult;
import com.example.zct11.course.ui.home.coursedetilsfragment.Introduction;
import com.example.zct11.course.ui.home.coursedetilsfragment.Study;
import com.example.zct11.course.ui.home.mainfragment.DownLoadFragment;
import com.example.zct11.course.ui.home.mainfragment.MainFragment;
import com.example.zct11.course.ui.home.mainfragment.MessageFragment;
import com.example.zct11.course.ui.home.mainfragment.MyFragment;
import com.example.zct11.course.ui.login.LoginActivity;
import com.example.zct11.course.utils.ImageLoaderUtils;
import com.example.zct11.course.utils.SPUtils;
import com.example.zct11.course.utils.ToastUtil;
import com.example.zct11.course.utils.ViewFindUtils;
import com.example.zct11.course.video.JCVideoPlayerStandardAutoCompleteAfterFullscreen;
import com.example.zct11.course.video.MyUserActionStandard;
import com.example.zct11.course.widget.MyPopupWindow;
import com.example.zct11.course.widget.statusbar.StatusBarUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class CourseDetilsActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] mTitles = {"介绍", "章节", "评价", "咨询"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private View view;
    private ViewPager viewPager;
    private TextView textView;
    private CoursepagerAdapter adapter;
    private SlidingTabLayout slidingPaneLayout;
    private JCVideoPlayerStandardAutoCompleteAfterFullscreen mJcVideoPlayerStandard;
    private ImageView back;
    private MyPopupWindow myPopupWindow;
    private ImageView love;
    private boolean flag = false;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detils);
        StatusBarUtil.setTranslucent(this, 1000);
        view = getWindow().getDecorView();
        back = (ImageView) findViewById(R.id.back);
        textView = (TextView) findViewById(R.id.tool_title);
        love = (ImageView) findViewById(R.id.love);
        love.setOnClickListener(this);
        back.setOnClickListener(this);
        viewPager = (ViewPager) findViewById(R.id.vp);
        initPop();
        mJcVideoPlayerStandard = (JCVideoPlayerStandardAutoCompleteAfterFullscreen) findViewById(R.id.jc_video);
        mJcVideoPlayerStandard.setUp("http://newoss.maiziedu.com/android_app_sde_1.mp4"
                , JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
        ImageLoaderUtils.display(this, mJcVideoPlayerStandard.thumbImageView, "http://img1.sycdn.imooc.com/szimg/5909a1250001197e05400300-360-202.jpg");
        JCVideoPlayer.setJcUserAction(new MyUserActionStandard("http://newoss.maiziedu.com/android_app_sde_1.mp4", "http://img1.sycdn.imooc.com/szimg/5909a1250001197e05400300-360-202.jpg", textView.getText().toString()));
        slidingPaneLayout = ViewFindUtils.find(view, R.id.tl);
        initData();
        adapter = new CoursepagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(adapter);
        slidingPaneLayout.setViewPager(viewPager, mTitles);
        slidingPaneLayout.setCurrentTab(0);
        slidingPaneLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                viewPager.setCurrentItem(position);
                if (position == 2) {
                    myPopupWindow.showAtLocation(viewPager, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                } else {
                    myPopupWindow.dismiss();
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 2) {
                    myPopupWindow.showAtLocation(viewPager, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    myPopupWindow.dismiss();
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(4);

    }

    private void initPop() {
        LayoutInflater inflater1 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View edit = inflater1.inflate(R.layout.assess, null, false);
        TextView text = edit.findViewById(R.id.text);
        ImageView send = edit.findViewById(R.id.send);
        send.setOnClickListener(this);
        myPopupWindow = new MyPopupWindow(CourseDetilsActivity.this, edit);
        myPopupWindow.setFocusable(true);
        myPopupWindow.setOutsideTouchable(false);
    }

    private void initData() {
        mFragments.add(Introduction.getInstance());
        mFragments.add(Study.getInstance());
        mFragments.add(Assess.getInstance());
        mFragments.add(Consult.getInstance());
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.love:
                if (!flag) {
                    love.setImageDrawable(getResources().getDrawable(R.drawable.ic_love));
                    ToastUtil.showToast("收藏成功");
                    flag = true;
                } else {
                    love.setImageDrawable(getResources().getDrawable(R.drawable.ic_nolove));
                    ToastUtil.showToast("取消收藏");
                    flag = false;
                }
                break;
            case R.id.send:
                if (!SPUtils.getSharedBooleanData(CourseApplication.getAppContext(), "islogin")) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setTitle("提示：你还没有登录！");
                    builder1.setMessage("前往登录界面？");
                    builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(CourseDetilsActivity.this, LoginActivity.class));
                        }
                    });
                    builder1.setNegativeButton("取消", null);
                    mDialog = builder1.create();
                    mDialog.show();
                }else {

                }
                break;
        }

    }
}
