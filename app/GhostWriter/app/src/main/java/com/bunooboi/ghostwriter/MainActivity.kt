package com.bunooboi.ghostwriter

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bunooboi.ghostwriter.ui.theme.GhostWriterTheme
import java.util.*


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


    fun copyTextToClipboard(text: String) {
        val clipboardManager = this.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        val clipData = ClipData.newPlainText("text", text)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "コピーしました。", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun AutoSizeText(text: String, modifier: Modifier = Modifier, fontSize: TextUnit = 10.sp, padding: MutableList<Dp> = mutableListOf(0.dp, 0.dp, 0.dp, 0.dp), textAlign: TextAlign = TextAlign.Center, color: Color = Color.Black) {
    // デバイスの画面サイズを取得する
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density

    // テキストサイズを設定する
    val fontSize = remember {
        if (screenWidth > 720) {
            fontSize * 2
        } else if (screenWidth > 480) {
            fontSize * 1.5f
        } else if (screenWidth > 320) {
            fontSize
        } else {
            fontSize / 2
        }
    }

    for (i in 0..3) {
        padding[i] = remember {
            if (screenWidth > 720) {
                padding[i] * 4
            } else if (screenWidth > 480) {
                padding[i] * 2
            } else if (screenWidth > 320) {
                padding[i]
            } else {
                padding[i] / 2
            }
        }
    }
    Text(text = text, fontSize = fontSize, modifier = modifier.padding(padding[0], padding[1], padding[2], padding[3]), textAlign = textAlign, color = color)
}

@Composable
fun AppScreen() {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val navController = rememberNavController()

    Column {
        TopAppBar(title = { Text(text = "ゴーストライ太") }, elevation = 4.dp, navigationIcon = {
            Image(painter = painterResource(id = R.mipmap.ic_launcher_foreground), contentDescription = null)
        }, actions = {
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(Icons.Filled.Menu, contentDescription = "Search Icon")
            }
            DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(onClick = {
                    val currentScreen = navController.currentBackStackEntry?.destination?.route.toString()
                    if (currentScreen != "termScreen") navController.navigate("termScreen")
                    isExpanded = false
                }) {
                    Text(text = "利用規約", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
                }
            }
        })
        NavHost(navController = navController, startDestination = "mainScreen") {
            composable(route = "mainScreen") {
                MainScreen()
            }
            composable(route = "termScreen") {
                TermScreen(onClick = { navController.navigateUp() })
            }
        }
    }
}

@Composable
fun TermScreen(onClick: () -> Unit) {
    var shouldNavigateBack by remember { mutableStateOf(false) }

    BackHandler(enabled = shouldNavigateBack) {
        onClick()
    }
    Column(modifier = Modifier
        .background(Color(0xFF88BEEA))
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Text(text = "この規約は(以下「本規約」という)、お客様にbunooboi(以下「bunooboi」という)が提供する「ゴーストライ太(以下「本アプリ」という)をご利用頂く際の取扱いにつき定めるものです。\n" + "本規約に同意した上で本アプリをダウンロード、インストール又は利用してください。\n" + "本規約にご同意いただけない場合、本アプリを利用することはできませんので、その場合は直ちに本アプリのダウンロード、インストール又は利用を中止してください。\n" + "本アプリをダウンロード、インストール又は利用する場合、本規約に同意したものとみなします。\n\n" + "1.サービス概要\n" + "本アプリは、お客様のAndroid搭載端末にダウンロードされたアプリ「ゴーストライ太」を使用して、bunooboiのサービスをご利用いただくものです。\n\n" + "2.本アプリを利用できる対象機種・対象OSは、bunooboiが所定のものに限られます。\n\n" + "3.知的財産権\n" + "本アプリで提供される情報（以下「情報等」という）及び本アプリの著作権等の知的財産権は、bunooboiまたは正当な権利を有する第三者に帰属します。\n\n" + "4.不保証\n" + "bunooboiが提供する情報等については、その内容を保証するものではありません。\n" + "提供される情報に基づいてお客様が不利益を被った場合であっても、bunooboiおよび情報提供元は一切責任を負いません。\n\n" + "5.免責事項\n" + "本アプリのご利用に関して、お客様の損害について、bunooboiは一切の責任を負わないものとします。\n" + "(a)本アプリの不具合(表示情報の誤謬・逸脱、取引依頼の不能等)による損害\n" + "(b)本アプリがお客様の携帯電話機器に与える影響・損害\n" + "(c)お客様が本アプリを正常に利用できないことにより生じた損害\n" + "(d)通信回線及びシステム機器等の瑕疵または障害(天災地変等不可抗力によるものを含みます)、コンピュータウィルスや第三者からなされる請求により生じた損害\n" + "(e)bunooboiが、他に定める約款、取引説明書等にに免責事項として定める損害\n" + "(f)その他、一切の損害\n" + "お客様は、本規約に定めるところに違反した場合、bunooboiは直ちにお客様の本アプリ利用を停止できるものとします。\n\n" + "6.規約変更\n" + "bunooboiは、お客様の承諾およびお客様への通知なしに、いつでも本アプリの中止・内容変更、本アプリのバージョンアップ、本規約の変更を行うことができるものとします。\n\n" + "7.準拠法及び裁判管轄\n" + "本アプリ利用に関し紛争が生じた場合には、東京地方裁判所を第一審の専属的合意管轄裁判所とします。また、本規約は日本国法に準じて解釈されます。\n" + "以上\n\n" + "2023年4月25日制定", modifier = Modifier
            .background(Color(0xFF88BEEA))
            .fillMaxSize()
            .padding(20.dp))
    }
}

@Composable
fun MainScreen() {
    Column {
        Title(modifier = Modifier.weight(0.5f))
        SorryRow(modifier = Modifier
            .weight(1f)
            .background(Color(0xFF6E9FD1)), title = "Who", arrayOf(R.drawable.boss, R.drawable.colleague, R.drawable.family), arrayOf("上司", "同僚", "家族"), index = 0)
        SorryRow(modifier = Modifier
            .weight(1f)
            .background(Color(0xFF176CB6)), title = "Level", arrayOf(R.drawable.serious, R.drawable.normal, R.drawable.easy), arrayOf("真剣", "普通", "気軽"), index = 1)
        SorryRow(modifier = Modifier
            .weight(1f)
            .background(Color(0xFF11449D)), title = "Why", arrayOf(R.drawable.late, R.drawable.vacation, R.drawable.miss), arrayOf("遅刻", "休暇", "ミス"), index = 2)
    }
}


@Composable
fun Title(modifier: Modifier) {
    val displayMetrics = LocalContext.current.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density

    val padding = 10.dp

    val autoPadding = remember {
        if (screenWidth > 720) {
            padding * 2
        } else if (screenWidth > 480) {
            padding * 1.5f
        } else if (screenWidth > 320) {
            padding
        } else {
            padding / 2
        }
    }

    Row(modifier = modifier
        .fillMaxSize()
        .background(Color(0xFF88BEEA))) {
        AutoSizeText(text = "あなたの代わりに作文します。", fontSize = 15.sp, modifier = Modifier
            .weight(1.5f)
            .align(Alignment.CenterVertically), padding = mutableListOf(15.dp, 0.dp, 0.dp, 0.dp), textAlign = TextAlign.Left, color = Color.White)
        Image(painter = painterResource(id = R.drawable.title), contentDescription = null, modifier = Modifier
            .weight(1f)
            .padding(autoPadding, autoPadding * 1.5f))
    }
}

@Composable
fun SorryRow(modifier: Modifier, title: String, imgs: Array<Int>, label: Array<String>, index: Int) {
    Column(modifier = modifier.fillMaxSize()) {
        AutoSizeText(text = title, modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(0.dp, 10.dp, 0.dp, 0.dp), fontSize = 18.sp, textAlign = TextAlign.Center, color = Color.White)
        ImageRow(modifier = Modifier.weight(4f), imgs = imgs, index = index)
        Row(modifier = Modifier.weight(1f)) {
            for (i in 0..2) {
                AutoSizeText(text = label[i], modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 15.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun ImageRow(modifier: Modifier, imgs: Array<Int>, index: Int) {
    // var selected by remember { mutableStateOf(0) }
    Row(modifier = modifier.fillMaxSize()) {
        for (i in 0..2) {
            CircleButton(modifier = Modifier.weight(1f), img = imgs[i], index1 = index, index2 = i)
        }
    }
}

@Composable
fun CircleButton(viewModel: SorryViewModel = viewModel(), modifier: Modifier, img: Int, index1: Int, index2: Int) {
    val selected = viewModel.selected.observeAsState(listOf(0, 0, 0)).value.toMutableStateList()[index1]

    val context = LocalContext.current
    val mainActivity = context as MainActivity

    val displayMetrics = LocalContext.current.resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels / displayMetrics.density

    val paddingWrap = 15.dp

    val autoPaddingWrap = remember {
        if (screenWidth > 720) {
            paddingWrap * 4
        } else if (screenWidth > 480) {
            paddingWrap * 1.5f
        } else if (screenWidth > 320) {
            paddingWrap
        } else {
            paddingWrap / 2
        }
    }

    val paddingContent = 20.dp

    val autoPaddingContent = remember {
        if (screenWidth > 720) {
            paddingContent * 2
        } else if (screenWidth > 480) {
            paddingContent * 1.5f
        } else if (screenWidth > 320) {
            paddingContent
        } else {
            paddingContent / 2
        }
    }

    Surface(shape = CircleShape, modifier = modifier
        .padding(autoPaddingWrap)
        .aspectRatio(1f)) {
        Box(modifier = Modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .clickable(enabled = selected != index2 + 1, onClick = {
                val sentence = viewModel.onSelected(index1, index2 + 1)
                if (sentence != "") mainActivity.copyTextToClipboard(sentence)
            }), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = img), contentDescription = null, modifier = Modifier
                .background(if (selected == index2 + 1) Color.Yellow else Color.White)
                .clip(CircleShape)
                .padding(autoPaddingContent))
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