package com.jorgeromo.androidClassMp1.navigation

import SecondPartialView
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.jorgeromo.androidClassMp1.firstpartial.FirstPartialView
import com.jorgeromo.androidClassMp1.ids.imc.views.IMCView
import com.jorgeromo.androidClassMp1.ids.IdsView
import com.jorgeromo.androidClassMp1.ids.location.views.LocationListScreen
import com.jorgeromo.androidClassMp1.ids.login.views.LoginView
import com.jorgeromo.androidClassMp1.ids.student.views.StudentView
import com.jorgeromo.androidClassMp1.ids.sum.views.SumView
import com.jorgeromo.androidClassMp1.ids.temperature.views.TempView
import com.jorgeromo.androidClassMp1.thirdpartial.ThirdPartialView
import androidx.compose.ui.graphics.Color
import com.jorgeromo.androidClassMp1.ids.login.LoginScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarNavigationView(navController: NavHostController = rememberNavController()) {
    val items = listOf(
        ScreenNavigation.Ids,
        ScreenNavigation.FirstPartial,
        ScreenNavigation.SecondPartial,
        ScreenNavigation.ThirdPartial
    )

    // Mapa de títulos por ruta (incluye tabs y pantallas internas)
    val routeTitles = remember {
        mapOf(
            ScreenNavigation.Ids.route to ScreenNavigation.Ids.label,
            ScreenNavigation.FirstPartial.route to ScreenNavigation.FirstPartial.label,
            ScreenNavigation.SecondPartial.route to ScreenNavigation.SecondPartial.label,
            ScreenNavigation.ThirdPartial.route to ScreenNavigation.ThirdPartial.label,

            // Rutas internas (ajusta a tus strings preferidos)
            ScreenNavigation.IMC.route to "IMC",
            ScreenNavigation.Login.route to "Login",
            ScreenNavigation.Sum.route to "Suma",
            ScreenNavigation.Temperature.route to "Temperatura",
            ScreenNavigation.StudentList.route to "Estudiantes",
            ScreenNavigation.Locations.route to "Ubicaciones"
        )
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // Si usas nested graphs, puedes leer la jerarquía; aquí basta con la route actual:
    val currentRoute = navBackStackEntry?.destination?.route
    val currentTitle = routeTitles[currentRoute] ?: ""

    Scaffold(
        topBar = {
            // Puedes usar SmallTopAppBar o CenterAlignedTopAppBar
            CenterAlignedTopAppBar(
                title = { Text(text = "Sara Escamilla 12105") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFF2196F3), // Azul
                titleContentColor = Color.White
            )
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEach { screen ->
                    val selected = currentRoute == screen.route
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNavigation.Ids.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ScreenNavigation.Ids.route) { IdsView(navController) }
            composable(ScreenNavigation.FirstPartial.route) { FirstPartialView(navController) }
            composable(ScreenNavigation.SecondPartial.route) { SecondPartialView() }
            composable(ScreenNavigation.ThirdPartial.route) { ThirdPartialView(navController) }
            composable(ScreenNavigation.Login.route) { LoginScreen() }



            // Rutas internas
            composable(ScreenNavigation.IMC.route) { IMCView() }
            composable(ScreenNavigation.Login.route) { LoginView() }
            composable(ScreenNavigation.Sum.route) { SumView() }
            composable(ScreenNavigation.Temperature.route) { TempView() }
            composable(ScreenNavigation.StudentList.route) { StudentView() }
            composable(ScreenNavigation.Locations.route) { LocationListScreen() }
        }
    }
}
