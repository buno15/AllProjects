package com.bunooboi.stadice

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bunooboi.stadice.ui.theme.Body
import com.bunooboi.stadice.ui.theme.Green200
import com.bunooboi.stadice.ui.theme.Green300

@Composable
fun AddScreen(navController: NavHostController, viewModel: AppViewModel, context: Context) {
    Scaffold(
        modifier = Modifier.background(color = Body), scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Open)), topBar = { BackTopBar(navController, "タスクを追加") },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                AddBodyContent(viewModel, context)
            }
        },
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddBodyContent(viewModel: AppViewModel, context: Context) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Body)) {
        var name by remember { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), contentAlignment = Alignment.Center) {
            OutlinedTextField(value = name, onValueChange = {
                name = it
            }, label = { Text(text = "タスク名") }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp), singleLine = true, colors = TextFieldDefaults.outlinedTextFieldColors(backgroundColor = Color.White, textColor = Color.Black, focusedBorderColor = Green300, focusedLabelColor = Green300, unfocusedBorderColor = Green200, unfocusedLabelColor = Green200, cursorColor = Green300), keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done), keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }))
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), contentAlignment = Alignment.Center) {
            Button(onClick = {
                if (name == "" || name == " ") {
                    Toast.makeText(context, "タスク名を入力してください", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.insertTask(name)
                    Toast.makeText(context, "タスクを追加しました", Toast.LENGTH_SHORT).show()
                    keyboardController?.hide()
                    name = ""
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 30.dp), colors = ButtonDefaults.textButtonColors(backgroundColor = Green300, contentColor = Color.White)) {
                Text(text = "追加", fontSize = 18.sp)
            }
        }
    }
}

