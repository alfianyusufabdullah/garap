package alfianyusufabdullah.garap

import android.content.Context
import android.util.DisplayMetrics

fun Int.toPx(context: Context) = Math.round(this * (context.resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))