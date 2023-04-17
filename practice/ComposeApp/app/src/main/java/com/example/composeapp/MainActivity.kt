package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }*/
            AppScreen()
        }
    }
}

@Composable
fun AppScreen() {
    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.icon), contentDescription = "icon", modifier = Modifier
            .border(2.dp, Color.Black)
            .weight(1f), contentScale = ContentScale.Crop)
        Image(painter = painterResource(id = R.drawable.icon), contentDescription = "icon", modifier = Modifier
            .border(2.dp, Color.Black)
            .weight(2f)
            .align(Alignment.Top), contentScale = ContentScale.Crop)
        Image(painter = painterResource(id = R.drawable.icon), contentDescription = "icon", modifier = Modifier
            .border(2.dp, Color.Black)
            .weight(1f), contentScale = ContentScale.Crop)
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAppTheme {
        Greeting("Android")
    }
}