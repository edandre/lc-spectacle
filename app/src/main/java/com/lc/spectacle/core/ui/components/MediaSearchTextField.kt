package com.lc.spectacle.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lc.spectacle.R

val Labels = hashMapOf(
    Pair("MOVIES", R.string.movies_search_text_field_label),
    Pair("MUSIC", R.string.music_search_text_field_label)
)

@Preview
@Composable
fun MediaSearchTextField(
    mediaType: String = "MOVIES",
    onSearchTriggered: (query: String) -> Unit = {}
) {
    val mediaSearchState = remember { mutableStateOf(TextFieldValue()) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
            .alpha(0.65f),
        value = mediaSearchState.value,
        onValueChange = {
        },
        label = {
            Text(
                text = stringResource(Labels[mediaType]!!),
                modifier = Modifier.padding(bottom = 5.dp),
                style = TextStyle(
                    color = Color.LightGray
                )
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.LightGray,
            focusedLabelColor = Color.LightGray,
            cursorColor = MaterialTheme.colors.secondaryVariant
        ),
        maxLines = 1,
        shape = RoundedCornerShape(24.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = true,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchTriggered(mediaSearchState.value.text)
            }
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    )
}