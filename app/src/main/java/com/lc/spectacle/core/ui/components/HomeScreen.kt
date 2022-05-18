package com.lc.spectacle.core.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lc.spectacle.R
import com.lc.spectacle.core.ui.components.FeatureSelectionPanel
import com.lc.spectacle.core.ui.navigation.Screen
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen (
    navController: NavController
){
    Scaffold(
        backgroundColor = MaterialTheme.colors.primary,
        topBar = {
            TopAppBar (
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 0.dp,
                title = {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
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
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
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
                        .align(
                            Alignment.Center
                        )
                        .padding(bottom = 36.dp)
                ) {
                    FeatureSelectionPanel(
                        gradientColors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondaryVariant),
                        feature = "MUSIC",
                        onClick = {
                            navController.navigate(Screen.MoviesScreen.route)
                        }
                    )
                    FeatureSelectionPanel(
                        gradientColors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.primaryVariant),
                        feature = "MOVIES"
                    )
                }
            }
        }
    )
}