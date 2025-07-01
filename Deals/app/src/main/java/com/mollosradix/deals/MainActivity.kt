package com.mollosradix.deals

import android.Manifest
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.mollosradix.deals.navigation.BottomNavBar
import com.mollosradix.deals.navigation.NavigationItem
import com.mollosradix.deals.ui.screens.DealDetailScreen
import com.mollosradix.deals.ui.screens.DealsScreen
import com.mollosradix.deals.ui.screens.NotificationScreen
import com.mollosradix.deals.ui.screens.ProfileScreen
import com.mollosradix.deals.ui.screens.SearchScreen
import com.mollosradix.deals.ui.theme.DealsTheme
import com.mollosradix.deals.viewmodel.DealsViewModel
import com.mollosradix.deals.viewmodel.NotificationViewModel
import com.mollosradix.deals.viewmodel.SearchViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        val dealIdFromNotification = intent.getStringExtra("deal_id")
        setContent {
            DealsTheme {
                MainScreen(dealId = dealIdFromNotification)
            }
        }
        createNotificationChannel(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1001
            )
        }


    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String?>,
//        grantResults: IntArray,
//        deviceId: Int
//    ) {
//        if (requestCode == 1001 && grantResults.isNotEmpty()) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "Notifications enabled", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(this, "Notifications disabled", Toast.LENGTH_SHORT).show()
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
//    }

    override fun onResume() {
        super.onResume()
        AppState.isInForeground = true
    }

    override fun onPause() {
        super.onPause()
        AppState.isInForeground = false
    }

}


fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Good Deals"
        val descriptionText = "Get notified about best deals!"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("good_deals_channel", name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}


@Composable
fun MainScreen(dealId: String?) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val dealsViewModel = remember { DealsViewModel() }
    val notificationViewModel = remember { NotificationViewModel(context.applicationContext as Application) }

    LaunchedEffect(key1 = dealId) {
        dealId?.let {
            navController.navigate("dealDetail/$dealId")
        }
    }

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BottomNavBar(navController = navController)
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavigationItem.Home.route) {
                DealsScreen(
                    navController = navController,
                    viewModel = dealsViewModel,
                )
            }
            composable(NavigationItem.Search.route) { backStackEntry ->
                val searchViewModel: SearchViewModel = viewModel(backStackEntry)

                SearchScreen(
                    viewModel = searchViewModel, onBackOperation = {
                        navController.popBackStack()
                    })
            }
            composable(NavigationItem.Notifications.route) { NotificationScreen(viewModel = notificationViewModel) }
            composable(NavigationItem.Profile.route) { ProfileScreen() }

            composable("dealDetail/{dealId}") { backStackEntry ->
                val dealIdArg = backStackEntry.arguments?.getString("dealId")
                DealDetailScreen(dealId = dealIdArg ?: "")
            }
        }
    }
}


object AppState{
    var isInForeground = false
}


