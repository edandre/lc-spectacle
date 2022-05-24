package com.lc.spectacle.features.movie_library.presentation.movies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lc.spectacle.R
import com.lc.spectacle.core.ui.components.MediaSearchTextField
import java.util.*

@Preview
@Composable
fun MoviesScreen(
    //navController: NavController
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        topBar = {
            TopAppBar (
                navigationIcon = {
                    IconButton(onClick = { /*navController.navigateUp()*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 0.dp,
                title = {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(end = 56.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.app_name).uppercase(Locale.ROOT),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                },
            )
        },
        content = { padding ->
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(padding)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.primary,
                            MaterialTheme.colors.secondary
                        )
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    MediaSearchTextField(
                        mediaType = "MOVIES",
                        onSearchTriggered = { query ->
                            //TODO: Call Movies API
                        }
                    )
                    Spacer(
                        modifier = Modifier
                            .height(24.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 24.dp),
                        text = stringResource(id = R.string.my_movies_label),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                    Text(
                        modifier = Modifier.padding(start = 25.dp, top = 8.dp),
                        text = "Clássicos",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    )
                    AddMoviePanel()
                }
            }
        }
    )
}

@Composable fun AddMoviePanel() {
    Button(
        onClick = {},
        modifier = Modifier
            .height(150.dp)
            .width(140.dp)
            .padding(start = 24.dp, top = 16.dp, end = 16.dp)
            .graphicsLayer(alpha = 0.90f)
            .clip(RoundedCornerShape(8.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.Outlined.AddCircleOutline,
                "",
                tint = Color.DarkGray
            )
            Text(
                text = "Adicionar",
                style = TextStyle(
                    color = Color.DarkGray
                )
            )
        }
    }
}