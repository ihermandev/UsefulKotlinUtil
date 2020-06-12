package com.iherman.util

import android.view.View

/**
 * Created by Illia Herman on 11.06.2020.
 */

/**
 * View visibility func
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun applyViewVisible(vararg view: View) =
    view.forEach {
        it.visible()
    }


fun applyViewInvisible(vararg view: View) =
    view.forEach {
        it.invisible()
    }


fun applyViewGone(vararg view: View) =
    view.forEach {
        it.gone()
    }
