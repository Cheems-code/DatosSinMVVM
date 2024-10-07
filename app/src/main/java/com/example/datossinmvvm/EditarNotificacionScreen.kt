package com.example.datossinmvvm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarNotificacionScreen(
    navController: NavController,
    index: Int,
    notificacionActual: Pair<String, String>,
    onUpdateNotificacion: (Int, Pair<String, String>) -> Unit
) {
    var titulo by remember { mutableStateOf(notificacionActual.first) }
    var descripcion by remember { mutableStateOf(notificacionActual.second) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Notificación") },
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
            // Campo de texto para el título
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para la descripción
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para guardar los cambios
            Button(
                onClick = {
                    // Actualizamos la notificación con el nuevo título y descripción
                    onUpdateNotificacion(index, Pair(titulo, descripcion))
                    navController.popBackStack()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Guardar")
            }
        }
    }
}
