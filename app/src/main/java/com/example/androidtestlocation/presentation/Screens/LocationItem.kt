package com.example.androidtestlocation.presentation.Screens

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.androidtestlocation.R
import com.example.androidtestlocation.domain.models.LocationModel
import com.example.androidtestlocation.presentation.ext.loadBitmapFromUri
import com.example.androidtestlocation.presentation.viewModels.LocationItemViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationItem(viewModel: LocationItemViewModel = viewModel(), location: LocationModel, focusManager: FocusManager, modifier: Modifier = Modifier) {
    viewModel.init(location)
    val state = viewModel.state.collectAsState()

    val context = LocalContext.current

    val isElementDeleted  = remember {
        mutableStateOf(false)
    }

    val showFullScreenImage = remember {
        mutableStateOf(false)
    }

    var  selectImageFullScreen by remember {
        mutableStateOf(0)
    }


    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
        CoroutineScope(Dispatchers.IO).launch {
            val bitmaps= uris.map { uri ->
                loadBitmapFromUri(uri, context)
            }.filterNotNull()




            viewModel.updateNewImage(bitmaps)

        }
    }

    if(showFullScreenImage.value){
        MyPopup(bitmap = state.value.images[selectImageFullScreen].bitmap , onDismiss = {showFullScreenImage.value = false})
    }

    val list = (1..10).map { it.toString() }
    Box(modifier = modifier.padding(top = 8.dp, bottom = 58.dp)) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = if (isElementDeleted.value) 20.dp else 0.dp),
            shape = RoundedCornerShape(30.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Card(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(16.dp), shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEDF3F4))
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(modifier = Modifier.wrapContentHeight()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            TextField(value = state.value.name, onValueChange = {viewModel.updateLocationName(it)}, modifier = Modifier
                                .wrapContentHeight()
                                .weight(1f),
                                singleLine = true,
                                textStyle = TextStyle(fontSize = 30.sp),
                                placeholder = {
                                    Text(
                                        text = "введите название",
                                        textAlign = TextAlign.Center
                                    )
                                }, colors = TextFieldDefaults.colors(
                                    unfocusedIndicatorColor = Color.Transparent,
                                    unfocusedTextColor = Color(0xFF869495),
                                    unfocusedContainerColor = Color.Transparent
                                ),
                                keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()
                                viewModel.saveLocationName()})
                            )
                            IconButton(onClick = { galleryLauncher.launch("image/*") }, modifier = Modifier.padding(end = 8.dp)) {
                                Icon(
                                    painterResource(id = R.drawable.baseline_add_circle_24),
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(
                            start = 8.dp,
                            top = 8.dp,
                            end = 8.dp,
                            bottom = 8.dp
                        ), modifier = Modifier.heightIn(max = 1000.dp)
                    ) {
                        items(state.value.images.size) { index ->
                            Card(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .combinedClickable(enabled = !isElementDeleted.value,
                                        onClick = {
                                            if (!isElementDeleted.value) {
                                                selectImageFullScreen = index
                                                showFullScreenImage.value = true
                                            }
                                        },
                                        onLongClick = {
                                            isElementDeleted.value = true
                                        }
                                    ),
                            ) {
                                Box(Modifier.fillMaxSize()) {
                                    Image(modifier= Modifier.fillMaxSize(),
                                        bitmap = state.value.images[index].bitmap.asImageBitmap(),
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop
                                    )
                                    if(isElementDeleted.value){
                                    DeleteCheckButton(changeChoose = {
                                                                     if(it){
                                                                         viewModel.addImageToDelete(state.value.images[index].id)
                                                                     }else viewModel.deleteImageToDelete(state.value.images[index].id)
                                    }, modifier = Modifier.align(
                                        Alignment.TopEnd))
                                    }
                                }

                            }
                        }
                    }

                }
            }
        }

        if(isElementDeleted.value){
        Button(onClick = { isElementDeleted.value = false
                         viewModel.deleteImages()}, modifier = Modifier
            .border(
                width = 3.dp, color = Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .align(Alignment.BottomCenter)
            .height(40.dp)
            .fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCE6666))) {
            Text(text = "Удалить")
        }}
    }
}


@Preview
@Composable
fun PreviewLocationItem(){
    val focusManager = LocalFocusManager.current
    LocationItem(location =  LocationModel(1,"", listOf()), focusManager =  focusManager)
}