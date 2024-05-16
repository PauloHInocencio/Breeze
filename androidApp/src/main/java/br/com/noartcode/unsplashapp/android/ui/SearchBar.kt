package br.com.noartcode.unsplashapp.android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    value:String,
    hint: String,
    modifier: Modifier = Modifier,
    isEnabled:Boolean = true,
    height: Dp = 45.dp,
    elevation: Dp = 3.dp,
    cornerShape: Shape = RoundedCornerShape(8.dp),
    backgroundColor:Color = Color.White,
    onSearchClicked: () -> Unit = {},
    onTextChange: (String) -> Unit = {}
) {

    Row(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .shadow(elevation = elevation, shape = cornerShape)
            .background(color = backgroundColor, shape = cornerShape)
            .clickable { onSearchClicked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            value = value,
            onValueChange = { onTextChange(it) },
            enabled = isEnabled,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            decorationBox = { innerTextField ->
                if(value.isEmpty()){
                    Text(
                        text = hint,
                        color = Color.Gray.copy(alpha = 0.5f),
                        fontSize = 16.sp
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
            singleLine = true
            
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .size(45.dp)
                .background(color = Color.Transparent, shape = CircleShape)
                .clickable {
                    if (value.isNotEmpty()) {
                        onTextChange("")
                    }
                }
        ) {
            if (value.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                    ,
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            } else {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary

                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchBar_Preview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(10.dp)
        ,
    ) {
        var text by remember { mutableStateOf("") }
        SearchBar(
            value = text,
            hint = "type something",
            onTextChange = {value -> text = value }
        )
    }
}