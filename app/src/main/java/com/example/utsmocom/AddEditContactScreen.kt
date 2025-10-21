@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.utsmocom

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddEditContactScreen(
    navController: NavController,
    viewModel: ContactViewModel,
    isEdit: Boolean = false,
    editIndex: Int = -1
) {

    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }


    LaunchedEffect(isEdit, editIndex) {
        if (isEdit && editIndex >= 0) {
            val contact = viewModel.contacts[editIndex]
            name = contact.name
            address = contact.address
            phone = contact.phone
            email = contact.email
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isEdit) "Edit Kontak" else "Tambah Kontak"
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nama") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Alamat") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Telepon") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val newContact = Contact(name, address, phone, email)
                    if (isEdit && editIndex >= 0) {
                        viewModel.updateContact(editIndex, newContact)
                    } else {
                        viewModel.addContact(newContact)
                    }
                    navController.navigate("list")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isEdit) "Simpan Perubahan" else "Tambah Kontak")
            }
        }
    }
}
