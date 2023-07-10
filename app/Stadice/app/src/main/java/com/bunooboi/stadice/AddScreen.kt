package com.bunooboi.stadice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bunooboi.stadice.ui.theme.Body
import com.bunooboi.stadice.ui.theme.Green200
import com.bunooboi.stadice.ui.theme.Green300

@Composable
fun AddScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier.background(color = Body), scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Open)), topBar = { BackTopBar(navController) },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                AddBodyContent()
            }
        },
    )
}

@Composable
fun AddBodyContent() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Body)) {
        var text by remember { mutableStateOf("") }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), contentAlignment = Alignment.Center) {
            OutlinedTextField(value = text, onValueChange = {
                text = it
            }, label = { Text(text = "タスク") }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp), singleLine = true, colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.White, textColor = Color.Black, focusedBorderColor = Green300, focusedLabelColor = Green300, unfocusedBorderColor = Green200, unfocusedLabelColor = Green200, cursorColor = Green300))
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), contentAlignment = Alignment.Center) {
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 30.dp), colors = ButtonDefaults.textButtonColors(backgroundColor = Green300, contentColor = Color.White)) {
                Text(text = "追加", fontSize = 18.sp)
            }
        }
    }
}

