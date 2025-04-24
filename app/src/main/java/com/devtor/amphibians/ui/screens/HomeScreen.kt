package com.devtor.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.devtor.amphibians.R
import com.devtor.amphibians.network.dto.AmphibiansItem

@Composable
fun HomeScreen(modifier: Modifier = Modifier, amphibiansUiState: AmphibiansUiState) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibiansUiState.Success -> AmphibiansGridScreen(amphibians = amphibiansUiState.amphibians, modifier = modifier)
        is AmphibiansUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ErrorScreen(modifier: Modifier=Modifier) {
    Image(
        painter = painterResource(R.drawable.ic_connection_error),
        contentDescription = null,
        modifier = modifier.size(200.dp)
    )
}

@Composable
fun AmphibiansGridScreen(amphibians: List<AmphibiansItem>, modifier: Modifier=Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        columns = GridCells.Adaptive(300.dp)
    ) {
        items(

            items = amphibians,
            key = {amphibian -> amphibian.name}
        ) {
            AmphibiansCard(amphibian = it, modifier = modifier.padding(12.dp))
        }
    }
}

@Composable
fun AmphibiansCard(amphibian: AmphibiansItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column (
            modifier = modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ){
            Text(
                modifier=Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                text = amphibian.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .aspectRatio(1.5f),
                model = amphibian.imgSrc,
                contentDescription = amphibian.type,
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img)
            )
            Text(
                modifier=Modifier.fillMaxWidth(),
                text = amphibian.type,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start
            )
            Text(
                modifier=Modifier.fillMaxWidth().padding(8.dp),
                text = amphibian.description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center)

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AmphibiansCardPreview() {
    MaterialTheme {
        AmphibiansCard(
            AmphibiansItem(
                "Description",
                "Type",
                "Name",
                "imgSrc")
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier= Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = null,
        modifier = modifier.size(200.dp)
    )
}