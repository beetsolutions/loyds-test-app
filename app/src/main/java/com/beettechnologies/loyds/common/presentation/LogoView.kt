package com.beettechnologies.loyds.common.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun LogoView() {
    Box(
        modifier = Modifier.height(200.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Test",
            fontSize = 68.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center).padding(top = 40.dp, bottom = 20.dp)
        )
    }
}