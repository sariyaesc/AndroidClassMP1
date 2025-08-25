package com.jorgeromo.androidClassMp1.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jorgeromo.androidClassMp1.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pages = listOf(
        OnboardingPage(R.drawable.onboarding1, "Descubre y vive los mejores eventos en tu ciudad", "Con LiveIt siempre sabrás qué está pasando cerca de ti. Encuentra conciertos, talleres, festivales y más en un solo lugar, ¡sin perderte de nada!\n"),
        OnboardingPage(R.drawable.onboarding2, "Tu agenda de diversión en un solo lugar", "Recibe recomendaciones personalizadas, guarda tus eventos favoritos y comparte planes con tus amigos. Todo lo que necesitas para disfrutar tu ciudad al máximo."),
        OnboardingPage(R.drawable.onboarding3, "Promociona tus eventos fácilmente", "Crea, publica y llega a tu público ideal en minutos. LiveIt te ayuda a aumentar la asistencia y visibilidad de tus eventos sin complicaciones.")
    )

    val pagerState = rememberPagerState { pages.size }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = pages[page].image),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = pages[page].title, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = pages[page].description, style = MaterialTheme.typography.bodyMedium)
            }
        }

        Button(
            onClick = {
                if (pagerState.currentPage == pages.lastIndex) {
                    onFinish()
                } else {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                if (pagerState.currentPage == pages.lastIndex) "Empezar" else "Siguiente"
            )
        }
    }
}
