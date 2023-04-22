package com.example.imsorry

import android.graphics.fonts.FontStyle
import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imsorry.ui.theme.ImSorryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImSorryTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    AppScreen()
                }
            }
        }
    }
}

@Composable
fun AppScreen() {
    Column {
        TopAppBar(title = { Text(text = "ImSorry") }, elevation = 4.dp, navigationIcon = {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
        })
        Title(modifier = Modifier.weight(0.5f))
        SorryRow(modifier = Modifier
            .weight(1f)
            .background(Color(0xFF6E9FD1)), title = "Who")
        SorryRow(modifier = Modifier
            .weight(1f)
            .background(Color(0xFF176CB6)), title = "Level")
        SorryRow(modifier = Modifier
            .weight(1f)
            .background(Color(0xFF11449D)), title = "Why")
    }
}

@Composable
fun Title(modifier: Modifier) {
    Row(modifier = modifier
        .fillMaxSize()
        .background(Color(0xFF88BEEA))) {
        Text(text = "謝罪文自動生成機\nあなたの代わりに作文します。", modifier = Modifier
            .weight(1.5f)
            .align(Alignment.CenterVertically)
            .padding(10.dp, 0.dp, 0.dp, 0.dp), fontWeight = FontWeight.Bold, textAlign = TextAlign.Left, color = Color(0xFF0C1F6B))
        Image(painter = painterResource(id = R.drawable.title), contentDescription = null, modifier = Modifier
            .weight(1f)
            .padding(10.dp, 20.dp))
    }
}

@Composable
fun SorryRow(modifier: Modifier, title: String) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = title, modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(0.dp, 10.dp, 0.dp, 0.dp), fontSize = 20.sp, textAlign = TextAlign.Center)
        ImageRow(modifier = Modifier.weight(4f))
    }
}

@Composable
fun ImageRow(modifier: Modifier) {
    Row(modifier = modifier.fillMaxSize()) {
        CircleButton(modifier = Modifier.weight(1f))
        CircleButton(modifier = Modifier.weight(1f))
        CircleButton(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CircleButton(modifier: Modifier) {
    Surface(shape = CircleShape, modifier = modifier.padding(15.dp)) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null, modifier = Modifier
            .aspectRatio(1f)
            .background(Color.White)
            .clip(CircleShape)
            .clickable { })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImSorryTheme {
        AppScreen()
    }
}