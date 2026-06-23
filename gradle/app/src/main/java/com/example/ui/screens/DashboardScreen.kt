package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.*
import com.example.ui.theme.*
import com.example.ui.viewmodel.AcademyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class DashboardTab {
    CLASSES, PDFs, TESTS, ADMIN_DESK
}

@Composable
fun DashboardScreen(
    viewModel: AcademyViewModel,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(DashboardTab.CLASSES) }
    val currentUser by viewModel.currentUser.collectAsState()
    val context = LocalContext.current

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                // Outer bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "PRETİ",
                                color = SlateTextMain,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black
                            )
                            Box(
                                modifier = Modifier
                                    .size(18.dp)
                                    .clip(CircleShape)
                                    .background(PretibimbPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = null, tint = Color.White, modifier = Modifier.size(10.dp))
                            }
                            Text(
                                text = "İMB ACADEMY",
                                color = PretibimbPrimary,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(
                            text = "स्वागत है, ${currentUser?.name ?: "छात्र"}",
                            color = SlateTextSecondary,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Logout Button
                    IconButton(
                        onClick = { viewModel.logout() },
                        modifier = Modifier
                            .background(Color(0xFFF1F5F9), CircleShape)
                            .size(36.dp)
                            .testTag("logout_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout icon",
                            tint = PretibimbPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        },
        bottomBar = {
            Column {
                Divider(color = Color(0xFFE2E8F0), thickness = 1.dp)
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = SlateTextSecondary,
                    windowInsets = WindowInsets.navigationBars,
                    tonalElevation = 0.dp
                ) {
                    // Tab 1: Classes
                    NavigationBarItem(
                        selected = selectedTab == DashboardTab.CLASSES,
                        onClick = { selectedTab = DashboardTab.CLASSES },
                        icon = { Icon(if (selectedTab == DashboardTab.CLASSES) Icons.Filled.VideoLibrary else Icons.Outlined.VideoLibrary, contentDescription = "कक्षाएँ") },
                        label = { Text("वीडियो क्लासेज", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PretibimbPrimary,
                            selectedTextColor = PretibimbPrimary,
                            indicatorColor = PretibimbPrimary.copy(alpha = 0.12f),
                            unselectedIconColor = SlateTextSecondary,
                            unselectedTextColor = SlateTextSecondary
                        ),
                        modifier = Modifier.testTag("nav_tab_classes")
                    )

                    // Tab 2: PDFs
                    NavigationBarItem(
                        selected = selectedTab == DashboardTab.PDFs,
                        onClick = { selectedTab = DashboardTab.PDFs },
                        icon = { Icon(if (selectedTab == DashboardTab.PDFs) Icons.Filled.MenuBook else Icons.Outlined.MenuBook, contentDescription = "नोट्स") },
                        label = { Text("नोट्स (PDF)", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PretibimbPrimary,
                            selectedTextColor = PretibimbPrimary,
                            indicatorColor = PretibimbPrimary.copy(alpha = 0.12f),
                            unselectedIconColor = SlateTextSecondary,
                            unselectedTextColor = SlateTextSecondary
                        ),
                        modifier = Modifier.testTag("nav_tab_pdfs")
                    )

                    // Tab 3: Tests
                    NavigationBarItem(
                        selected = selectedTab == DashboardTab.TESTS,
                        onClick = { selectedTab = DashboardTab.TESTS },
                        icon = { Icon(if (selectedTab == DashboardTab.TESTS) Icons.Filled.Quiz else Icons.Outlined.Quiz, contentDescription = "टेस्ट") },
                        label = { Text("टेस्ट सीरीज", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PretibimbPrimary,
                            selectedTextColor = PretibimbPrimary,
                            indicatorColor = PretibimbPrimary.copy(alpha = 0.12f),
                            unselectedIconColor = SlateTextSecondary,
                            unselectedTextColor = SlateTextSecondary
                        ),
                        modifier = Modifier.testTag("nav_tab_tests")
                    )

                    // Tab 4: Admin Desk (Owner portal - only visible if ADMIN)
                    if (currentUser?.role == "ADMIN") {
                        NavigationBarItem(
                            selected = selectedTab == DashboardTab.ADMIN_DESK,
                            onClick = { selectedTab = DashboardTab.ADMIN_DESK },
                            icon = { Icon(if (selectedTab == DashboardTab.ADMIN_DESK) Icons.Filled.AdminPanelSettings else Icons.Outlined.AdminPanelSettings, contentDescription = "मालिक पैनल") },
                            label = { Text("मालिक पैनल", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = PretibimbPrimary,
                                selectedTextColor = PretibimbPrimary,
                                indicatorColor = PretibimbPrimary.copy(alpha = 0.12f),
                                unselectedIconColor = SlateTextSecondary,
                                unselectedTextColor = SlateTextSecondary
                            ),
                            modifier = Modifier.testTag("nav_tab_admin")
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            when (selectedTab) {
                DashboardTab.CLASSES -> ClassesTabContent(viewModel)
                DashboardTab.PDFs -> PdfsTabContent(viewModel)
                DashboardTab.TESTS -> TestsTabContent(viewModel)
                DashboardTab.ADMIN_DESK -> AdminDeskContent(viewModel)
            }
        }
    }
}

// --- SHARED COMPONENT: HERO BANNER & CONTACTS ---
@Composable
fun HeroHeaderBanner() {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = HighContrastCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                // Generated Banner Image
                Image(
                    painter = painterResource(id = R.drawable.img_hero_banner),
                    contentDescription = "Pretibimb Virtual Classroom Canvas Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Dark Gradient overlay for text reading
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xCC000000))
                            )
                        )
                )

                // Banner overlays
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(PretibimbPrimary, RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text("सर्वश्रेष्ठ ऑनलाइन शिक्षा", color = Color.Black, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "UP Board / Competitions 2026",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Contact & Action Details area
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF8FAFC))
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.PhoneCallback, contentDescription = null, tint = PretibimbPrimary, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("संदेह निवारण हेल्पलाइन (Admission Help):", color = SlateTextSecondary, fontSize = 10.sp)
                    }
                    Text(
                        text = "7691021159",
                        color = SlateTextMain,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )
                }

                // Call Button
                Button(
                    onClick = {
                        val number = "7691021159"
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PretibimbPrimary, contentColor = Color.White),
                    shape = RoundedCornerShape(18.dp),
                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp),
                    modifier = Modifier.testTag("hotline_call_button")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Call, contentDescription = "Call", tint = Color.White, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("कॉल करें", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

// ================= TAB 1: CLASSES (वीडियो क्लासेज) =================
@Composable
fun ClassesTabContent(viewModel: AcademyViewModel) {
    val classesList by viewModel.classes.collectAsState()
    val isAdmin = viewModel.currentUser.collectAsState().value?.role == "ADMIN"
    val subjects = listOf("सभी विषय", "Physics", "Mathematics", "Science", "History")
    var selectedSubject by remember { mutableStateOf("सभी विषय") }

    // Active Simulated Video player dialog block
    var activeStreamingClass by remember { mutableStateOf<ClassItem?>(null) }

    val filteredList = if (selectedSubject == "सभी विषय") {
        classesList
    } else {
        classesList.filter { it.subject.equals(selectedSubject, ignoreCase = true) }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("classes_scroll_view"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            HeroHeaderBanner()
        }

        // Horizontal Subject Filter chips
        item {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(subjects) { sub ->
                    val isSelected = selectedSubject == sub
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedSubject = sub },
                        label = { Text(sub, color = if (isSelected) Color.Black else Color.Gray) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PretibimbPrimary,
                            selectedLabelColor = Color.Black
                        )
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "लाइव और रिकॉर्डेड लेक्चर्स (${filteredList.size})",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        if (filteredList.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(42.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.VideoCameraBack, contentDescription = null, modifier = Modifier.size(48.dp), tint = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("कोई लेक्चर उपलब्ध नहीं है", color = Color.Gray, fontSize = 14.sp)
                }
            }
        } else {
            items(filteredList) { cl ->
                ClassItemCard(
                    classItem = cl,
                    isAdmin = isAdmin,
                    onPlayClick = { activeStreamingClass = cl },
                    onDelete = { viewModel.removeClass(cl.id) },
                    onToggleLive = { viewModel.setLiveState(cl.id, !cl.isLiveNow) }
                )
            }
        }
    }

    // Interactive Video Player Simulator Dialog
    activeStreamingClass?.let { cls ->
        VideoPlayerSimulatorDialog(
            classItem = cls,
            onDismiss = { activeStreamingClass = null }
        )
    }
}

@Composable
fun ClassItemCard(
    classItem: ClassItem,
    isAdmin: Boolean,
    onPlayClick: () -> Unit,
    onDelete: () -> Unit,
    onToggleLive: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .clickable { onPlayClick() }
            .testTag("class_card_${classItem.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = if (classItem.isLiveNow) BorderStroke(1.5.dp, LiveRed) else BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Tags
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFEFF6FF), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(classItem.subject, color = PretibimbPrimary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }

                    if (classItem.isLiveNow) {
                        // Live Tag Pulsing look
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFFEF2F2), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .background(LiveRed, CircleShape)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("LIVE NOW", color = LiveRed, fontSize = 9.sp, fontWeight = FontWeight.Black)
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFF1F5F9), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(classItem.type, color = SlateTextSecondary, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Delete or Edit buttons for Admin/Owner
                if (isAdmin) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Button(
                            onClick = onToggleLive,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (classItem.isLiveNow) Color.Gray else LiveRed,
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.height(24.dp).testTag("class_toggle_live_${classItem.id}")
                        ) {
                            Text(if (classItem.isLiveNow) "End Live" else "Go Live", fontSize = 9.sp)
                        }

                        IconButton(
                            onClick = onDelete,
                            modifier = Modifier.size(24.dp).testTag("class_delete_${classItem.id}")
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red, modifier = Modifier.size(16.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = classItem.title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = SlateTextMain,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = classItem.description,
                fontSize = 12.sp,
                color = SlateTextSecondary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Instructor
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = InfoBlue, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(classItem.instructor, color = SlateTextSecondary, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                }

                // Schedule details
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Schedule, contentDescription = null, tint = SlateTextSecondary, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(classItem.liveDateTime, color = SlateTextSecondary, fontSize = 11.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = onPlayClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (classItem.isLiveNow) LiveRed else PretibimbPrimary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(38.dp).testTag("class_play_button_${classItem.id}")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Icon(
                        imageVector = if (classItem.isLiveNow) Icons.Default.LiveTv else Icons.Default.PlayCircleFilled,
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (classItem.isLiveNow) "लाइव क्लास ज्वाइन करें" else "क्लास देखें",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Interactive Live/Recorded Video stream with doubt sending
@Composable
fun VideoPlayerSimulatorDialog(
    classItem: ClassItem,
    onDismiss: () -> Unit
) {
    var isPlaying by remember { mutableStateOf(true) }
    var userMessage by remember { mutableStateOf("") }
    val chats = remember {
        mutableStateListOf(
            "रवि: सर, त्रिकोणमिति का फार्मूला फिर से बतायें",
            "नितेश: MR Sir Best Physics Teacher!! ⚡",
            "प्रिया: Class very helpful, Board exams 2026 preps!"
        )
    }

    val infiniteTransition = rememberInfiniteTransition(label = "wave_anim")
    val waveScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "wave_scale"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (classItem.isLiveNow) "🔴 लाइव स्ट्रीम" else "📺 रिकॉर्डेड क्लास",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    color = if (classItem.isLiveNow) Color.Red else PretibimbPrimary
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.LightGray)
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // MOCK VIDEO PLAYER CANVAS SCREEN
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Black)
                        .border(1.dp, Color.DarkGray, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    if (isPlaying) {
                        // Drawing video player visual vibes (waves representing speech/playing)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = if (classItem.isLiveNow) Icons.Default.LiveTv else Icons.Default.MovieFilter,
                                contentDescription = null,
                                tint = PretibimbPrimary,
                                modifier = Modifier
                                    .size(48.dp)
                                    .run { if (classItem.isLiveNow) scale(waveScale) else this }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                "लेक्चर चल रहा है... [03:42 / 45:00]",
                                fontSize = 11.sp,
                                color = Color.Green,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        IconButton(onClick = { isPlaying = true }) {
                            Icon(Icons.Default.PlayArrow, contentDescription = "Play icon", tint = Color.White, modifier = Modifier.size(52.dp))
                        }
                    }

                    // Play/Pause override overlay controls
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                    ) {
                        IconButton(
                            onClick = { isPlaying = !isPlaying },
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color(0x80000000), CircleShape)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = "PauseBtn",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

                Text(classItem.title, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 14.sp)
                Text("शिक्षक: ${classItem.instructor}", fontSize = 11.sp, color = PretibimbPrimary)

                Divider(color = Color.DarkGray)

                // DOUBT BOX & LIVE CHAT (FOR UTKARSH / HIGH INTERACTION EXPERIENCE)
                Text("💬 डाउट पूछें एवं लाइव चैट (Doubt Center):", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.SemiBold)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(86.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF07111E)),
                    border = BorderStroke(1.dp, Color.DarkGray)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(6.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        chats.forEach { chat ->
                            Text(chat, color = Color.LightGray, fontSize = 10.sp)
                        }
                    }
                }

                // Chat Input field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    OutlinedTextField(
                        value = userMessage,
                        onValueChange = { userMessage = it },
                        placeholder = { Text("शिक्षक से पूछें...", fontSize = 10.sp) },
                        modifier = Modifier.weight(1f).height(46.dp).testTag("live_chat_input"),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = PretibimbPrimary
                        )
                    )

                    IconButton(
                        onClick = {
                            if (userMessage.trim().isNotEmpty()) {
                                chats.add("मैं (संदेह): ${userMessage.trim()}")
                                userMessage = ""
                            }
                        },
                        modifier = Modifier
                            .background(PretibimbPrimary, CircleShape)
                            .size(36.dp)
                            .testTag("send_chat_button")
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Send Message", tint = Color.Black, modifier = Modifier.size(14.dp))
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("बंद करें (Close)", color = PretibimbPrimary)
            }
        }
    )
}

// ================= TAB 2: PDFs (ई-बुक्स और नोट्स) =================
@Composable
fun PdfsTabContent(viewModel: AcademyViewModel) {
    val pdfsList by viewModel.pdfs.collectAsState()
    val isAdmin = viewModel.currentUser.collectAsState().value?.role == "ADMIN"
    var activeViewingPdf by remember { mutableStateOf<PdfItem?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("pdfs_scroll_view"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            HeroHeaderBanner()
        }

        item {
            Text(
                text = "अध्ययन सामग्री और ई-बुक्स नोट्स",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        if (pdfsList.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(42.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.PictureAsPdf, contentDescription = null, modifier = Modifier.size(48.dp), tint = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("कोई PDF अध्ययन सामग्री अभी उपलब्ध नहीं है।", color = Color.Gray, fontSize = 14.sp)
                }
            }
        } else {
            items(pdfsList) { pdf ->
                PdfItemCard(
                    pdfItem = pdf,
                    isAdmin = isAdmin,
                    onOpenNote = { activeViewingPdf = pdf },
                    onDelete = { viewModel.removePdf(pdf.id) }
                )
            }
        }
    }

    // Custom simulated Document Note Reader
    activeViewingPdf?.let { pdf ->
        PdfReadingDialog(
            pdfItem = pdf,
            onDismiss = { activeViewingPdf = null }
        )
    }
}

@Composable
fun PdfItemCard(
    pdfItem: PdfItem,
    isAdmin: Boolean,
    onOpenNote: () -> Unit,
    onDelete: () -> Unit
) {
    var downloadProgress by remember { mutableStateOf(0f) }
    var isDownloading by remember { mutableStateOf(false) }
    var isDownloaded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .testTag("pdf_card_${pdfItem.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFE5F55).copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PictureAsPdf,
                            contentDescription = "PDF Icon",
                            tint = Color(0xFFFE5F55),
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFEFF6FF), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(pdfItem.subject, color = PretibimbPrimary, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(pdfItem.title, color = SlateTextMain, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }

                if (isAdmin) {
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(24.dp).testTag("pdf_delete_${pdfItem.id}")
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red, modifier = Modifier.size(16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(pdfItem.description, fontSize = 11.sp, color = SlateTextSecondary)
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.CollectionsBookmark, contentDescription = null, tint = SlateTextSecondary, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text("${pdfItem.pageCount} Pages", color = SlateTextSecondary, fontSize = 11.sp)
                    Spacer(modifier = Modifier.width(14.dp))
                    Icon(Icons.Outlined.SdStorage, contentDescription = null, tint = SlateTextSecondary, modifier = Modifier.size(12.dp))
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(pdfItem.fileSize, color = SlateTextSecondary, fontSize = 11.sp)
                }

                // Interactive Progress Bar trigger / Read book trigger
                if (!isDownloaded) {
                    if (isDownloading) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CircularProgressIndicator(
                                progress = { downloadProgress },
                                modifier = Modifier.size(18.dp),
                                color = PretibimbPrimary,
                                strokeWidth = 2.dp,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("${(downloadProgress * 100).toInt()}%", color = PretibimbPrimary, fontSize = 11.sp)
                        }
                    } else {
                        Button(
                            onClick = {
                                isDownloading = true
                                scope.launch {
                                    // Simulated smooth network download
                                    while (downloadProgress < 1.0f) {
                                        delay(150)
                                        downloadProgress += 0.1f
                                    }
                                    isDownloading = false
                                    isDownloaded = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFF6FF), contentColor = PretibimbPrimary),
                            modifier = Modifier.height(32.dp),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Download, contentDescription = null, tint = PretibimbPrimary, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("डाउनलोड", color = PretibimbPrimary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                } else {
                    Button(
                        onClick = onOpenNote,
                        colors = ButtonDefaults.buttonColors(containerColor = PretibimbPrimary, contentColor = Color.White),
                        modifier = Modifier.height(32.dp).testTag("pdf_read_button_${pdfItem.id}"),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.MenuBook, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("अभी पढ़ें", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

// Custom book document simulator dialog with notes view
@Composable
fun PdfReadingDialog(
    pdfItem: PdfItem,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(pdfItem.title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = PretibimbPrimary, maxLines = 1, overflow = TextOverflow.Ellipsis)
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.LightGray)
                }
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 380.dp)
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFF07111E))
                    .padding(14.dp)
                    .border(0.5.dp, Color.Gray, RoundedCornerShape(4.dp)),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.background(Color.Red, RoundedCornerShape(2.dp)).padding(horizontal = 4.dp, vertical = 1.dp)) {
                        Text("E-NOTES", color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.Black)
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Pretibimb Academy Digital Library 2026", color = Color.Gray, fontSize = 9.sp)
                }

                Divider(color = Color.DarkGray)

                // Educational Formulas matching physics / mathematics notes
                Text("📑 विषय: " + pdfItem.subject, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)

                Text(
                    text = "1. प्रमुख परिभाषाएँ (Key Definitions):\n" +
                            "इस प्रकरण के अंतर्गत सभी मूलभूत संकल्पनाओं को रेखांकित किया गया है। परीक्षा की दृष्टि से निम्नलिखित नियम अत्यंत महत्वपूर्ण हैं:\n" +
                            "• गति के समीकरण (Equations of Motion):\n" +
                            "  v = u + at\n" +
                            "  s = ut + ½at²\n" +
                            "  v² = u² + 2as\n\n" +
                            "• कूलॉम का नियम (Coulomb's Law):\n" +
                            "  F = k · (q₁q₂) / r²\n\n" +
                            "• आयलर का सिद्धांत (Euler's Trigonometry Formula):\n" +
                            "  e^(iθ) = cos(θ) + i·sin(θ)\n",
                    color = Color.LightGray,
                    fontSize = 11.sp,
                    lineHeight = 18.sp
                )

                Text(
                    text = "2. अभ्यास प्रश्न (Practice Problem Sets):\n" +
                            "यदि विद्युत क्षेत्र की तीव्रता E = 100 V/m है, तो 2 सेमी की दूरी पर विभव प्रवणता (potential gradient) ज्ञात कीजिए।\n" +
                            "उत्तर संकेत: विभव प्रवणता = dV/dx = -E = -100 V/m।\n",
                    color = Color.LightGray,
                    fontSize = 11.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "[ अंत में: Pretibimb Academy नोट्स संकलन 7691021159 ]",
                    color = PretibimbPrimary,
                    fontSize = 9.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("पढ़ना बंद करें (Close)", color = PretibimbPrimary)
            }
        }
    )
}

// ================= TAB 3: MCQ TESTS (टेस्ट सीरीज) =================
@Composable
fun TestsTabContent(viewModel: AcademyViewModel) {
    val quizList by viewModel.quizzes.collectAsState()
    val attempts by viewModel.userAttempts.collectAsState()
    val isAdmin = viewModel.currentUser.collectAsState().value?.role == "ADMIN"

    // Calculate score metrics
    val averageScore = if (attempts.isNotEmpty()) {
        attempts.map { (it.score.toFloat() / it.maxMarks) * 100 }.average().toInt()
    } else 0

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("tests_scroll_view"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            HeroHeaderBanner()
        }

        // Student stats card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = HighContrastCard),
                border = BorderStroke(1.dp, PretibimbPrimary.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("📊 आपकी प्रगति (Performance Log):", color = Color.Gray, fontSize = 11.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("औसत प्राप्तांक प्रतिशत: $averageScore%", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                        Text("कुल दिए गए टेस्ट: ${attempts.size}", color = Color.LightGray, fontSize = 12.sp)
                    }

                    Box(
                        modifier = Modifier
                            .background(PretibimbPrimary, CircleShape)
                            .size(52.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(attempts.size.toString(), color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text("TESTS", color = Color.Black, fontSize = 8.sp, fontWeight = FontWeight.Black)
                        }
                    }
                }
            }
        }

        // Test history
        if (attempts.isNotEmpty()) {
            item {
                Text(
                    text = "गत परीक्षण परिणाम (Past Results):",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            items(attempts) { att ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(att.quizTitle, color = SlateTextMain, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            Text("समय: ${java.text.SimpleDateFormat("MMM dd, yyyy HH:mm").format(att.timestamp)}", color = SlateTextSecondary, fontSize = 10.sp)
                        }
                        Box(
                            modifier = Modifier
                                .background(SuccessGreen.copy(alpha = 0.12f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text("${att.score} / ${att.maxMarks} Marks", color = SuccessGreen, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "सक्रिय लाइव टेस्ट सीरीज (Active Quizzes)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }

        if (quizList.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(42.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Default.Quiz, contentDescription = null, modifier = Modifier.size(48.dp), tint = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("कोई टेस्ट सीरीज अभी उपलब्ध नहीं है।", color = Color.Gray, fontSize = 14.sp)
                }
            }
        } else {
            items(quizList) { quiz ->
                QuizItemCard(
                    quizItem = quiz,
                    isAdmin = isAdmin,
                    onStartQuiz = { viewModel.startQuiz(quiz) },
                    onDelete = { viewModel.removeQuiz(quiz.id) }
                )
            }
        }
    }
}

@Composable
fun QuizItemCard(
    quizItem: QuizItem,
    isAdmin: Boolean,
    onStartQuiz: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .testTag("quiz_card_${quizItem.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFDEF7EC), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(quizItem.subject, color = SuccessGreen, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(quizItem.title, color = SlateTextMain, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }

                if (isAdmin) {
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(24.dp).testTag("quiz_delete_${quizItem.id}")
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red, modifier = Modifier.size(16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Timer, contentDescription = null, tint = SlateTextSecondary, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("${quizItem.durationMinutes} मिनट", color = SlateTextSecondary, fontSize = 12.sp)
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Assignment, contentDescription = null, tint = SlateTextSecondary, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("${quizItem.totalQuestions} प्रश्न", color = SlateTextSecondary, fontSize = 12.sp)
                    }
                }

                Box(
                    modifier = Modifier
                        .background(Color(0xFFEFF6FF), RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text("पूर्णांक: ${quizItem.maxMarks}", color = PretibimbPrimary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onStartQuiz,
                colors = ButtonDefaults.buttonColors(containerColor = PretibimbPrimary, contentColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth().height(38.dp).testTag("quiz_start_button_${quizItem.id}")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Icon(Icons.Default.ArrowRightAlt, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("टेस्ट सीरीज़ शुरू करें (Start Test)", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// ================= TAB 4: OWNER/ADMIN PORTAL (मालिक डेस्क) =================
@Composable
fun AdminDeskContent(viewModel: AcademyViewModel) {
    val context = LocalContext.current
    var isClassExpanded by remember { mutableStateOf(false) }
    var isPdfExpanded by remember { mutableStateOf(false) }
    var isQuizExpanded by remember { mutableStateOf(false) }

    // Forms states - Class
    var classTitle by remember { mutableStateOf("") }
    var classSubject by remember { mutableStateOf("Physics") }
    var classDesc by remember { mutableStateOf("") }
    var classInstructor by remember { mutableStateOf("") }
    var classType by remember { mutableStateOf("LIVE") }
    var classVideoId by remember { mutableStateOf("") }
    var classScheduleDesc by remember { mutableStateOf("") }
    var classIsLiveImmediately by remember { mutableStateOf(true) }

    // Forms states - PDF
    var pdfTitle by remember { mutableStateOf("") }
    var pdfSubject by remember { mutableStateOf("Physics") }
    var pdfDesc by remember { mutableStateOf("") }
    var pdfPages by remember { mutableStateOf("") }
    var pdfSize by remember { mutableStateOf("") }

    // Forms states - Quiz Composer
    var quizTitle by remember { mutableStateOf("") }
    var quizSubject by remember { mutableStateOf("Physics") }
    var quizDuration by remember { mutableStateOf("10") }

    // Sub questions compose states (Allows admin to add 1 quiz question at a time)
    val composedQuestions = remember { mutableStateListOf<QuizQuestion>() }
    var qText by remember { mutableStateOf("") }
    var qOpt1 by remember { mutableStateOf("") }
    var qOpt2 by remember { mutableStateOf("") }
    var qOpt3 by remember { mutableStateOf("") }
    var qOpt4 by remember { mutableStateOf("") }
    var qCorrectIdx by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("admin_scroll_view"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = PremiumDarkSchemeCard),
                border = BorderStroke(1.5.dp, PretibimbPrimary)
            ) {
                Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.AdminPanelSettings, contentDescription = null, modifier = Modifier.size(42.dp), tint = PretibimbPrimary)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("🔐 Pretibimb Academy मालिक पोर्टल", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text("यहाँ से आप लाइव क्लासेज, पीडीएफ नोट्स, व टेस्ट सीरीज़ अपलोड कर सकते हैं।", color = Color.LightGray, fontSize = 11.sp, lineHeight = 16.sp)
                    }
                }
            }
        }

        // Expanded Card 1: Add Classes
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = HighContrastCard),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(0.5.dp, if (isClassExpanded) PretibimbPrimary else Color.DarkGray)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isClassExpanded = !isClassExpanded },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.VideoCameraBack, contentDescription = null, tint = PretibimbPrimary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("1. नई वीडियो क्लास जोड़ें / लाइव करें", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                        Icon(imageVector = if (isClassExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color.Gray)
                    }

                    if (isClassExpanded) {
                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = classTitle,
                            onValueChange = { classTitle = it },
                            label = { Text("क्लास का शीर्षक (Title)", color = Color.Gray) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                            modifier = Modifier.fillMaxWidth().testTag("admin_class_title")
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Subject Select input
                            OutlinedTextField(
                                value = classSubject,
                                onValueChange = { classSubject = it },
                                label = { Text("विषय (Subject)", color = Color.Gray) },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                                modifier = Modifier.weight(1f).testTag("admin_class_subject")
                            )

                            // Instructor name
                            OutlinedTextField(
                                value = classInstructor,
                                onValueChange = { classInstructor = it },
                                label = { Text("शिक्षक (Instructor)", color = Color.Gray) },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                                modifier = Modifier.weight(1f).testTag("admin_class_instructor")
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = classDesc,
                            onValueChange = { classDesc = it },
                            label = { Text("विवरण भाषण (Description)", color = Color.Gray) },
                            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                            modifier = Modifier.fillMaxWidth().height(80.dp).testTag("admin_class_desc")
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Class type option toggle
                            Column(modifier = Modifier.weight(1f)) {
                                Text("प्रसारण प्रकार (Type):", color = Color.Gray, fontSize = 11.sp)
                                Row {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        RadioButton(selected = classType == "LIVE", onClick = { classType = "LIVE" }, colors = RadioButtonDefaults.colors(selectedColor = PretibimbPrimary))
                                        Text("LIVE", color = Color.White, fontSize = 11.sp)
                                    }
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        RadioButton(selected = classType == "RECORDED", onClick = { classType = "RECORDED" }, colors = RadioButtonDefaults.colors(selectedColor = PretibimbPrimary))
                                        Text("RECORDED", color = Color.White, fontSize = 11.sp)
                                    }
                                }
                            }

                            // YouTube ID or dummy token
                            OutlinedTextField(
                                value = classVideoId,
                                onValueChange = { classVideoId = it },
                                label = { Text("वीडियो टोकन / ID (Optional)", color = Color.Gray) },
                                singleLine = true,
                                placeholder = { Text("e.g. dQw4w9WgXcQ", fontSize = 11.sp) },
                                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                                modifier = Modifier.weight(1f).testTag("admin_class_videoid")
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = classScheduleDesc,
                            onValueChange = { classScheduleDesc = it },
                            label = { Text("समय / दिनांक विवरण (e.g. Live: Today 4 PM)", color = Color.Gray) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                            modifier = Modifier.fillMaxWidth().testTag("admin_class_time")
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = classIsLiveImmediately, onCheckedChange = { classIsLiveImmediately = it }, colors = CheckboxDefaults.colors(checkedColor = PretibimbPrimary))
                            Text("अभी क्लास लाइव प्रसारित करें! (Go Live Immediately)", color = Color.White, fontSize = 12.sp)
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                if (classTitle.trim().isEmpty() || classInstructor.trim().isEmpty()) {
                                    Toast.makeText(context, "कृपया शीर्षक और शिक्षक का नाम दर्ज करें!", Toast.LENGTH_SHORT).show()
                                } else {
                                    viewModel.publishClass(
                                        title = classTitle.trim(),
                                        subject = classSubject.trim(),
                                        desc = if (classDesc.trim().isEmpty()) "Pretibimb Online Smart Classroom Lecture." else classDesc.trim(),
                                        instructor = classInstructor.trim(),
                                        type = classType,
                                        videoId = classVideoId,
                                        scheduleDesc = if (classScheduleDesc.trim().isEmpty()) "Live Streams" else classScheduleDesc.trim(),
                                        isLive = classIsLiveImmediately
                                    )
                                    Toast.makeText(context, "सफलतापूर्वक क्लास जोड़ी गयी!", Toast.LENGTH_SHORT).show()
                                    // Reset fields
                                    classTitle = ""
                                    classDesc = ""
                                    classInstructor = ""
                                    classVideoId = ""
                                    classScheduleDesc = ""
                                    isClassExpanded = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PretibimbPrimary),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().testTag("admin_class_submit")
                        ) {
                            Text("क्लास प्रकाशित करें (Publish Lecture)", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Expanded Card 2: Add PDFs Note
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = HighContrastCard),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(0.5.dp, if (isPdfExpanded) PretibimbPrimary else Color.DarkGray)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isPdfExpanded = !isPdfExpanded },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.PictureAsPdf, contentDescription = null, tint = PretibimbPrimary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("2. नया ई-बुक नोट्स (PDF) जोड़ें", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                        Icon(imageVector = if (isPdfExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color.Gray)
                    }

                    if (isPdfExpanded) {
                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = pdfTitle,
                            onValueChange = { pdfTitle = it },
                            label = { Text("PDF शीर्षक (Title)", color = Color.Gray) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                            modifier = Modifier.fillMaxWidth().testTag("admin_pdf_title")
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Subject Select input
                            OutlinedTextField(
                                value = pdfSubject,
                                onValueChange = { pdfSubject = it },
                                label = { Text("विषय (Subject)", color = Color.Gray) },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                                modifier = Modifier.weight(1f).testTag("admin_pdf_subject")
                            )

                            // Page counts
                            OutlinedTextField(
                                value = pdfPages,
                                onValueChange = { pdfPages = it },
                                label = { Text("पृष्ठ संख्या (Pages Count)", color = Color.Gray) },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                                modifier = Modifier.weight(1f).testTag("admin_pdf_pages")
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = pdfDesc,
                            onValueChange = { pdfDesc = it },
                            label = { Text("नोट्स विवरण (Description)", color = Color.Gray) },
                            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                            modifier = Modifier.fillMaxWidth().height(80.dp).testTag("admin_pdf_desc")
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = pdfSize,
                            onValueChange = { pdfSize = it },
                            label = { Text("फाइल साइज विवरण (e.g. 2.4 MB)", color = Color.Gray) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                            modifier = Modifier.fillMaxWidth().testTag("admin_pdf_size")
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = {
                                val pCount = pdfPages.toIntOrNull() ?: 1
                                if (pdfTitle.trim().isEmpty() || pdfSubject.trim().isEmpty()) {
                                    Toast.makeText(context, "कृपया शीर्षक और विषय दर्ज करें!", Toast.LENGTH_SHORT).show()
                                } else {
                                    viewModel.publishPdf(
                                        title = pdfTitle.trim(),
                                        subject = pdfSubject.trim(),
                                        desc = if (pdfDesc.trim().isEmpty()) "Pretibimb Digital Classroom Handwritten PDF Notes." else pdfDesc.trim(),
                                        pages = pCount,
                                        size = if (pdfSize.trim().isEmpty()) "1.2 MB" else pdfSize.trim()
                                    )
                                    Toast.makeText(context, "सफलतापूर्वक नोट्स PDF प्रकाशित की गयी!", Toast.LENGTH_SHORT).show()
                                    // Reset fields
                                    pdfTitle = ""
                                    pdfDesc = ""
                                    pdfPages = ""
                                    pdfSize = ""
                                    isPdfExpanded = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PretibimbPrimary),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().testTag("admin_pdf_submit")
                        ) {
                            Text("पीडीएफ प्रकाशित करें (Publish Study Material)", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Expanded Card 3: Compose Quiz Test
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = HighContrastCard),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(0.5.dp, if (isQuizExpanded) PretibimbPrimary else Color.DarkGray)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isQuizExpanded = !isQuizExpanded },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Quiz, contentDescription = null, tint = PretibimbPrimary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("3. नया टेस्ट सीरीज (Quiz) कम्पोज़ करें", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        }
                        Icon(imageVector = if (isQuizExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, contentDescription = null, tint = Color.Gray)
                    }

                    if (isQuizExpanded) {
                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = quizTitle,
                            onValueChange = { quizTitle = it },
                            label = { Text("टेस्ट सीरीज का शीर्षक (Test Title)", color = Color.Gray) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                            modifier = Modifier.fillMaxWidth().testTag("admin_quiz_title")
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Subject Select input
                            OutlinedTextField(
                                value = quizSubject,
                                onValueChange = { quizSubject = it },
                                label = { Text("विषय (Subject)", color = Color.Gray) },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                                modifier = Modifier.weight(1f).testTag("admin_quiz_subject")
                            )

                            // Quiz timer duration
                            OutlinedTextField(
                                value = quizDuration,
                                onValueChange = { quizDuration = it },
                                label = { Text("समय सीमा (मिनट)", color = Color.Gray) },
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                                modifier = Modifier.weight(1f).testTag("admin_quiz_duration")
                            )
                        }

                        // Compiled Questions summary indicator
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF0F1E31), RoundedCornerShape(8.dp))
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(10.dp)
                        ) {
                            Column {
                                Text("📝 तैयार प्रश्न संकलन (Questions Compiled): ${composedQuestions.size}", color = PretibimbPrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                if (composedQuestions.isEmpty()) {
                                    Text("नीचे बने फॉर्म से कम से कम 1 प्रश्न जोड़ें।", color = Color.Gray, fontSize = 10.sp)
                                } else {
                                    composedQuestions.forEachIndexed { num, question ->
                                        Text("${num + 1}. ${question.text} (${question.options[question.correctAnswerIndex]})", color = Color.LightGray, fontSize = 10.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                    }
                                }
                            }
                        }

                        // Mini form to append questions
                        Spacer(modifier = Modifier.height(14.dp))
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF0D1B2A)),
                            border = BorderStroke(1.dp, Color.Gray),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text("➕ प्रश्न लिखें (Add Question Form):", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)

                                OutlinedTextField(
                                    value = qText,
                                    onValueChange = { qText = it },
                                    label = { Text("प्रश्न का कथ्य (Question Text)", color = Color.Gray) },
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = PretibimbPrimary),
                                    modifier = Modifier.fillMaxWidth().testTag("admin_q_text")
                                )

                                OutlinedTextField(
                                    value = qOpt1,
                                    onValueChange = { qOpt1 = it },
                                    label = { Text("विकल्प A (Option 1)", color = Color.Gray) },
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = Color.Gray),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                OutlinedTextField(
                                    value = qOpt2,
                                    onValueChange = { qOpt2 = it },
                                    label = { Text("विकल्प B (Option 2)", color = Color.Gray) },
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = Color.Gray),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                OutlinedTextField(
                                    value = qOpt3,
                                    onValueChange = { qOpt3 = it },
                                    label = { Text("विकल्प C (Option 3)", color = Color.Gray) },
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = Color.Gray),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                OutlinedTextField(
                                    value = qOpt4,
                                    onValueChange = { qOpt4 = it },
                                    label = { Text("विकल्प D (Option 4)", color = Color.Gray) },
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White, focusedBorderColor = Color.Gray),
                                    modifier = Modifier.fillMaxWidth()
                                )

                                // Correct Option Index
                                Text("सही राष्ट्रीय विकल्प (Correct Option):", color = Color.Gray, fontSize = 11.sp)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    listOf("A", "B", "C", "D").forEachIndexed { index, title ->
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            RadioButton(selected = qCorrectIdx == index, onClick = { qCorrectIdx = index }, colors = RadioButtonDefaults.colors(selectedColor = PretibimbPrimary))
                                            Text(title, color = Color.White, fontSize = 11.sp)
                                        }
                                    }
                                }

                                Button(
                                    onClick = {
                                        if (qText.trim().isEmpty() || qOpt1.trim().isEmpty() || qOpt2.trim().isEmpty()) {
                                            Toast.makeText(context, "कृपया प्रश्न और कम से कम 2 विकल्प लिखें!", Toast.LENGTH_SHORT).show()
                                        } else {
                                            val options = listOf(
                                                qOpt1.trim(),
                                                qOpt2.trim(),
                                                if (qOpt3.trim().isEmpty()) "Option C" else qOpt3.trim(),
                                                if (qOpt4.trim().isEmpty()) "Option D" else qOpt4.trim()
                                            )
                                            composedQuestions.add(
                                                QuizQuestion(
                                                    id = composedQuestions.size + 1,
                                                    text = qText.trim(),
                                                    options = options,
                                                    correctAnswerIndex = qCorrectIdx
                                                )
                                            )
                                            Toast.makeText(context, "प्रश्न संकलन में जोड़ा गया!", Toast.LENGTH_SHORT).show()
                                            // Reset question mini fields
                                            qText = ""
                                            qOpt1 = ""
                                            qOpt2 = ""
                                            qOpt3 = ""
                                            qOpt4 = ""
                                            qCorrectIdx = 0
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                                    contentPadding = PaddingValues(horizontal = 14.dp, vertical = 2.dp),
                                    modifier = Modifier.fillMaxWidth().height(28.dp).testTag("admin_q_add")
                                ) {
                                    Text("प्रश्न जोड़ें (Add Question)", color = Color.Black, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = {
                                val durationMins = quizDuration.toIntOrNull() ?: 10
                                if (quizTitle.trim().isEmpty() || composedQuestions.isEmpty()) {
                                    Toast.makeText(context, "कृपया टेस्ट का नाम लिखें और कम से कम 1 प्रश्न जोड़ें!", Toast.LENGTH_SHORT).show()
                                } else {
                                    viewModel.publishQuiz(
                                        title = quizTitle.trim(),
                                        subject = quizSubject.trim(),
                                        duration = durationMins,
                                        questions = composedQuestions.toList()
                                    )
                                    Toast.makeText(context, "सफलतापूर्वक टेस्ट सीरीज प्रकाशित की गयी!", Toast.LENGTH_SHORT).show()
                                    // Reset fields
                                    quizTitle = ""
                                    composedQuestions.clear()
                                    isQuizExpanded = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PretibimbPrimary),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth().testTag("admin_quiz_submit")
                        ) {
                            Text("टेस्ट सीरीज प्रकाशित करें (Publish Test Series)", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

val PremiumDarkSchemeCard = Color(0xFF14243A)
