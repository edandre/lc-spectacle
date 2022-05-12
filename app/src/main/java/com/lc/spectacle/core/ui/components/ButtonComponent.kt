package com.lc.spectacle.features.auth.presentation.register.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun IdleButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
        contentPadding = PaddingValues(16.dp)
    ) {
        Text(text,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center)
    }
}

@Composable
fun LoadingButton(
    colorTest: Color, ) {
    Button(
        onClick = { },
        Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorTest),
        contentPadding = PaddingValues(5.dp)

    ) {
        CircularProgressIndicator(Modifier.size(40.dp),)
    }
}