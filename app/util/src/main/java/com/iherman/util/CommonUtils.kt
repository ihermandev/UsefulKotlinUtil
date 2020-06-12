package com.iherman.util

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*

/**
 * Created by Illia Herman on 12.06.2020.
 */

/**
 * Return device model
 */
fun getDeviceModel(): String = (Build.MANUFACTURER
        + " " + Build.MODEL + " " + Build.VERSION.RELEASE
        + " " + Build.VERSION_CODES::class.java.fields[Build.VERSION.SDK_INT]
    .name)

/**
 * Make blurred screenshot of a view
 */
fun prepareBlurredScreenshot(context: Context, image: Bitmap, blurRadius: Float = 15f): Bitmap {
    val outputBitmap = Bitmap.createBitmap(image)
    val renderScript = RenderScript.create(context)
    val tmpIn = Allocation.createFromBitmap(renderScript, image)
    val tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap)

    //Intrinsic Gausian blur filter
    val theIntrinsic =
        ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))

    theIntrinsic.apply {
        setRadius(blurRadius)
        setInput(tmpIn)
        forEach(tmpOut)
    }
    tmpOut.copyTo(outputBitmap)

    return outputBitmap
}

