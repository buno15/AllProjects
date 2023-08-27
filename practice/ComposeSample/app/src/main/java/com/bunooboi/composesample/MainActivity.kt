package com.bunooboi.composesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bunooboi.composesample.ui.component.BottomMenuBar
import com.bunooboi.composesample.ui.theme.ComposeSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: AppViewModel = viewModel()

            ComposeSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface() {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    val scrollState = rememberLazyListState()
    var isBottomAppBarVisible by remember { mutableStateOf(true) }

    // スクロールの動きを検知する
    val nestedScrollConnection = rememberUpdatedState(isBottomAppBarVisible).let { isVisible ->
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // 上にスクロールした場合
                if (available.y > 0) {
                    isBottomAppBarVisible = true
                }
                // 下にスクロールした場合
                else if (available.y < 0) {
                    isBottomAppBarVisible = false
                }
                return super.onPreScroll(available, source)
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), content = { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)) {
            LazyColumn(modifier = Modifier.padding(padding), state = scrollState) {
                items(1000) {
                    Text(text = "$it", modifier = Modifier.fillMaxWidth())
                }
            }
            BottomMenuBar(isBottomAppBarVisible, Modifier.align(Alignment.BottomCenter))
        }
    })
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ComposeSampleTheme {
        MainScreen()
    }
}