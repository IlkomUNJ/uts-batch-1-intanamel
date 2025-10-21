@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.utsmocom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.utsmocom.ui.theme.UtsMocomTheme
import androidx.compose.runtime.remember
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UtsMocomTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    ContactApp()
                }
            }
        }
    }
}

@Composable
fun ContactApp() {
    val navController = rememberNavController()
    val viewModel = remember { ContactViewModel() }

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            ListContactScreen(navController = navController, viewModel = viewModel)
        }
        composable("add") {
            AddEditContactScreen(navController = navController, viewModel = viewModel)
        }
        composable("edit/{index}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: -1
            AddEditContactScreen(
                navController = navController,
                viewModel = viewModel,
                isEdit = true,
                editIndex = index
            )
        }
    }
}
