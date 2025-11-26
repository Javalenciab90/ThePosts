package com.javalenciab90.theposts

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.javalenciab90.design_system.theme.ThePostsTheme
import com.javalenciab90.theposts.navigation.NavigatorAppHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThePostsTheme {
                NavigatorAppHost()
            }
        }
    }
}