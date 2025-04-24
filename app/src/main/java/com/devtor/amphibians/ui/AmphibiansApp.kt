package com.devtor.amphibians.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devtor.amphibians.R
import com.devtor.amphibians.ui.screens.AmphibiansViewModel
import com.devtor.amphibians.ui.screens.HomeScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AmphibiansApp(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier,
        topBar = { AmphibiansAppBar() }
    ) { innerPadding ->
        val viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            HomeScreen(
                amphibiansUiState = viewModel.amphibiansUiState,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    )
}