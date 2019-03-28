package com.zyy.recyclerviewlayoutanimationdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CustomLayoutAnimationController.Callback {
    /**
     * 自定义动画执行顺序
     */
    override fun onIndex(controller: CustomLayoutAnimationController?, count: Int, index: Int): Int {
        return when (index) {
            0 -> 0
            1, 3 -> 1
            2, 4, 6 -> 3
            5, 7, 9 -> 4
            8, 10 -> 5
            11 -> 6
            else -> 0
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rlv.layoutManager = GridLayoutManager(this, 3)

        /* val layoutAnimationController = object : LayoutAnimationController(animation) {
             override fun getTransformedIndex(params: AnimationParameters?): Int {
                 Log.e("params", "${params!!.index} ")
                 return if (params!!.index >= 3) 1 else 0
                 //其中params.index 表示子控件在父控件中的位置，返回的参数表示动画执行顺序，值越小越先执行
                 //返回数值小代表先执行
                 //return if (params!!.index >= 3) 1 else 0 表示 如果当前view的索引大于3 即第四个view 就后执行动画（return 1），前面的先执行（return 0）
             }
         }*/
        rlv.adapter = RlvAdapter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.layout_animation_simple -> {
                val animation = AnimationUtils.loadAnimation(this, R.anim.rlv_scal)
                val layoutAnimationController = LayoutAnimationController(animation)
                layoutAnimationController.delay = 0.3f
                layoutAnimationController.order = LayoutAnimationController.ORDER_NORMAL
                rlv.layoutAnimation = layoutAnimationController
                notifyRlv()
            }
            R.id.layout_animation_custom -> {
                val animation = AnimationUtils.loadAnimation(this, R.anim.rlv_scal)
                val layoutAnimationController = CustomLayoutAnimationController(animation)
                layoutAnimationController.setOnIndexListener(this);
                layoutAnimationController.delay = 0.3f
                layoutAnimationController.order = LayoutAnimationController.ORDER_NORMAL
                rlv.layoutAnimation = layoutAnimationController
                notifyRlv()
            }
            R.id.layout_animation_xml -> {
                //通过 android:layoutAnimation="@anim/layout_animation_fall_down" 指定
            }
            R.id.layout_animation_notify -> {
                notifyRlv()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun notifyRlv() {
        rlv?.adapter?.notifyDataSetChanged()
        rlv.scheduleLayoutAnimation()
    }
}
