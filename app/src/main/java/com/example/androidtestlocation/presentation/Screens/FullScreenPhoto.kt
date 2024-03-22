package com.example.androidtestlocation.presentation.Screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.androidtestlocation.R
import android.net.Uri
import androidx.compose.ui.graphics.asImageBitmap
import coil.compose.rememberAsyncImagePainter


@Composable
fun MyPopup(
    bitmap: Bitmap ,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
) {
    Popup(
        onDismissRequest = onDismiss,
        offset = IntOffset(0, 0),
        properties = PopupProperties(
            focusable = true,
            dismissOnBackPress = true,
        ),
    ) {
        Box(
            modifier.fillMaxSize().background(
                Color(1, 1, 1, 185)
            ),
        ) {
            Image(bitmap = bitmap.asImageBitmap(), contentDescription ="" , modifier = Modifier.fillMaxWidth().align(Alignment.Center) ,)
        }
    }
}