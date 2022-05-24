package com.lc.spectacle.features.movie_library.presentation.movies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lc.spectacle.features.movie_library.domain.model.MovieTileModel

@Composable
fun MovieTile(
    data: MovieTileModel,
    onFavoriteToggle: (value: Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(start = 24.dp, bottom = 16.dp, end = 16.dp)
            .graphicsLayer(alpha = 0.90f)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.White),
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = data.movie.posterUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
            )
            Spacer(
                modifier = Modifier.width(8.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    data.movie.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Thin
                    )
                )
                Text(
                    data.movie.overview,
                    style = TextStyle(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Row (
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 16.dp)
                        .fillMaxWidth()
                    )
                {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Rating",
                        tint = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 4.dp),
                        text = "${data.movie.voteAverage} (${data.movie.voteCount})"
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            data.isFavorite = !data.isFavorite
                            onFavoriteToggle(data.isFavorite)
                        },
                        content = {
                            Icon(
                                modifier = Modifier
                                    .size(72.dp),
                                imageVector = if (data.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = null,
                                tint = MaterialTheme.colors.primaryVariant,
                            )
                        }
                    )
                }
            }
        }
    }
}