package com.xanthenite.isining.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegate utility for [Fragment] which keeps track of the lifecycle and clears the value once
 * Lifecycle is destroyed
 */
class AutoCleanedValue<T : Any>(
        fragment: Fragment,
        private val initializer: (() -> T)?,
        beforeCleaning: (T?) -> Unit
                               ) : ReadWriteProperty<Fragment , T>
{

    private var _value: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver
                                       {
            val viewLifecycleOwnerObserver = Observer<LifecycleOwner?> { viewLifecycleOwner ->

                viewLifecycleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver
                                                           {
                    override fun onDestroy(owner: LifecycleOwner) {
                        beforeCleaning(_value)
                        _value = null
                    }
                })
            }

            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerObserver)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerObserver)
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val value = _value

        if (value != null) {
            return value
        }
        val currentLifecycleState = thisRef.viewLifecycleOwner.lifecycle.currentState
        if (currentLifecycleState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            return initializer?.invoke().also { _value = it }
                   ?: error("The value has not yet been set or no default initializer provided")
        } else {
            error("Fragment might have been destroyed or not initialized yet")
        }
    }

    override fun setValue(thisRef: Fragment , property: KProperty<*> , value: T) {
        _value = value
    }
}

/**
 * Delegate for auto cleaning up value when the lifecycle of this Fragment is destroyed
 */
fun <T : Any> Fragment.autoCleaned(
        initializer: (() -> T)? = null,
        beforeCleaning: (T?) -> Unit = {}
                                  ): AutoCleanedValue<T> {
    return AutoCleanedValue(this, initializer, beforeCleaning)
}
