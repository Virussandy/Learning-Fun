package com.example.examplea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.examplea.ui.theme.ExampleATheme
import com.example.examplea.util.Screens

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExampleATheme {
                val windowSizeClass = calculateWindowSizeClass(this)
                when(windowSizeClass.widthSizeClass){
                    WindowWidthSizeClass.Compact -> NavBottom()
                    WindowWidthSizeClass.Medium -> NavRail()
                    WindowWidthSizeClass.Expanded -> NavPermanent()
                }
            }
        }
    }
}

@Composable
fun NavPermanent(modifier: Modifier = Modifier) {
    var selectedScreen by remember { mutableStateOf<Screens>(Screens.DashBoard) }

    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet {
                listOf(Screens.DashBoard,Screens.Tasks,Screens.Completed,Screens.Settings).forEach{
                    screen -> NavigationDrawerItem(
                        icon = { Icon(screen.icon,null) },
                        label = { Text(screen.title) },
                        selected = selectedScreen == screen,
                        onClick = {selectedScreen = screen}
                    )
                }
            }
        }
    ){
        Box(modifier = modifier.fillMaxSize()){
            when(selectedScreen){
                Screens.DashBoard -> DashboardPage()
                Screens.Tasks -> TasksPage()
                Screens.Completed -> CompletedPage()
                Screens.Settings -> SettingsPage()
            }
        }
    }
}

@Composable
fun NavRail(modifier: Modifier = Modifier) {
    var selectedScreen by remember { mutableStateOf<Screens>(Screens.DashBoard) }

    Row {
        NavigationRail {
            listOf(Screens.DashBoard,Screens.Tasks,Screens.Completed,Screens.Settings).forEach{
                screens ->
                NavigationRailItem(
                    icon = {Icon(screens.icon,null)},
                    label = { Text(screens.title) },
                    selected = selectedScreen == screens,
                    onClick = {selectedScreen = screens}
                )
            }
        }
        Box(modifier.fillMaxSize()){
            when(selectedScreen){
                Screens.DashBoard -> DashboardPage()
                Screens.Tasks -> TasksPage()
                Screens.Completed -> CompletedPage()
                Screens.Settings -> SettingsPage()
            }
        }
    }
}

@Composable
fun NavBottom(modifier: Modifier = Modifier) {

    var selectedScreen by remember { mutableStateOf<Screens>(Screens.DashBoard) }
    Scaffold(
        bottomBar = {
            BottomAppBar {
                listOf(Screens.DashBoard,Screens.Tasks,Screens.Completed,Screens.Settings).forEach{
                    screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon,null) },
                        label = { Text(screen.title) },
                        selected = selectedScreen == screen,
                        onClick = {selectedScreen = screen}
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier.padding(innerPadding)) {
            when(selectedScreen){
                Screens.DashBoard -> DashboardPage(modifier)
                Screens.Tasks -> TasksPage(modifier)
                Screens.Completed -> CompletedPage(modifier)
                Screens.Settings -> SettingsPage(modifier)
            }
        }
    }
}

@Composable
fun DashboardPage(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Dashboard Page")
    }
}

@Composable
fun TasksPage(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Tasks Page")
    }
}

@Composable
fun CompletedPage(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Completed Page")
    }
}

@Composable
fun SettingsPage(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Settings Page")
    }
}