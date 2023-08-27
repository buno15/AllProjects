package com.bunooboi.stadice

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bunooboi.stadice.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: AppViewModel = hiltViewModel()

            setAlarm(LocalContext.current, viewModel)

            StadiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(navController, viewModel, applicationContext)
                        }
                        composable("add") {
                            AddScreen(navController, viewModel, applicationContext)
                        }
                        composable("history") {
                            HistoryScreen(navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}

private fun setAlarm(context: Context, viewModel: AppViewModel) {
    val hour = viewModel.randomTime.value!!.hour
    val minute = viewModel.randomTime.value!!.minute

    val calendar = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()

        if (get(Calendar.HOUR_OF_DAY) >= hour && get(Calendar.MINUTE) >= minute) {
            add(Calendar.DATE, 1)
        }

        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
    }

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, RandomAlarmReceiver::class.java)
    val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)

    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
}

@Composable
fun MainScreen(navController: NavHostController, viewModel: AppViewModel, context: Context) {
    viewModel.refreshTasks()
    viewModel.loadPriorityTask()
    viewModel.loadRandomTime()

    Scaffold(modifier = Modifier.background(color = Body), scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Open)), topBar = { MainTopBar(viewModel, context) }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            MainBodyContent(viewModel, context)
        }
    }, bottomBar = { BottomBar(navController) }, floatingActionButtonPosition = FabPosition.Center, isFloatingActionButtonDocked = true, floatingActionButton = { BottomFAB(viewModel, context) })
}

@Composable
fun MainBodyContent(viewModel: AppViewModel, context: Context) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Body)
        .padding(bottom = 10.dp)) {
        var selectedIndex by remember { mutableStateOf(-1) }
        var showDialog by remember { mutableStateOf(false) }

        val tasks = viewModel.tasks.observeAsState(mutableListOf()).value

        TaskList(viewModel = viewModel, context) {
            selectedIndex = it
            showDialog = true
        }

        if (showDialog) {
            AlertDialog(onDismissRequest = { showDialog = false }, confirmButton = {
                Button(onClick = {
                    viewModel.deleteAndRefreshTask(tasks[selectedIndex])
                    showDialog = false
                }, colors = ButtonDefaults.textButtonColors(backgroundColor = Green300, contentColor = Color.White)) {
                    Text("OK")
                }
            }, dismissButton = {
                TextButton(onClick = { showDialog = false }, colors = ButtonDefaults.textButtonColors(contentColor = Green300)) {
                    Text("NO")
                }
            }, text = { Text("${tasks[selectedIndex].name} を削除しますか？") })
        }
    }
}

@Composable
fun TaskList(viewModel: AppViewModel, context: Context, onDelete: (Int) -> Unit = {}) {
    val tasks = viewModel.tasks.observeAsState(mutableListOf()).value
    val priorityTask by viewModel.priorityTask.observeAsState(initial = Task(-1, "", false, Date()))

    LazyColumn(modifier = Modifier
        .background(Body)
        .fillMaxSize(), contentPadding = PaddingValues(5.dp)) {
        itemsIndexed(tasks) { index, task ->
            if (!task.finished) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 5.dp, start = 15.dp, end = 15.dp)
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .then(if (task == priorityTask) Modifier.border(border = BorderStroke(width = 2.dp, color = Green300), shape = RoundedCornerShape(10.dp)) else Modifier)) {
                    IconButton(onClick = {
                        val updatedTask = task.copy(finished = true, date = Date())
                        viewModel.updateAndRefreshTask(updatedTask)
                        Toast.makeText(context, "${task.name} を完了しました", Toast.LENGTH_SHORT).show()
                    }, modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp)
                        .weight(1f)) {
                        Icon(painter = painterResource(id = if (task.finished) {
                            R.drawable.baseline_check_circle_30
                        } else {
                            R.drawable.baseline_check_circle_30
                        }), modifier = Modifier.size(35.dp), contentDescription = null, tint = if (task.finished) {
                            Green300
                        } else {
                            Circle
                        })
                    }
                    Text(text = "${task.name}", fontSize = 18.sp, modifier = Modifier.weight(4f))
                    IconButton(onClick = { onDelete(index) }, modifier = Modifier
                        .padding(10.dp)
                        .size(30.dp)
                        .weight(1f)) {
                        Icon(painter = painterResource(id = R.drawable.baseline_delete_outline_30), contentDescription = null)
                    }
                }
            }
        }
    }
}

@Composable
fun MainTopBar(viewModel: AppViewModel, context: Context) {
    val randomTime = viewModel.randomTime.observeAsState(RandomTime(0, 0)).value

    val timePickerDialog = TimePickerDialog(LocalContext.current, { _, hour: Int, minute: Int ->
        randomTime.hour = hour
        randomTime.minute = minute
        viewModel.saveRandomTime(randomTime)
        setAlarm(context = context, viewModel)
    }, randomTime.hour, randomTime.minute, true)

    TopAppBar(title = { Text(text = "StaDice", color = Green300, modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) }, elevation = 4.dp, backgroundColor = Green100, modifier = Modifier
        .background(Body)
        .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)), actions = {
        IconButton(onClick = {
            timePickerDialog.show()
        }, modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_edit_notifications_30), contentDescription = null, tint = Green300)
        }
    })
}

@Composable
fun BackTopBar(navController: NavHostController, title: String) {
    TopAppBar(title = { Text(text = title, color = Green300, modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)) }, elevation = 4.dp, backgroundColor = Green100, modifier = Modifier
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
fun BottomFAB(viewModel: AppViewModel, context: Context) {
    FloatingActionButton(onClick = {
        viewModel.setPriorityTaskRandom()
        viewModel.savePriorityTask(viewModel.priorityTask.value!!)
        if (viewModel.priorityTask.value!!.id == -1) {
            Toast.makeText(context, "タスクが登録されていません", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "今日は ${viewModel.priorityTask.value!!.name} を取り組みましょう", Toast.LENGTH_SHORT).show()
        }
    }, contentColor = Green300, backgroundColor = Body, modifier = Modifier
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
