package com.shannon.moviemvvm.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

inline fun <T> Fragment.observe(liveData: LiveData<T>, crossinline runnable: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner) {
        runnable.invoke(it)
    }
}

inline fun <T> Fragment.event(liveData: LiveData<Event<T>>, crossinline runnable: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, EventObserver {
        runnable.invoke(it)
    })
}