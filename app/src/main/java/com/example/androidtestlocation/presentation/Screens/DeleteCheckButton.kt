package com.example.androidtestlocation.presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidtestlocation.R

@Composable
fun DeleteCheckButton(changeChoose: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    val isSelect = remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            isSelect.value = !isSelect.value
            changeChoose(isSelect.value)
        },
        modifier = modifier.wrapContentSize()
            .border(
                width = 3.dp,
                color = Color(0xFFCE6666),
                shape = CircleShape
            )
            .background(Color.White, shape = CircleShape),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.White,
            contentColor = Color(0xFFCE6666)
        )
    ) {
        if (isSelect.value) {
            Image(
                painter = painterResource(id = R.drawable.baseline_close_24),
                modifier = Modifier,
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color(0xFFCE6666))
            )
        }
    }
}


@Preview
@Composable
fun PreviewDeleteCheckButton(){
    DeleteCheckButton({})
}