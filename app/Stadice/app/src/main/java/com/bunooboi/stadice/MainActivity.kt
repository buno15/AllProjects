package com.bunooboi.stadice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bunooboi.stadice.ui.theme.*
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            StadiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(navController)
                        }
                        composable("add") {
                            AddScreen(navController)
                        }
                        composable("history") {
                            HistoryScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(modifier = Modifier.background(color = Body), scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Open)), topBar = { DeleteTopBar(navController) }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            MainBodyContent()
        }
    }, bottomBar = { BottomBar(navController) }, floatingActionButtonPosition = FabPosition.Center, isFloatingActionButtonDocked = true, floatingActionButton = { BottomFAB() })
}

@Composable
fun MainBodyContent() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Body)) {
        var selectedIndex by remember { mutableStateOf(-1) }

        val viewModel: AppViewModel = viewModel()
        val tasks = viewModel.tasks.observeAsState(mutableListOf()).value

        TaskList(tasks = tasks) { selectedIndex = it }

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
fun TaskList(tasks: MutableList<Task>, onClickItem: (Int) -> Unit = {}) {
    LazyColumn(modifier = Modifier
        .background(Body)
        .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp), contentPadding = PaddingValues(5.dp)) {
        itemsIndexed(tasks) { index, task ->
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 15.dp, end = 15.dp)
                .background(Color.White, RoundedCornerShape(10.dp))) {
                IconButton(onClick = { onClickItem(index) }, modifier = Modifier
                    .padding(10.dp)
                    .size(40.dp)
                    .weight(1f)) {
                    Icon(painter = painterResource(id = if (task.finished) {
                        R.drawable.baseline_check_circle_30
                    } else {
                        R.drawable.baseline_circle_30
                    }), modifier = Modifier.size(35.dp), contentDescription = null, tint = if (task.finished) {
                        Green300
                    } else {
                        Circle
                    })
                }
                Text(text = "${task.name}", fontSize = 18.sp, modifier = Modifier.weight(4f))
                IconButton(onClick = { onClickItem(index) }, modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp)
                    .weight(1f)) {
                    Icon(painter = painterResource(id = R.drawable.baseline_delete_outline_30), contentDescription = null)
                }
            }
        }
    }
}

@Composable
fun DeleteTopBar(navController: NavHostController) {
    TopAppBar(title = { Text(text = "StaDice", color = Green300, modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) }, elevation = 4.dp, backgroundColor = Green100, modifier = Modifier
        .background(Body)
        .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)), actions = {
        IconButton(onClick = { navController.navigate("add") }, modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_edit_notifications_30), contentDescription = null, tint = Green300)
        }
    })
}

@Composable
fun BackTopBar(navController: NavHostController) {
    TopAppBar(title = { Text(text = "StaDice", color = Green300, modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)) }, elevation = 4.dp, backgroundColor = Green100, modifier = Modifier
        .background(Body)
        .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)), actions = {}, navigationIcon = {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Green300)
        }
    })
}

@Composable
fun BottomBar(screenNavController: NavHostController) {
    val navController = rememberNavController()

    BottomAppBar(modifier = Modifier
        .background(Body)
        .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
        .height(65.dp), backgroundColor = Green300, cutoutShape = androidx.compose.foundation.shape.CircleShape, elevation = 22.dp) {
        BottomNavigation(navController = navController, screenNavController = screenNavController)
    }
}

@Composable
fun BottomFAB() {
    FloatingActionButton(onClick = { /*TODO*/ }, contentColor = Green300, backgroundColor = Body, modifier = Modifier
        .width(80.dp)
        .height(80.dp)) {
        Icon(painter = painterResource(id = R.drawable.baseline_casino_40), contentDescription = null, tint = Green300)
    }
}

sealed class Screen(val route: String, val label: String, val icon: Int) {
    object MailScreen : Screen("add", "追加", R.drawable.baseline_add_24)
    object ProfileScreen : Screen("history", "履歴", R.drawable.baseline_history_24)
}

val screenItem = listOf(Screen.MailScreen, Screen.ProfileScreen)

@Composable
fun BottomNavigation(navController: NavHostController, screenNavController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(modifier = Modifier
        .height(100.dp)
        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)), elevation = 0.dp, backgroundColor = Green300) {
        screenItem.forEach { screen ->
            BottomItem(screen = screen, currentDestination = currentDestination, navController = navController, screenNavController = screenNavController)
        }
    }
}

@Composable
fun RowScope.BottomItem(screen: Screen, currentDestination: NavDestination?, navController: NavHostController, screenNavController: NavHostController) {
    val pad = if (screen.route == "add") Modifier.padding(0.dp, 0.dp, 30.dp, 0.dp) else Modifier.padding(30.dp, 0.dp, 0.dp, 0.dp)

    BottomNavigationItem(modifier = pad, icon = { Icon(painter = painterResource(id = screen.icon), modifier = Modifier.size(35.dp), contentDescription = null, tint = Color.White) }, label = {
        screen.label?.let {
            Text(text = it, color = Color.White)
        }
    }, selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true, unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled), onClick = {
        if (screen.route == "add") {
            screenNavController.navigate("add")
        } else if (screen.route == "history") {
            screenNavController.navigate("history")
        }
    })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StadiceTheme {

    }
}