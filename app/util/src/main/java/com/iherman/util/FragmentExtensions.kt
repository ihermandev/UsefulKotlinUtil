package com.iherman.util

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by Illia Herman on 11.06.2020.
 */

/**
 * Show Toast
 */
fun Fragment.shortToast(message: String) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun Fragment.longToast(message: String) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()