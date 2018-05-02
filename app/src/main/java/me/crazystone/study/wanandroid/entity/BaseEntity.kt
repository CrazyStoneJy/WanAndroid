package me.crazystone.study.wanandroid.entity

/**
 * Created by crazy_stone on 18-4-28.
 */
open class BaseEntity<T> {
    var data: T? = null
    var errorCode: Int = 0
    var errorMsg: String = ""
}