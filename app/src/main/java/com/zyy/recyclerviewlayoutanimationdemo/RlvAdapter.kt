package com.zyy.recyclerviewlayoutanimationdemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 *   作者 : zyy（赵岩）
 *   时间 : 2019/3/28
 */
class RlvAdapter(var context: Context) : RecyclerView.Adapter<RlvAdapter.Holder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        val view = from(context).inflate(R.layout.item, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = 12

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv?.text="position:$position"
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var tv:TextView ?= null

        init {
            tv = itemView.findViewById(R.id.item_tv)
        }
    }
}