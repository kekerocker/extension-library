package com.dsoft.extensions

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

fun Context.getStatusBarHeightInPx(): Int {
    var result = 0
    val resourceId = resources
        .getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

fun Fragment.navigate(directions: NavDirections) {
    val controller = findNavController()
    val currentDestination = (controller.currentDestination as? FragmentNavigator.Destination)?.className
        ?: (controller.currentDestination as? DialogFragmentNavigator.Destination)?.className
    if (currentDestination == this.javaClass.name) {
        controller.navigate(directions)
    }
}

inline fun <T> Fragment.observeResultLiveData(key: String, crossinline observer: (T) -> Unit): MutableLiveData<T>? {
    val liveData = findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
    liveData?.observe(viewLifecycleOwner) {
        observer(it)
    }
    return liveData
}

fun <T> Fragment.setResultLiveData(key: String, value: T?) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, value)
}