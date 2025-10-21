@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.utsmocom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ListContactScreen(
    navController: NavController,
    viewModel: ContactViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Daftar Kontak") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") }
            ) {
                Text("+")
            }
        }
    ) { padding ->
        if (viewModel.contacts.isEmpty()) {
            // Kalau belum ada kontak
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Belum ada kontak yang tersimpan")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(viewModel.contacts) { index, contact ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("edit/$index")
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = contact.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = contact.phone, style = MaterialTheme.typography.bodyMedium)
                            Text(text = contact.email, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
