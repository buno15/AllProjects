package com.bunooboi.stadice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bunooboi.stadice.ui.theme.Body
import com.bunooboi.stadice.ui.theme.Green100
import com.bunooboi.stadice.ui.theme.Green200
import com.bunooboi.stadice.ui.theme.StadiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StadiceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    AppScreen()
                }
            }
        }
    }
}

@Composable
fun AppScreen() {
    Scaffold(modifier = Modifier.background(color = Body), scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Open)), topBar = { TopBar() }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            BodyContent()
        }
    }, bottomBar = { BottomBar() }, floatingActionButtonPosition = FabPosition.Center, isFloatingActionButtonDocked = true, floatingActionButton = { BottomFAB() })
}

@Composable
fun BodyContent() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Body)) {
        Text(text = "asdf")
    }
}

@Composable
fun TopBar() {
    TopAppBar(title = { Text(text = "StaDice", color = Green200, modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)) }, elevation = 4.dp, backgroundColor = Green100, modifier = Modifier
        .background(Body)
        .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)), actions = {
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_edit_notifications_30), contentDescription = null, tint = Green200)
        }
    })
}

@Composable
fun BottomBar() {
    val navController = rememberNavController()

    BottomAppBar(modifier = Modifier
        .background(Body)
        .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
        .height(65.dp), backgroundColor = Green200, cutoutShape = androidx.compose.foundation.shape.CircleShape, elevation = 22.dp) {
        BottomNavigation(navController = navController)
    }
}

@Composable
fun BottomFAB() {
    FloatingActionButton(onClick = { /*TODO*/ }, contentColor = Green200, backgroundColor = Body, modifier = Modifier
        .width(80.dp)
        .height(80.dp)) {
        Icon(painter = painterResource(id = R.drawable.baseline_casino_40), contentDescription = null, tint = Green200)
    }
}

sealed class Screen(val route: String, val label: String, val icon: Int) {
    object MailScreen : Screen("add", "追加", R.drawable.baseline_add_24)
    object ProfileScreen : Screen("history", "履歴", R.drawable.baseline_history_24)
}

val screenItem = listOf(Screen.MailScreen, Screen.ProfileScreen)

@Composable
fun BottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(modifier = Modifier
        .height(100.dp)
        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)), elevation = 0.dp, backgroundColor = Green200) {
        screenItem.forEach { screen ->
            BottomItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.BottomItem(screen: Screen, currentDestination: NavDestination?, navController: NavHostController) {
    val pad = if (screen.route == "add") Modifier.padding(0.dp, 0.dp, 30.dp, 0.dp) else Modifier.padding(30.dp, 0.dp, 0.dp, 0.dp)

    BottomNavigationItem(modifier = pad, icon = { Icon(painter = painterResource(id = screen.icon), modifier = Modifier.size(35.dp), contentDescription = null, tint = Color.White) }, label = {
        screen.label?.let {
            Text(text = it, color = Color.White)
        }
    }, selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true, unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled), onClick = {})
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StadiceTheme {
        AppScreen()
    }
}