package com.bunooboi.ghostwriter

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.disabled
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel

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
fun AppScreen(viewModel: SorryViewModel = viewModel()) {
    val who: Int by viewModel.who.observeAsState(0)
    val level: Int by viewModel.level.observeAsState(0)
    val why: Int by viewModel.why.observeAsState(0)

    Column {
        TopAppBar(title = { Text(text = "GhostWriter") }, elevation = 4.dp, navigationIcon = {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
        })
        Title(modifier = Modifier.weight(0.5f))
        SorryRow(viewModel, modifier = Modifier
            .weight(1f)
            .background(Color(0xFF6E9FD1)), title = "Who", arrayOf(R.drawable.boss, R.drawable.colleague, R.drawable.family), arrayOf("上司", "同僚", "家族"), index = 1) { cIndex -> viewModel.setWho(cIndex) }
        SorryRow(viewModel, modifier = Modifier
            .weight(1f)
            .background(Color(0xFF176CB6)), title = "Level", arrayOf(R.drawable.serious, R.drawable.normal, R.drawable.easy), arrayOf("真剣", "普通", "気軽"), index = 2) { cIndex -> viewModel.setLevel(cIndex) }
        SorryRow(viewModel, modifier = Modifier
            .weight(1f)
            .background(Color(0xFF11449D)), title = "Why", arrayOf(R.drawable.late, R.drawable.vacation, R.drawable.miss), arrayOf("遅刻", "休暇", "ミス"), index = 3) { cIndex -> viewModel.setWhy(cIndex) }
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
fun SorryRow(viewModel: SorryViewModel = viewModel(), modifier: Modifier, title: String, imgs: Array<Int>, label: Array<String>, index: Int, onBClick: (Int) -> Unit) {
    val onCClick: (Int) -> Unit = { cIndex -> onBClick(cIndex) }

    Column(modifier = modifier.fillMaxSize()) {
        Text(text = title, modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(0.dp, 10.dp, 0.dp, 0.dp), fontSize = 18.sp, textAlign = TextAlign.Center, color = Color.White)
        ImageRow(modifier = Modifier.weight(4f), imgs, index, onCClick = onCClick)
        Row(modifier = Modifier.weight(1f)) {
            for (i in 0..2) {
                Text(text = label[i], modifier = Modifier
                    .weight(1f)
                    .padding(0.dp, 0.dp, 0.dp, 0.dp), textAlign = TextAlign.Center, fontSize = 15.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun ImageRow(modifier: Modifier, imgs: Array<Int>, index: Int, onCClick: (Int) -> Unit) {
    Row(modifier = modifier.fillMaxSize()) {
        for (i in 0..2) {
            CircleButton(modifier = Modifier.weight(1f), imgs[i], index = i + 1, onCClick = onCClick)
        }
    }
}

@Composable
fun CircleButton(modifier: Modifier, img: Int, index: Int, onCClick: (Int) -> Unit) {
    Surface(shape = CircleShape, modifier = modifier.padding(15.dp)) {
        Box(modifier = Modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable(enabled = true) { onCClick(index) }) {
            Log.v("test", "$index")
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