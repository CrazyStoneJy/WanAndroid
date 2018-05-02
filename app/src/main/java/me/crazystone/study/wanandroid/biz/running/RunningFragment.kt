package me.crazystone.study.wanandroid.biz.running

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.crazystone.study.wanandroid.base.BaseFragment

/**
 * Created by crazy_stone on 18-4-18.
 */
class RunningFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val container = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null)
        val txt = container.findViewById<TextView>(android.R.id.text1)
        txt.setText("running")
        return container
    }

}