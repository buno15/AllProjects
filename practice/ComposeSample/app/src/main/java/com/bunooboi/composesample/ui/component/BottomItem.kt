package com.bunooboi.composesample.ui.component

import com.bunooboi.composesample.R

sealed class BottomItem(val label: String, val route: String, val icon: Int) {
    object Menu : BottomItem(label = "Menu", route = "Menu", icon = R.drawable.baseline_menu_book_24)
    object Settings : BottomItem(label = "Settings", route = "Settings", icon = R.drawable.baseline_settings_24)
}