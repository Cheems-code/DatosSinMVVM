package com.example.datossinmvvm

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificacionesScreen(
    navController: NavController,
    notificaciones: List<Pair<String, String>>,  // Lista de notificaciones (título, descripción)
    onUpdateNotificaciones: (List<Pair<String, String>>) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notificaciones") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("crear_notificacion") }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Notificación")
            }
        },
        bottomBar = {
            IconosInferiores() // Reutiliza los iconos de la parte inferior
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NotificacionesList(
                navController = navController,
                notificaciones = notificaciones,
                updateNotificaciones = onUpdateNotificaciones
            )
        }
    }
}

@Composable
fun NotificacionesList(
    navController: NavController,
    notificaciones: List<Pair<String, String>>,  // Lista de notificaciones (título, descripción)
    updateNotificaciones: (List<Pair<String, String>>) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        notificaciones.forEachIndexed { index, (titulo, descripcion) ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = titulo,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = descripcion,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificacionesScreenPreview() {
    val navController = rememberNavController()
    NotificacionesScreen(
        navController = navController,
        notificaciones = listOf(
            Pair("Notificación 1", "Descripción de la notificación 1"),
            Pair("Notificación 2", "Descripción de la notificación 2")
        ),
        onUpdateNotificaciones = {}
    )
}
