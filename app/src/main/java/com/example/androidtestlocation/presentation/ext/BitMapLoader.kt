package com.example.androidtestlocation.presentation.ext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun loadBitmapFromUri(uri: Uri , context: Context): Bitmap? {
    return withContext(Dispatchers.IO) {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(uri)
            .build()

        val result = (imageLoader.execute(request) as SuccessResult).drawable
        (result as? BitmapDrawable)?.bitmap
    }
}