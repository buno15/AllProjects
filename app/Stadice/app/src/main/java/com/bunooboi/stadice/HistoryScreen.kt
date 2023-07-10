package com.bunooboi.stadice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bunooboi.stadice.ui.theme.Body
import com.bunooboi.stadice.ui.theme.Green300
import java.text.SimpleDateFormat

@Composable
fun HistoryScreen(navController: NavHostController, viewModel: AppViewModel) {
    Scaffold(
        modifier = Modifier.background(color = Body), scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Open)), topBar = { BackTopBar(navController, "履歴") },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                HistoryBodyContent(viewModel)
            }
        },
    )
}

@Composable
fun HistoryBodyContent(viewModel: AppViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Body)
        .padding(bottom = 10.dp)) {
        var selectedIndex by remember { mutableStateOf(-1) }

        val tasks = viewModel.tasks.observeAsState(mutableListOf()).value

        HistoryList(tasks = tasks) { selectedIndex = it }

        if (selectedIndex >= 0) {
            AlertDialog(onDismissRequest = { selectedIndex = -1 }, confirmButton = {
                TextButton(onClick = { selectedIndex = -1 }) {
                    Text("OK")
                }
            }, text = { Text("Index $selectedIndex is clicked.") })
        }
    }
}

@Composable
fun HistoryList(tasks: List<Task>, onClickItem: (Int) -> Unit = {}) {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    LazyColumn(modifier = Modifier
        .background(Body)
        .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp), contentPadding = PaddingValues(5.dp)) {
        itemsIndexed(tasks) { index, task ->
            if (task.finished) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                    .background(Color.White, RoundedCornerShape(10.dp))) {
                    IconButton(onClick = { onClickItem(index) }, modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp)
                        .weight(1f)) {
                        Icon(painter = painterResource(id = R.drawable.baseline_check_circle_30), modifier = Modifier.size(35.dp), contentDescription = null, tint = Green300)
                    }
                    Column(modifier = Modifier.weight(5f)) {
                        Text(text = "${task.name}", fontSize = 18.sp, modifier = Modifier.padding(top = 5.dp))
                        Text(text = "${sdf.format(task.date)}", fontSize = 12.sp, modifier = Modifier.padding(vertical = 5.dp))
                    }
                }
            }
        }
    }
}

