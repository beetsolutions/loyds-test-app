package com.beettechnologies.loyds.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beettechnologies.loyds.app.navigation.Navigation
import com.beettechnologies.loyds.app.navigation.NavigationPreviewParameterProvider
import com.beettechnologies.loyds.app.theme.Purple700
import com.beettechnologies.loyds.common.presentation.LogoView

@Composable
@Preview
fun HomeView(@PreviewParameter(NavigationPreviewParameterProvider::class) navigation: Navigation) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple700)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 16.dp)
                ) {
                    Text(
                        text = "Welcome home!",
                        color = Color.White,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            item {
                LogoView()
            }
        }
    }
}