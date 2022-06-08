package com.dsoft.extensions

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

fun Number.dpToPx() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics
).roundToInt()