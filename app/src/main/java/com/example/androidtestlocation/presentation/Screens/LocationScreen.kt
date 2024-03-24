package com.example.androidtestlocation.presentation.Screens


import androidx.lifecycle.viewmodel.compose.viewModel
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androidtestlocation.R
import com.example.androidtestlocation.presentation.viewModels.LocationItemViewModel
import com.example.androidtestlocation.presentation.viewModels.LocationViewModel
import org.w3c.dom.Text

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationScreen(viewModel: LocationViewModel = viewModel(), modifier: Modifier = Modifier){

    val state = viewModel.state.collectAsState()
    val locations = viewModel.locations.collectAsState()
    val focusManager = LocalFocusManager.current
    val pagerState = rememberPagerState(pageCount = {
        locations.value.size
    })

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)){
            Image(painterResource(id = R.drawable.group_303), contentDescription = "", modifier = Modifier.align(
                Alignment.Center) )
            Text(text = "Локации", fontSize = 40.sp , modifier = Modifier.align(Alignment.Center))
        }
        Card(elevation = CardDefaults.cardElevation(defaultElevation = 12.dp), modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp) , colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Card(modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(16.dp), shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEDF3F4))) {
                    TextField(value = state.value, onValueChange = {viewModel.updateChapter(it)} , modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                        singleLine = true,
                        textStyle = TextStyle(textAlign = TextAlign.Center , fontSize = 30.sp),
                        placeholder = {
                            Text(
                                text = "введите название",
                                textAlign = TextAlign.Center
                            )
                        },colors = TextFieldDefaults.colors(unfocusedIndicatorColor = Color.Transparent ,
                            unfocusedContainerColor = Color.Transparent),
                        keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()
                        viewModel.changeChapterName()
                        }))
            }
        }


        VerticalPager(state = pagerState, reverseLayout = true ,  modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) { page ->
            val itemViewModel  = hiltViewModel<LocationItemViewModel>()
            itemViewModel.init(locations.value[page])
            LocationItem(itemViewModel,locations.value[page], focusManager = focusManager)
        }



    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLocationScreen(){
    LocationScreen()
}
