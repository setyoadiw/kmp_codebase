package com.setyo.mycmpapplication.news.presentation.page.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewsPage(
    viewModel: NewsViewModel = koinViewModel <NewsViewModel>()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    NewsContent(
        state = state,
        onAction = viewModel::onAction,
    )
}

@Composable
fun NewsContent(
    state: NewsState,
    onAction: (NewsAction) -> Unit
) {
    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .consumeWindowInsets(innerPadding)
        ) {

            Surface(
                shadowElevation = 22.dp,
                tonalElevation = 4.dp,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Magenta)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
//                        .padding(
//                            top = WindowInsets.statusBars
//                                .asPaddingValues()
//                                .calculateTopPadding()
//                        )
                            .height(55.dp)
                            .background(Color(0xFF1950AC)),
                    ) {
                        IconButton(
                            onClick = { onAction(NewsAction.OnBackClick) },
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(36.dp)
                                .align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .height(18.dp)
                            )
                        }
                        Text(
                            text = "News",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE9EBF8),
                        shape = RoundedCornerShape(14.dp)
                    ),
            ) {
                items(
                    items = state.articles,
                ) { item ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .width(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            model = item.urlToImage,
                            contentDescription = null,
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = item.author ?: "No Autor",
                                style = MaterialTheme.typography.titleSmall,
                                color = Color(0xFF212121),
                            )
                            Text(
                                text = item.title ?: "No Title",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF212121),
                            )
                        }
                    }

                }
            }
        }
    }
}

