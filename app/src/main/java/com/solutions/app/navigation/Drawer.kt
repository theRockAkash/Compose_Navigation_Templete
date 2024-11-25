package com.solutions.app.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.solutions.app.R

/**
 * @Created by akash on 19-11-2024.
 * Know more about author at https://akash.cloudemy.in
 */


@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    mainNavController: NavHostController,
    closeDrawer: () -> Unit,
    body: @Composable () -> Unit = {}
) {
    val items = listOf(
        Destinations.Settings,
        Destinations.Help
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(IntrinsicSize.Min)) {
                Box(
                    Modifier
                        .padding(bottom = 16.dp)
                        .height(120.dp)
                        .fillMaxWidth()
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(0.dp)
                        )
                ) {
                    Image(
                        bitmap = ImageBitmap.imageResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "logo",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .height(56.dp)
                            .wrapContentWidth()
                            .align(Alignment.Center)
                    )
                }
                items.forEach {
                    Text(
                        text = it.label,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                closeDrawer()
                                mainNavController.navigate(it.route)
                            }
                            .padding(16.dp)
                    )
                }

            }
        },
        gesturesEnabled = true
    ) {
        body.invoke()
    }

}
