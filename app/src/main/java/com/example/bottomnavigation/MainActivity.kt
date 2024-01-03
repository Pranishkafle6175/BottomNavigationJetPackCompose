package com.example.bottomnavigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottomnavigation.ui.theme.BottomNavigationTheme
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationTheme {
                val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {BottomBar(navController)}
                    ) {
                            NavHost(navController = navController, startDestination= BottomNavigationScreen.HomeScreen.route ){
                                composable(BottomNavigationScreen.HomeScreen.route){
                                    Home()
                                }

                                composable(BottomNavigationScreen.ProfileScreen.route){
                                    Profile()
                                }

                                composable(BottomNavigationScreen.Setting.route){
                                    Settings()
                                }

                            }
                    }

            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    
    val screens = listOf(
        BottomNavigationScreen.HomeScreen,
        BottomNavigationScreen.ProfileScreen,
        BottomNavigationScreen.Setting
    )

    BottomNavigation {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEachIndexed { index, screen -> 
            BottomNavigationItem(
                icon ={ Icon(
                    imageVector = screen.icon,
                    contentDescription = "Navigation Item")},
                label ={screen.title},
                selected = currentDestination?.hierarchy?.any {it.route==screen.route}==true,
                onClick = {
                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState=true
                        }

                        launchSingleTop=true

                        restoreState= true
                    }
                }

            )
        }
    }
}

