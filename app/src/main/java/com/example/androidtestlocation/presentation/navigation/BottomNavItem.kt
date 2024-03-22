package com.example.androidtestlocation.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.androidtestlocation.R




val bottomNavItems = listOf<BottomNavItem>(
    BottomNavItem("settings", R.drawable.settings, "settings"),
    BottomNavItem("expenses", R.drawable.expenses, "expenses"),
    BottomNavItem("add", R.drawable.add, "-"),
    BottomNavItem("moodboard", R.drawable.moodboard, "moodboard"),
    BottomNavItem("location", R.drawable.location, "location")
)

data class BottomNavItem(
    val title: String,
    val iconId: Int,
    val route: String
)


sealed class NavRoutes(val route: String){
    object SettingsRoute: NavRoutes("settings")
    object ExpensesRoute: NavRoutes("expenses")
    object MoodboardRoute: NavRoutes("moodboard")
    object LocationRoute: NavRoutes("location")
}