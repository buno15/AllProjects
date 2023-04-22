package com.example.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen()
        }
    }
}

data class Animal(val text: String, val img: Int)

class SampleViewModel : ViewModel() {
    val mcount = MutableLiveData(0)
    val count: LiveData<Int> get() = mcount

    fun countUp() {
        val c = mcount.value ?: 0
        mcount.value = c + 1
    }
}

@Composable
fun AppScreen() {
    ComposeAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Column(verticalArrangement = Arrangement.spacedBy(30.dp), horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(30.dp)) {
                Button(onClick = {}) {
                    Text(text = "Button", fontSize = 20.sp)
                }
                FloatingActionButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                }
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)) {
                    Text(text = "Text on Card", fontSize = 30.sp, textAlign = TextAlign.Center)
                }
                Text(text = "Text on background", fontSize = 30.sp)
            }
        }
    }
}

data class Fruits(val name: String, val imageResource: Int)

val fruits = listOf(
    Fruits("Apple", R.drawable.icon),
    Fruits("Orange", R.drawable.icon),
    Fruits("Grape", R.drawable.icon),
    Fruits("Peach", R.drawable.icon),
    Fruits("Strawberry", R.drawable.icon),
)

@Composable
fun List1(fruits: List<Fruits>, onClickItem: (Int) -> Unit = {}) {
    ComposeAppTheme {
        LazyColumn(modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp), contentPadding = PaddingValues(5.dp)) {
            itemsIndexed(fruits) { index, fruit ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(5.dp))
                    .clickable { onClickItem(index) }) {
                    Image(painter = painterResource(id = fruit.imageResource), contentDescription = null, modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .size(50.dp))
                    Text(text = "$index: ${fruit.name}", fontSize = 18.sp)
                }
            }
        }
    }
}

@Composable
fun AnimalComposable(resourceId: Int, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painter = painterResource(id = resourceId), contentDescription = null, modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f), contentScale = ContentScale.Crop)
        Text(
            text = text,
            fontSize = 20.sp,
        )
    }
}

@Composable
fun QuestionComposable(onClick: () -> Unit = {}) {
    Button(onClick = onClick, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)) {
        Text(text = "Yes")
    }
}

@Composable
fun ResponseComposable() {
    Text(text = "Thank you!!!", fontSize = 25.sp)
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