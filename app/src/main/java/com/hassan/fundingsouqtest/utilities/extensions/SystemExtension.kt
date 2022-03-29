package com.hassan.fundingsouqtest.utilities.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

fun <T> LiveData<T>.observeNTimes(
    noOfObservations: Int, lifecycleOwner: LifecycleOwner, observer: Observer<T>
) {
    observe(lifecycleOwner, object : Observer<T> {
        var observations = 0
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            observations++
            if (observations >= noOfObservations) {
                removeObserver(this)
            }
        }
    })
}