package com.zyy.recyclerviewlayoutanimationdemo;

import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;

/**
 * 作者 : zyy（赵岩）
 * 时间 : 2019/3/28
 */
public class CustomLayoutAnimationController extends LayoutAnimationController {

    private Callback onIndexListener;

    public void setOnIndexListener(Callback onIndexListener) {
        this.onIndexListener = onIndexListener;
    }

    public CustomLayoutAnimationController(Animation anim) {
        super(anim);
    }

    @Override
    protected int getTransformedIndex(AnimationParameters params) {
        if (onIndexListener != null) {
            return onIndexListener.onIndex(this, params.count, params.index);
        } else {
            return super.getTransformedIndex(params);
        }
    }

    public interface Callback {
        int onIndex(CustomLayoutAnimationController controller, int count, int index);
    }
}
