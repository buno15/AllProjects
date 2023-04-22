package com.bunooboi.ghostwriter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bunooboi.ghostwriter.ui.theme.GhostWriterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GhostWriterTheme {
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
        TopAppBar(title = { Text(text = "GhostWriter") }, elevation = 4.dp, navigationIcon = {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
        })
        Title(modifier = Modifier.weight(0.5f))
        SorryRow(modifier = Modifier
            .weight(1f)
            .background(Color(0xFF6E9FD1)), title = "Who", arrayOf(R.drawable.boss, R.drawable.colleague, R.drawable.family), arrayOf("上司", "同僚", "家族"))
        SorryRow(modifier = Modifier
            .weight(1f)
            .background(Color(0xFF176CB6)), title = "Level", arrayOf(R.drawable.serious, R.drawable.normal, R.drawable.easy), arrayOf("真剣", "普通", "気軽"))
        SorryRow(modifier = Modifier
            .weight(1f)
            .background(Color(0xFF11449D)), title = "Why", arrayOf(R.drawable.late, R.drawable.vacation, R.drawable.miss), arrayOf("遅刻", "休暇", "ミス"))
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
fun SorryRow(modifier: Modifier, title: String, imgs: Array<Int>, label: Array<String>) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = title, modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(0.dp, 10.dp, 0.dp, 0.dp), fontSize = 18.sp, textAlign = TextAlign.Center, color = Color.White)
        ImageRow(modifier = Modifier.weight(4f), imgs)
        Row(modifier = Modifier.weight(1f)) {
            Text(text = label[0], modifier = Modifier
                .weight(1f)
                .padding(0.dp, 0.dp, 0.dp, 0.dp), textAlign = TextAlign.Center, fontSize = 15.sp, color = Color.White)
            Text(text = label[1], modifier = Modifier
                .weight(1f)
                .padding(0.dp, 0.dp, 0.dp, 0.dp), textAlign = TextAlign.Center, fontSize = 15.sp, color = Color.White)
            Text(text = label[2], modifier = Modifier
                .weight(1f)
                .padding(0.dp, 0.dp, 0.dp, 0.dp), textAlign = TextAlign.Center, fontSize = 15.sp, color = Color.White)
        }
    }
}

@Composable
fun ImageRow(modifier: Modifier, imgs: Array<Int>) {
    Row(modifier = modifier.fillMaxSize()) {
        CircleButton(modifier = Modifier.weight(1f), imgs[0])
        CircleButton(modifier = Modifier.weight(1f), imgs[1])
        CircleButton(modifier = Modifier.weight(1f), imgs[2])
    }
}

@Composable
fun CircleButton(modifier: Modifier, img: Int) {
    Surface(shape = CircleShape, modifier = modifier.padding(15.dp)) {
        Box(modifier = Modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable { }) {
            Image(painter = painterResource(id = img), contentDescription = null, modifier = Modifier
                .background(Color.White)
                .clip(CircleShape)
                .padding(25.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GhostWriterTheme {
        AppScreen()
    }
}