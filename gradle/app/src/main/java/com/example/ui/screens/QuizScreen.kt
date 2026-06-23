package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.QuizItem
import com.example.ui.theme.*
import com.example.ui.viewmodel.AcademyViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuizScreen(
    viewModel: AcademyViewModel,
    modifier: Modifier = Modifier
) {
    val activeQuiz by viewModel.activeQuiz.collectAsState()
    val questions by viewModel.quizQuestions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val selectedAnswers by viewModel.selectedAnswers.collectAsState()
    val timerSeconds by viewModel.quizTimerSeconds.collectAsState()
    val finished by viewModel.quizFinished.collectAsState()
    val resultMsg by viewModel.quizResultMessage.collectAsState()
    val score by viewModel.quizFinalScore.collectAsState()

    val currentQuiz = activeQuiz ?: return

    // Helper to format remaining duration seconds into MM:SS
    val minutes = timerSeconds / 60
    val seconds = timerSeconds % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(DeepNavyBackground, Color(0xFF0F1E31))
                )
            )
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- TOP HEADER ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { viewModel.cancelQuiz() },
                    modifier = Modifier
                        .background(Color(0xFF1E293B), CircleShape)
                        .size(36.dp)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White, modifier = Modifier.size(18.dp))
                }

                Text(
                    text = currentQuiz.title,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f).padding(horizontal = 12.dp),
                    textAlign = TextAlign.Center
                )

                // Countdown Timer Box
                Box(
                    modifier = Modifier
                        .background(if (timerSeconds < 30) Color(0x33EF4444) else Color(0x3310B981), RoundedCornerShape(18.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Timer,
                            contentDescription = "Timer",
                            tint = if (timerSeconds < 30) DangerRed else SuccessGreen,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = formattedTime,
                            color = if (timerSeconds < 30) DangerRed else SuccessGreen,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            AnimatedContent(
                targetState = finished,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "quiz_screen_transition"
            ) { isFinished ->
                if (isFinished) {
                    // --- RESULTS / SCORE REPORT CARD CARD ---
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = HighContrastCard),
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.5.dp, PretibimbPrimary),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                                .testTag("scorecard_panel")
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Icon(Icons.Default.Celebration, contentDescription = "Congrats", tint = PretibimbPrimary, modifier = Modifier.size(64.dp))

                                Text(
                                    text = "परीक्षण पूर्ण हुआ! (Test Submitted)",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.White,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    text = resultMsg,
                                    fontSize = 15.sp,
                                    color = Color.LightGray,
                                    textAlign = TextAlign.Center
                                )

                                Divider(color = Color.DarkGray)

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text("प्राप्तांक (Your Score)", color = Color.Gray, fontSize = 11.sp)
                                        Text("$score", color = SuccessGreen, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                                    }

                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Text("कुल अंक (Max Marks)", color = Color.Gray, fontSize = 11.sp)
                                        Text("${currentQuiz.maxMarks}", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                                    }
                                }

                                Divider(color = Color.DarkGray)

                                Text(
                                    text = "सफलता का प्रतिबिंब - प्रतिबिम्ब एकेडमी",
                                    color = PretibimbPrimary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Button(
                                    onClick = { viewModel.cancelQuiz() },
                                    colors = ButtonDefaults.buttonColors(containerColor = PretibimbPrimary),
                                    shape = RoundedCornerShape(26.dp),
                                    modifier = Modifier.fillMaxWidth().height(48.dp).testTag("dismiss_scorecard")
                                ) {
                                    Text("डैशबोर्ड पर वापस जाएँ (Go Home)", color = Color.Black, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                } else {
                    // --- ACTIVE EXAM ENVIRONMENT ---
                    if (questions.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = PretibimbPrimary)
                        }
                    } else {
                        val currentQuestion = questions[currentIndex]
                        val userChoice = selectedAnswers[currentIndex]

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Question progress Indicator
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("प्रश्न ${currentIndex + 1} / ${questions.size}", color = PretibimbPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    Text("अंक: +10 / 0", color = Color.Gray, fontSize = 11.sp)
                                }
                                LinearProgressIndicator(
                                    progress = { (currentIndex + 1).toFloat() / questions.size },
                                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                                    color = PretibimbPrimary,
                                    trackColor = Color.DarkGray
                                )
                            }

                            // Question Title Card
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1E31)),
                                border = BorderStroke(1.dp, Color.DarkGray)
                            ) {
                                Text(
                                    text = currentQuestion.text,
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 24.sp,
                                    modifier = Modifier.padding(18.dp)
                                )
                            }

                            Text("सही विकल्प का चयन करें (Choose one option):", color = Color.LightGray, fontSize = 11.sp)

                            // Options List (0..3)
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                currentQuestion.options.forEachIndexed { optIndex, optionText ->
                                    val isSelected = userChoice == optIndex
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { viewModel.selectQuizAnswer(currentIndex, optIndex) }
                                            .testTag("option_${currentIndex}_$optIndex"),
                                        shape = RoundedCornerShape(10.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = if (isSelected) PretibimbPrimary.copy(alpha = 0.15f) else Color.Transparent
                                        ),
                                        border = BorderStroke(
                                            width = if (isSelected) 1.5.dp else 1.dp,
                                            color = if (isSelected) PretibimbPrimary else Color.DarkGray
                                        )
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(14.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(24.dp)
                                                    .border(
                                                        width = 1.dp,
                                                        color = if (isSelected) PretibimbPrimary else Color.Gray,
                                                        shape = CircleShape
                                                    )
                                                    .background(
                                                        color = if (isSelected) PretibimbPrimary else Color.Transparent,
                                                        shape = CircleShape
                                                    ),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = ('A' + optIndex).toString(),
                                                    color = if (isSelected) Color.Black else Color.Gray,
                                                    fontSize = 11.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }

                                            Spacer(modifier = Modifier.width(12.dp))

                                            Text(
                                                text = optionText,
                                                color = Color.White,
                                                fontSize = 14.sp,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(18.dp))

                            // --- BOTTOM ROW CONTROLS ---
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Previous Button
                                Button(
                                    onClick = { viewModel.prevQuizQuestion() },
                                    enabled = currentIndex > 0,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFF1E293B),
                                        contentColor = Color.White,
                                        disabledContainerColor = Color.DarkGray.copy(alpha = 0.3f)
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier.testTag("quiz_prev_button")
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.ArrowBackIos, contentDescription = null, modifier = Modifier.size(12.dp))
                                        Text("पीछे (Prev)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    }
                                }

                                // Submit Exam early trigger (Only shown if on final question or can be a persistent button)
                                if (currentIndex == questions.size - 1) {
                                    Button(
                                        onClick = { viewModel.submitQuizAnswers() },
                                        colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.testTag("quiz_submit_button")
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.Check, contentDescription = "Submit", tint = Color.Black)
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text("सबमिट (Submit)", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Black)
                                        }
                                    }
                                } else {
                                    // Next Button
                                    Button(
                                        onClick = { viewModel.nextQuizQuestion() },
                                        colors = ButtonDefaults.buttonColors(containerColor = PretibimbPrimary),
                                        shape = RoundedCornerShape(8.dp),
                                        modifier = Modifier.testTag("quiz_next_button")
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("आगे (Next)", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Icon(Icons.Default.ArrowForwardIos, contentDescription = null, tint = Color.Black, modifier = Modifier.size(12.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
