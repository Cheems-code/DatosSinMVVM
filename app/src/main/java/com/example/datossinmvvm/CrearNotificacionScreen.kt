package com.example.datossinmvvm

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearNotificacionScreen(
    navController: NavController,
    onCreateNotificacion: (String, String) -> Unit
) {
    var newNotificacionTitle by remember { mutableStateOf("") }
    var newNotificacionDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Notificación") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = newNotificacionTitle,
                onValueChange = { newNotificacionTitle = it },
                label = { Text("Título de la Notificación") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = newNotificacionDescription,
                onValueChange = { newNotificacionDescription = it },
                label = { Text("Descripción de la Notificación") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (newNotificacionTitle.isNotEmpty() && newNotificacionDescription.isNotEmpty()) {
                        onCreateNotificacion(newNotificacionTitle, newNotificacionDescription)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Crear")
            }
        }
    }
}