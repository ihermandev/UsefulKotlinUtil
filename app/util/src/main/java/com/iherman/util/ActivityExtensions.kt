package com.iherman.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.iherman.util.Const.FLAGS_FULLSCREEN

/**
 * Created by Illia Herman on 01.06.2020.
 */

/**
 * Fragment Transactions
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun FragmentManager.inTransactionAnimated(
    enter: Int,
    exit: Int,
    popEnter: Int,
    popExit: Int,
    func: FragmentTransaction.() -> FragmentTransaction
) =
    beginTransaction().setCustomAnimations(
        enter, exit, popEnter, popExit
    ).func().commit()

fun AppCompatActivity.popBackStack() = supportFragmentManager.popBackStack()

fun AppCompatActivity.popBackStackInclusive() {
    if (supportFragmentManager.backStackEntryCount > 0)
        supportFragmentManager.popBackStack(
            supportFragmentManager.getBackStackEntryAt(0).id,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) =
    supportFragmentManager.inTransaction {
        add(frameId, fragment, fragment.javaClass.simpleName)
    }

fun AppCompatActivity.addFragmentAnimated(
    fragment: Fragment,
    frameId: Int,
    enter: Int = R.anim.slide_in_right,
    exit: Int = R.anim.slide_out_left,
    popEnter: Int = R.anim.slide_in_left,
    popExit: Int = R.anim.slide_out_right
) =
    supportFragmentManager.inTransactionAnimated(
        enter = enter,
        exit = exit,
        popEnter = popEnter,
        popExit = popExit
    ) {
        add(frameId, fragment, fragment.javaClass.simpleName)
    }

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, addToStack: Boolean) {
    supportFragmentManager.inTransaction {
        if (addToStack) add(frameId, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
        else add(frameId, fragment)
    }
}

fun AppCompatActivity.addFragmentAnimated(
    fragment: Fragment,
    frameId: Int,
    addToStack: Boolean,
    enter: Int = R.anim.slide_in_right,
    exit: Int = R.anim.slide_out_left,
    popEnter: Int = R.anim.slide_in_left,
    popExit: Int = R.anim.slide_out_right
) {
    supportFragmentManager.inTransactionAnimated(
        enter = enter,
        exit = exit,
        popEnter = popEnter,
        popExit = popExit
    ) {
        if (addToStack) add(frameId, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
        else add(frameId, fragment)
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) =
    supportFragmentManager.inTransaction {
        replace(frameId, fragment, fragment.javaClass.simpleName)
    }

fun AppCompatActivity.replaceFragmentAnimated(
    fragment: Fragment,
    frameId: Int,
    enter: Int = R.anim.slide_in_right,
    exit: Int = R.anim.slide_out_left,
    popEnter: Int = R.anim.slide_in_left,
    popExit: Int = R.anim.slide_out_right
) =
    supportFragmentManager.inTransactionAnimated(
        enter = enter,
        exit = exit,
        popEnter = popEnter,
        popExit = popExit
    ) {
        replace(
            frameId, fragment, fragment.javaClass.simpleName
        )
    }

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, addToStack: Boolean) {
    supportFragmentManager.inTransaction {
        if (addToStack) replace(frameId, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
        else
            replace(frameId, fragment, fragment.javaClass.simpleName)
    }
}

fun AppCompatActivity.replaceFragmentAnimated(
    fragment: Fragment,
    frameId: Int,
    addToStack: Boolean,
    enter: Int = R.anim.slide_in_right,
    exit: Int = R.anim.slide_out_left,
    popEnter: Int = R.anim.slide_in_left,
    popExit: Int = R.anim.slide_out_right
) {
    supportFragmentManager.inTransactionAnimated(
        enter = enter,
        exit = exit,
        popEnter = popEnter,
        popExit = popExit
    ) {
        if (addToStack) replace(frameId, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
        else
            replace(frameId, fragment, fragment.javaClass.simpleName)
    }
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    frameId: Int,
    addToStack: Boolean,
    clearBackStack: Boolean
) {
    supportFragmentManager.inTransaction {

        if (clearBackStack && supportFragmentManager.backStackEntryCount > 0) {
            val first = supportFragmentManager.getBackStackEntryAt(0)
            supportFragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        if (addToStack) replace(frameId, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
        else
            replace(frameId, fragment, fragment.javaClass.simpleName)
    }
}

fun AppCompatActivity.replaceFragmentAnimated(
    fragment: Fragment,
    frameId: Int,
    addToStack: Boolean,
    clearBackStack: Boolean,
    enter: Int = R.anim.slide_in_right,
    exit: Int = R.anim.slide_out_left,
    popEnter: Int = R.anim.slide_in_left,
    popExit: Int = R.anim.slide_out_right
) {
    supportFragmentManager.inTransactionAnimated(
        enter = enter,
        exit = exit,
        popEnter = popEnter,
        popExit = popExit
    ) {

        if (clearBackStack && supportFragmentManager.backStackEntryCount > 0) {
            val first = supportFragmentManager.getBackStackEntryAt(0)
            supportFragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        if (addToStack) replace(frameId, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
        else
            replace(frameId, fragment, fragment.javaClass.simpleName)
    }
}


fun AppCompatActivity.getCurrentFragment(): Fragment? {
    val fragmentManager = supportFragmentManager
    var fragmentTag: String? = ""

    if (fragmentManager.backStackEntryCount > 0)
        fragmentTag =
            fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name

    return fragmentManager.findFragmentByTag(fragmentTag)
}


/**
 * Launch Activity
 *
 * Simple Activities
 * launchActivity<UserDetailActivity>()
 *
 * Add Intent extras
 * launchActivity<UserDetailActivity> {
 * putExtra(INTENT_USER_ID, user.id)
 * }
 *
 * Add custom flags
 * launchActivity<UserDetailActivity> {
 * putExtra(INTENT_USER_ID, user.id)
 * addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
 * }
 *
 * Add Shared Transitions
 * val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, avatar, "avatar")
 * launchActivity<UserDetailActivity>(options = options) {
 * putExtra(INTENT_USER_ID, user.id)
 * }
 *
 * Add requestCode for startActivityForResult() call
 * launchActivity<UserDetailActivity>(requestCode = 1234) {
 * putExtra(INTENT_USER_ID, user.id)
 * }
 */
inline fun <reified T : Any> AppCompatActivity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivityForResult(intent, requestCode, options)
}

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent, options)
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

inline fun <reified T> Activity.getExtra(extra: String): T? {
    return intent.extras?.get(extra) as? T?
}

/**
 * Show Toast
 */
fun AppCompatActivity.shortToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun AppCompatActivity.longToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

/**
 * Apply FullScreen
 */
fun AppCompatActivity.enableFullScreen() {
    window.decorView.systemUiVisibility = FLAGS_FULLSCREEN
}

/**
 * Hide keyboard func
 */
fun AppCompatActivity.hideKeyboard() {
    val imm: InputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = this.currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * Use this to dismiss keyboards on a view clicked
 */
fun AppCompatActivity.hideKeyboardOnViewClicked(vararg view: View) =
    view.forEach {
        it.setOnClickListener { this.hideKeyboard() }
    }