package me.crazystone.study.wanandroid.utils

/**
 * Created by crazy_stone on 18-5-2.
 */
object Collections {

    fun <E> isEmpty(array: Collection<E>?): Boolean {
        if (array != null && array.size > 0) {
            return false
        }
        return true
    }

    fun <E> notEmpty(array: Collection<E>?): Boolean {
        return !isEmpty(array)
    }

}