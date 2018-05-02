package me.crazystone.study.wanandroid.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife

/**
 * Created by crazy_stone on 18-4-18.
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutId()
        ButterKnife.bind(this)
    }

    abstract fun setLayoutId()


}