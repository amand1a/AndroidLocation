package com.example.androidtestlocation.presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidtestlocation.R
import com.example.androidtestlocation.presentation.navigation.NavRoutes
import com.example.androidtestlocation.presentation.navigation.bottomNavItems
import com.example.androidtestlocation.presentation.viewModels.LocationViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidtestlocation.presentation.viewModels.NavViewModel


@Composable
fun BottomNavigationWithFab(viewModel: NavViewModel = viewModel()) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            Box(modifier = Modifier
                .background(Color.Transparent)) {

                Box(
                    modifier = Modifier
                        .padding(top = 50.dp)
                ) {
                    Surface(
                        shadowElevation = 12.dp
                    ) {


                        Row (modifier = Modifier.background(Color.White).height(50.dp)) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route
                            bottomNavItems.forEachIndexed { index, item ->
                                if (index == 2) {
                                    Spacer(modifier = Modifier.weight(1f))
                                } else {
                                    NavigationBarItem(
                                        icon = {
                                            Icon(
                                                painterResource(id = item.iconId),
                                                contentDescription = item.title
                                            )
                                        },
                                        modifier = Modifier.height(40.dp),
                                        selected = currentRoute == item.route,
                                        onClick = {  navController.navigate(item.route) {
                                            popUpTo(navController.graph.startDestinationId)
                                            launchSingleTop = true
                                        } },
                                        alwaysShowLabel = false,
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = Color.Black,
                                            unselectedIconColor = Color(0xFFCFD2CC),
                                            selectedTextColor = Color.Transparent,
                                            indicatorColor = Color.White
                                        )
                                    )
                                }

                            }
                        }

                    }

                }
                IconButton(onClick = { viewModel.addLocation()},
                    modifier = Modifier.align(Alignment.TopCenter).size(100.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = ""
                    )
                }
            }

        },


    ) {
        NavHost(navController = navController, startDestination = NavRoutes.LocationRoute.route , modifier = Modifier
            .padding(it.calculateBottomPadding())
            .background(
                Color(0xFFFAFAFA)
            ) ){
            composable(NavRoutes.LocationRoute.route){
                val viewModelL = hiltViewModel<LocationViewModel>()
                LocationScreen(viewModelL)
            }
            composable(NavRoutes.SettingsRoute.route){

            }
            composable(NavRoutes.ExpensesRoute.route){

            }
            composable(NavRoutes.MoodboardRoute.route){

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigation() {
    BottomNavigationWithFab()
}

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

