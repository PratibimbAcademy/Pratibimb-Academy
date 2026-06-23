package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.AuthScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AcademyViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val viewModel: AcademyViewModel = viewModel()
                val currentUser by viewModel.currentUser.collectAsState()
                val activeQuiz by viewModel.activeQuiz.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimatedContent(
                        targetState = Triple(currentUser == null, activeQuiz != null, innerPadding),
                        transitionSpec = {
                            fadeIn() togetherWith fadeOut()
                        },
                        label = "main_screen_flow"
                    ) { (isLoggedOut, isQuizMode, padding) ->
                        when {
                            isLoggedOut -> {
                                AuthScreen(
                                    viewModel = viewModel,
                                    modifier = Modifier.padding(padding)
                                )
                            }
                            isQuizMode -> {
                                QuizScreen(
                                    viewModel = viewModel,
                                    modifier = Modifier.padding(padding)
                                )
                            }
                            else -> {
                                DashboardScreen(
                                    viewModel = viewModel,
                                    modifier = Modifier.padding(padding)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
