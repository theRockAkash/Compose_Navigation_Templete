package com.solutions.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.solutions.app.R
import kotlinx.coroutines.launch

/**
 * @Created by akash on 21-11-2024.
 * Know more about author at https://akash.cloudemy.in
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardNavGraph(mainNavController: NavHostController) {
    val bottomNavController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    NavigationDrawer(
        mainNavController = mainNavController,
        drawerState = drawerState,
        closeDrawer = {
            scope.launch { drawerState.close() }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar { BottomNavigationBar(bottomNavController = bottomNavController) }
            }) {
            NavHost(
                bottomNavController,
                startDestination = Destinations.Home.route,
                Modifier.padding(it)
            ) {
                composable(Destinations.Home.route) { Text("Home") }
                composable(Destinations.Search.route) { Text("Search") }
                composable(Destinations.Profile.route) { Text("Profile") }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(bottomNavController: NavHostController) {
    val destinations = listOf(Destinations.Home, Destinations.Search, Destinations.Profile)
    val selectedItem = bottomNavController.currentBackStackEntryAsState().value?.destination?.route
    NavigationBar {
        destinations.forEach { item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { item.icon?.let { Icon(it, contentDescription = item.label) } },
                label = { Text(item.label) },
                selected = selectedItem == item.route,
                onClick = {
                    bottomNavController.navigate(item.route) {
                        bottomNavController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}