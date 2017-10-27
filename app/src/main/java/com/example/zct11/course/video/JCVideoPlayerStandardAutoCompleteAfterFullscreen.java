package com.example.zct11.course.video;

import android.content.Context;
import android.util.AttributeSet;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by zct11 on 2017/10/25.
 */

public class JCVideoPlayerStandardAutoCompleteAfterFullscreen extends JCVideoPlayerStandard {
    public JCVideoPlayerStandardAutoCompleteAfterFullscreen(Context context) {
        super(context);
    }

    public JCVideoPlayerStandardAutoCompleteAfterFullscreen(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onAutoCompletion() {
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            setUiWitStateAndScreen(CURRENT_STATE_AUTO_COMPLETE);
        } else {
            super.onAutoCompletion();
        }

    }
}
