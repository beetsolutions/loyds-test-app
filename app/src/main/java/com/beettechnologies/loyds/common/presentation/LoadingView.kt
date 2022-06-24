package com.beettechnologies.loyds.common.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun LoadingView() {
    Box(Modifier.height(30.dp).width(30.dp)) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.White)
    }
}