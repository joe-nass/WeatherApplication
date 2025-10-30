package com.example.weatherapplication.core.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
import com.example.weatherapplication.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String?,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    enablePlaceHolder: Boolean = true
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .placeholder(if(enablePlaceHolder)R.drawable.fallback_image else 0)
            .error(R.drawable.ic_launcher_background)
            .fallback(R.drawable.fallback_image)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale
    )

}