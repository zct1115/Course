package com.example.zct11.course.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.MypagerAdapter;
import com.example.zct11.course.ui.home.mainfragment.DownLoadFragment;
import com.example.zct11.course.ui.home.mainfragment.MainFragment;
import com.example.zct11.course.ui.home.mainfragment.MessageFragment;
import com.example.zct11.course.ui.home.mainfragment.MyFragment;
import com.example.zct11.course.utils.ImageLoaderUtils;
import com.example.zct11.course.utils.ViewFindUtils;
import com.example.zct11.course.video.MyUserActionStandard;
import com.example.zct11.course.widget.statusbar.StatusBarUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class CourseDetilsActivity extends AppCompatActivity {

    private String[] mTitles = {"视频介绍", "学习章节", "评价", "咨询"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private View view;
    private ViewPager viewPager;
    private MypagerAdapter adapter;
    private SlidingTabLayout slidingPaneLayout;
    private JCVideoPlayerStandard mJcVideoPlayerStandard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detils);
        StatusBarUtil.setTranslucent(this);
        view=getWindow().getDecorView();
        viewPager= (ViewPager) findViewById(R.id.vp);
        mJcVideoPlayerStandard= (JCVideoPlayerStandard) findViewById(R.id.jc_video);
        mJcVideoPlayerStandard.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, "");
        ImageLoaderUtils.display(this,mJcVideoPlayerStandard.thumbImageView,"http://img1.sycdn.imooc.com/szimg/5909a1250001197e05400300-360-202.jpg");
        JCVideoPlayer.setJcUserAction(new MyUserActionStandard());
        slidingPaneLayout= ViewFindUtils.find(view,R.id.tl);
        initData();
        adapter = new MypagerAdapter(getSupportFragmentManager(), mFragments);
        viewPager.setAdapter(adapter);
        slidingPaneLayout.setViewPager(viewPager,mTitles);
        slidingPaneLayout.setCurrentTab(0);
        slidingPaneLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(4);

    }

    private void initData() {
        mFragments.add(MainFragment.getInstance());
        mFragments.add(MessageFragment.getInstance());
        mFragments.add(DownLoadFragment.getInstance());
        mFragments.add(MyFragment.getInstance());
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
}
