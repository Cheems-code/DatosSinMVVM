package com.example.datossinmvvm

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    var notificaciones by remember { mutableStateOf(listOf<Pair<String, String>>()) }

    NavHost(navController = navController, startDestination = "lista_notificaciones") {
        composable("lista_notificaciones") {
            PersonalizarAlertasCRUDScreen(
                navController = navController,
                notificaciones = notificaciones,
                onUpdateNotificaciones = { updatedList ->
                    notificaciones = updatedList
                }
            )
        }

        // Nueva ruta para la pantalla de notificaciones
        composable("notificaciones") {
            NotificacionesScreen(
                navController = navController,
                notificaciones = notificaciones,
                onUpdateNotificaciones = { updatedList ->
                    notificaciones = updatedList
                }
            )
        }

        composable(
            "editar_notificacion/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            val notificacionActual = notificaciones.getOrNull(index)

            if (notificacionActual != null) {
                EditarNotificacionScreen(
                    navController = navController,
                    index = index,
                    notificacionActual = notificacionActual,
                    onUpdateNotificacion = { i, updatedNotificacion ->
                        notificaciones = notificaciones.toMutableList().apply {
                            set(i, updatedNotificacion)
                        }
                    }
                )
            }
        }

        composable("crear_notificacion") {
            CrearNotificacionScreen(
                navController = navController,
                onCreateNotificacion = { newNotificacionTitle, newNotificacionDescription ->
                    notificaciones = notificaciones + Pair(newNotificacionTitle, newNotificacionDescription)
                }
            )
        }
    }
}
