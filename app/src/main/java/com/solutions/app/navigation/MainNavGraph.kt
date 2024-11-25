package com.solutions.app.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solutions.app.modules.auth.login.LoginScreen


@Composable
fun MainNavGraph(modifier: Modifier = Modifier) {
    val mainNavController = rememberNavController()

    NavHost(
        mainNavController,
        modifier = modifier,
        startDestination = Destinations.Login.route
    ) {

        composable(Destinations.Login.route) { LoginScreen(mainNavController) }

        composable(Destinations.SignUp.route) { Text("SignUp") }

        composable(Destinations.ForgotPassword.route) { Text("ForgotPassword") }

        composable(Destinations.Dashboard.route) { DashboardNavGraph(mainNavController) }

        composable(Destinations.Help.route) { Text("Help") }

        composable(Destinations.Settings.route) { Text("Settings") }

    }

}