package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.AcademyViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AuthScreen(
    viewModel: AcademyViewModel,
    modifier: Modifier = Modifier
) {
    var isLoginMode by remember { mutableStateOf(true) }
    var passwordVisible by remember { mutableStateOf(false) }

    val loginMobile by viewModel.loginMobile.collectAsState()
    val loginPassword by viewModel.loginPassword.collectAsState()
    val authError by viewModel.authError.collectAsState()

    val regName by viewModel.regName.collectAsState()
    val regMobile by viewModel.regMobile.collectAsState()
    val regPassword by viewModel.regPassword.collectAsState()
    val regSuccessMessage by viewModel.regSuccessMesssage.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(28.dp))

            // --- Pretibimb Academy Logo Display Card ---
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(24.dp))
                    .padding(horizontal = 24.dp, vertical = 18.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "PRETİ",
                            color = SlateTextMain,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black,
                            style = MaterialTheme.typography.headlineLarge
                        )
                        // Blue Circular dot wrapper
                        Box(
                            modifier = Modifier
                                .run { size(28.dp) }
                                .clip(RoundedCornerShape(14.dp))
                                .background(PretibimbPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "b-pen",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text(
                            text = "İMB",
                            color = SlateTextMain,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                    Text(
                        text = "ACADEMY",
                        color = PretibimbAdminPrimary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 4.sp
                    )
                }
            }

            Text(
                text = "प्रतिबिंब एकेडमी - शिक्षा का नया सवेरा",
                color = PretibimbPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Mode Selector
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0))
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    Button(
                        onClick = { isLoginMode = true },
                        modifier = Modifier.weight(1f).testTag("tab_login"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isLoginMode) PretibimbPrimary else Color.Transparent,
                            contentColor = if (isLoginMode) Color.White else SlateTextSecondary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        elevation = null
                    ) {
                        Text("लॉगिन करें", fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { isLoginMode = false },
                        modifier = Modifier.weight(1f).testTag("tab_register"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!isLoginMode) PretibimbPrimary else Color.Transparent,
                            contentColor = if (!isLoginMode) Color.White else SlateTextSecondary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        elevation = null
                    ) {
                        Text("पंजीकरण", fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Forms Layout with animation
            AnimatedContent(
                targetState = isLoginMode,
                transitionSpec = {
                    fadeIn() togetherWith fadeOut()
                },
                label = "auth_form_transition"
            ) { loginMode ->
                if (loginMode) {
                    // LOGIN MODE
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "अकाउंट लॉगिन",
                            color = SlateTextMain,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // Mobile number Input
                        OutlinedTextField(
                            value = loginMobile,
                            onValueChange = { viewModel.loginMobile.value = it },
                            label = { Text("मोबाइल नंबर (10 अंक)", color = SlateTextSecondary) },
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "PhoneIcon", tint = PretibimbPrimary) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = SlateTextMain,
                                unfocusedTextColor = SlateTextMain,
                                focusedBorderColor = PretibimbPrimary,
                                unfocusedBorderColor = Color(0xFFE2E8F0),
                                cursorColor = PretibimbPrimary,
                                focusedLabelColor = PretibimbPrimary,
                                unfocusedLabelColor = SlateTextSecondary
                            ),
                            modifier = Modifier.fillMaxWidth().testTag("login_mobile_input")
                        )

                        // Password Input
                        OutlinedTextField(
                            value = loginPassword,
                            onValueChange = { viewModel.loginPassword.value = it },
                            label = { Text("पासवर्ड", color = SlateTextSecondary) },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "LockIcon", tint = PretibimbPrimary) },
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = "Toggle password visibility",
                                        tint = PretibimbPrimary
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = SlateTextMain,
                                unfocusedTextColor = SlateTextMain,
                                focusedBorderColor = PretibimbPrimary,
                                unfocusedBorderColor = Color(0xFFE2E8F0),
                                cursorColor = PretibimbPrimary,
                                focusedLabelColor = PretibimbPrimary,
                                unfocusedLabelColor = SlateTextSecondary
                            ),
                            modifier = Modifier.fillMaxWidth().testTag("login_password_input")
                        )

                        // Error panel
                        authError?.let { err ->
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF2F2)),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFFCA5A5)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.ErrorOutline, contentDescription = "Error", tint = Color.Red)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(err, color = Color.Red, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                }
                            }
                        }

                        // Submit Button
                        Button(
                            onClick = { viewModel.login() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("login_submit_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = PretibimbPrimary, contentColor = Color.White),
                            shape = RoundedCornerShape(26.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("प्रवेश करें (Login)", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(Icons.Default.Login, contentDescription = "Login", tint = Color.White)
                            }
                        }

                        // PRE-PACKAGED DEMO CREDENTIALS SHORTCUTS (EXTREMELY USEFUL FOR EXAMINERS)
                        Divider(color = Color(0xFFE2E8F0), thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                        Text(
                            text = "💡 क्विक टेस्टिंग के लिए क्रेडेंशियल्स:",
                            color = PretibimbPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // User button
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        viewModel.loginMobile.value = "9876543210"
                                        viewModel.loginPassword.value = "user"
                                    }
                                    .testTag("fill_student_login"),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFBFDBFE))
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(Icons.Default.School, contentDescription = "User", tint = PretibimbPrimary)
                                    Text("छात्र (Student)", color = SlateTextMain, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    Text("9876543210\npass: user", color = SlateTextSecondary, fontSize = 10.sp, textAlign = TextAlign.Center)
                                }
                            }

                            // Owner / Admin button
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        viewModel.loginMobile.value = "7691021159"
                                        viewModel.loginPassword.value = "owner"
                                    }
                                    .testTag("fill_admin_login"),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7ED)),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFFFEDD5))
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Icon(Icons.Default.AdminPanelSettings, contentDescription = "Admin", tint = Color(0xFFEA580C))
                                    Text("मालिक (Owner)", color = Color(0xFFEA580C), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                    Text("7691021159\npass: owner", color = SlateTextSecondary, fontSize = 10.sp, textAlign = TextAlign.Center)
                                }
                            }
                        }
                    }
                } else {
                    // REGISTRATION MODE
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "नया स्टूडेंट पंजीकरण (Register)",
                            color = SlateTextMain,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        // Name field
                        OutlinedTextField(
                            value = regName,
                            onValueChange = { viewModel.regName.value = it },
                            label = { Text("पूरा नाम बताएं (Full Name)", color = SlateTextSecondary) },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Person", tint = PretibimbPrimary) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = SlateTextMain,
                                unfocusedTextColor = SlateTextMain,
                                focusedBorderColor = PretibimbPrimary,
                                unfocusedBorderColor = Color(0xFFE2E8F0),
                                cursorColor = PretibimbPrimary,
                                focusedLabelColor = PretibimbPrimary,
                                unfocusedLabelColor = SlateTextSecondary
                            ),
                            modifier = Modifier.fillMaxWidth().testTag("register_name_input")
                        )

                        // Phone field
                        OutlinedTextField(
                            value = regMobile,
                            onValueChange = { viewModel.regMobile.value = it },
                            label = { Text("मोबाइल नंबर (10 अंक)", color = SlateTextSecondary) },
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "Phone", tint = PretibimbPrimary) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = SlateTextMain,
                                unfocusedTextColor = SlateTextMain,
                                focusedBorderColor = PretibimbPrimary,
                                unfocusedBorderColor = Color(0xFFE2E8F0),
                                cursorColor = PretibimbPrimary,
                                focusedLabelColor = PretibimbPrimary,
                                unfocusedLabelColor = SlateTextSecondary
                            ),
                            modifier = Modifier.fillMaxWidth().testTag("register_mobile_input")
                        )

                        // Password field
                        OutlinedTextField(
                            value = regPassword,
                            onValueChange = { viewModel.regPassword.value = it },
                            label = { Text("नया सुरक्षित पासवर्ड (Password)", color = SlateTextSecondary) },
                            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock", tint = PretibimbPrimary) },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = SlateTextMain,
                                unfocusedTextColor = SlateTextMain,
                                focusedBorderColor = PretibimbPrimary,
                                unfocusedBorderColor = Color(0xFFE2E8F0),
                                cursorColor = PretibimbPrimary,
                                focusedLabelColor = PretibimbPrimary,
                                unfocusedLabelColor = SlateTextSecondary
                            ),
                            modifier = Modifier.fillMaxWidth().testTag("register_password_input")
                        )

                        // Success Message Panel
                        regSuccessMessage?.let { msg ->
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFD1FAE5)),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFA7F3D0)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.CheckCircleOutline, contentDescription = "Success", tint = SuccessGreen)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(msg, color = SuccessGreen, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                }
                            }
                        }

                        // Error message
                        authError?.let { err ->
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF2F2)),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, Color(0xFFFCA5A5)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.ErrorOutline, contentDescription = "Error", tint = Color.Red)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(err, color = Color.Red, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                                }
                            }
                        }

                        // Submit Button
                        Button(
                            onClick = { viewModel.register() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("register_submit_button"),
                            colors = ButtonDefaults.buttonColors(containerColor = PretibimbPrimary, contentColor = Color.White),
                            shape = RoundedCornerShape(26.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("अकाउंट बनाएं (Sign Up)", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(Icons.Default.Face, contentDescription = "SignUp", tint = Color.White)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "संपर्क सूत्र: 7691021159\nPretibimb Academy © 2026",
                color = SlateTextSecondary,
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}
