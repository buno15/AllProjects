package com.bunooboi.composesample.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun BottomMenuBar(visible: Boolean, modifier: Modifier) {
    AnimatedVisibility(modifier = modifier, visible = visible, enter = slideInVertically(initialOffsetY = { it }), exit = slideOutVertically(targetOffsetY = { it })) {

        Box(modifier = Modifier.shadow(elevation = 70.dp)) {
            BottomAppBar(modifier = Modifier
                .height(90.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(30.dp)), backgroundColor = Color.White, contentPadding = PaddingValues(0.dp)) {
                BottomMenuNavigation()
            }
        }
    }
}

@Composable
fun BottomMenuNavigation() {
    val menuItems = listOf(BottomItem.Menu, BottomItem.Settings)

    BottomNavigation(modifier = Modifier.height(100.dp), backgroundColor = Color.White) {
        menuItems.forEach { item ->
            BottomMenuNavigationItem(item)
        }
    }
}

@Composable
fun RowScope.BottomMenuNavigationItem(item: BottomItem) {
    BottomNavigationItem(selected = true, label = { Text(text = item.label) }, icon = { Icon(painter = painterResource(id = item.icon), contentDescription = null) }, onClick = { /*TODO*/ })
}

@Composable
fun BottomMenuFab() {
    FloatingActionButton(onClick = { /*TODO*/ }) {

    }
}

@Preview
@Composable
fun BottomMenuBarPreview() {
}