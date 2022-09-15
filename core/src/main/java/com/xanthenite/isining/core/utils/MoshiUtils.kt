package com.xanthenite.isining.core.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

inline fun <reified T> fromJson(json: String) = moshi.adapter(T::class.java).fromJson(json)

inline val <reified T> T.json get() = moshi.adapter(T::class.java).toJson(this)