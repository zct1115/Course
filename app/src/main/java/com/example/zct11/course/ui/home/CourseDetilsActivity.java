package com.example.zct11.course.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zct11.course.R;
import com.example.zct11.course.adapter.CoursepagerAdapter;
import com.example.zct11.course.adapter.MypagerAdapter;
import com.example.zct11.course.ui.home.coursedetilsfragment.Assess;
import com.example.zct11.course.ui.home.coursedetilsfragment.Consult;
import com.example.zct11.course.ui.home.coursedetilsfragment.Introduction;
import com.example.zct11.course.ui.home.coursedetilsfragment.Study;
import com.example.zct11.course.ui.home.mainfragment.DownLoadFragment;
import com.example.zct11.course.ui.home.mainfragment.MainFragment;
import com.example.zct11.course.ui.home.mainfragment.MessageFragment;
import com.example.zct11.course.ui.home.mainfragment.MyFragment;
import com.example.zct11.course.utils.ImageLoaderUtils;
import com.example.zct11.course.utils.ViewFindUtils;
import com.example.zct11.course.video.JCVideoPlayerStandardAutoCompleteAfterFullscreen;
import com.example.zct11.course.video.MyUserActionStandard;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detils);
        StatusBarUtil.setTranslucent(this,1000);
        view=getWindow().getDecorView();
        back= (ImageView) findViewById(R.id.back);
        textView=(TextView)findViewById(R.id.tool_title);
        back.setOnClickListener(this);
        viewPager= (ViewPager) findViewById(R.id.vp);
        mJcVideoPlayerStandard= (JCVideoPlayerStandardAutoCompleteAfterFullscreen) findViewById(R.id.jc_video);
        mJcVideoPlayerStandard.setUp("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4"
                , JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
        ImageLoaderUtils.display(this,mJcVideoPlayerStandard.thumbImageView,"http://img1.sycdn.imooc.com/szimg/5909a1250001197e05400300-360-202.jpg");
        JCVideoPlayer.setJcUserAction(new MyUserActionStandard("http://video.jiecao.fm/11/23/xin/%E5%81%87%E4%BA%BA.mp4","http://img1.sycdn.imooc.com/szimg/5909a1250001197e05400300-360-202.jpg",textView.getText().toString()));
        slidingPaneLayout= ViewFindUtils.find(view,R.id.tl);
        initData();
        adapter = new CoursepagerAdapter(getSupportFragmentManager(), mFragments);
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
        finish();
    }
}
