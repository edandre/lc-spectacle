package com.lc.spectacle.core.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lc.spectacle.R

val FeatureIcons = hashMapOf(
    Pair("MUSIC", Icons.Outlined.LibraryMusic),
    Pair("MOVIES", Icons.Outlined.VideoLibrary)
)

val FeatureTexts = hashMapOf(
    Pair("MUSIC", Pair(R.string.music_label_1st, R.string.music_label_2nd)),
    Pair("MOVIES", Pair(R.string.movies_label_1st, R.string.movies_label_2nd))
)

@Preview
@Composable
fun FeatureSelectionPanel(
    gradientColors: List<Color> = listOf(Color(0xFF28D8A3), Color(0xFF00BEB2)),
    feature: String = "MOVIES",
    onClick: () -> Unit = {}
) {
    val gradient =
        Brush.horizontalGradient(gradientColors)

    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(48.dp))
            .background(color = Color.White),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.width(36.dp))
            Icon(
                imageVector = FeatureIcons[feature] ?: Icons.Filled.BrokenImage,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier
                    .size(60.dp)
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.SrcAtop)
                        }
                    }
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Text(
                    text = FeatureTexts[feature]?.let { stringResource(id = it.first) } ?: "Feature Not Found",
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp,
                        color = Color.DarkGray
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                Text(
                    text = FeatureTexts[feature]?.let { stringResource(id = it.second) } ?: "Feature Not Found",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    ),
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
            }
        }
    }
}