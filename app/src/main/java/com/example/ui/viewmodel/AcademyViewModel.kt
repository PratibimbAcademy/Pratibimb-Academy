package com.example.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.model.*
import com.example.data.repository.AcademyRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AcademyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AcademyRepository

    // Current session user
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    // Auth fields
    val loginMobile = MutableStateFlow("")
    val loginPassword = MutableStateFlow("")
    val authError = MutableStateFlow<String?>(null)

    // Registration fields
    val regName = MutableStateFlow("")
    val regMobile = MutableStateFlow("")
    val regPassword = MutableStateFlow("")
    val regRole = MutableStateFlow("USER") // ADMIN or USER
    val regSuccessMesssage = MutableStateFlow<String?>(null)

    // Database flows
    val classes: StateFlow<List<ClassItem>>
    val pdfs: StateFlow<List<PdfItem>>
    val quizzes: StateFlow<List<QuizItem>>

    // User's quiz scores/attempts history
    private val _userAttempts = MutableStateFlow<List<QuizAttempt>>(emptyList())
    val userAttempts: StateFlow<List<QuizAttempt>> = _userAttempts.asStateFlow()

    // --- Quiz Environment States ---
    private val _activeQuiz = MutableStateFlow<QuizItem?>(null)
    val activeQuiz: StateFlow<QuizItem?> = _activeQuiz.asStateFlow()

    private val _quizQuestions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val quizQuestions: StateFlow<List<QuizQuestion>> = _quizQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    // Map of logical question index to selected option index (0..3)
    private val _selectedAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val selectedAnswers: StateFlow<Map<Int, Int>> = _selectedAnswers.asStateFlow()

    private val _quizTimerSeconds = MutableStateFlow(0)
    val quizTimerSeconds: StateFlow<Int> = _quizTimerSeconds.asStateFlow()

    private val _quizFinished = MutableStateFlow(false)
    val quizFinished: StateFlow<Boolean> = _quizFinished.asStateFlow()

    private val _quizResultMessage = MutableStateFlow("")
    val quizResultMessage: StateFlow<String> = _quizResultMessage.asStateFlow()

    private val _quizFinalScore = MutableStateFlow(0)
    val quizFinalScore: StateFlow<Int> = _quizFinalScore.asStateFlow()

    private var timerJob: Job? = null

    init {
        val database = AppDatabase.getDatabase(application)
        repository = AcademyRepository(database)

        // Bind DB flows with viewModelScope Stateflows
        classes = repository.classes.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        pdfs = repository.pdfs.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        quizzes = repository.quizzes.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

        // Auto prepopulate demo academic data asynchronously
        viewModelScope.launch {
            repository.prepopulateIfEmpty()
        }
    }

    // --- Authentication ---
    fun login() {
        authError.value = null
        val mobile = loginMobile.value.trim()
        val pwd = loginPassword.value.trim()

        if (mobile.isEmpty() || pwd.isEmpty()) {
            authError.value = "इस नंबर और पासवर्ड को दर्ज करें!"
            return
        }

        viewModelScope.launch {
            val user = repository.getUserByMobile(mobile)
            if (user != null && user.password == pwd) {
                _currentUser.value = user
                loadUserAttempts(user.mobile)
                // Clear inputs
                loginMobile.value = ""
                loginPassword.value = ""
            } else {
                authError.value = "गलत मोबाइल नंबर या पासवर्ड! मालिक का आईडी: 7691021159 (password: owner)"
            }
        }
    }

    fun register() {
        authError.value = null
        regSuccessMesssage.value = null
        val name = regName.value.trim()
        val mobile = regMobile.value.trim()
        val pwd = regPassword.value.trim()

        if (name.isEmpty() || mobile.isEmpty() || pwd.isEmpty()) {
            authError.value = "सभी फील्ड भरना अनिवार्य है!"
            return
        }

        viewModelScope.launch {
            val existing = repository.getUserByMobile(mobile)
            if (existing != null) {
                authError.value = "यह मोबाइल नंबर पहले से ही पंजीकृत है!"
            } else {
                val finalRole = if (mobile == "7691021159") "ADMIN" else "USER"
                val newUser = User(mobile = mobile, name = name, password = pwd, role = finalRole)
                repository.registerUser(newUser)
                regSuccessMesssage.value = "रजिस्ट्रेशन सफल! अब आप लॉगिन कर सकते हैं।"
                // Reset reg fields
                regName.value = ""
                regMobile.value = ""
                regPassword.value = ""
            }
        }
    }

    fun logout() {
        _currentUser.value = null
        _userAttempts.value = emptyList()
        cancelQuiz()
    }

    private fun loadUserAttempts(userMobile: String) {
        viewModelScope.launch {
            repository.getAttemptsForUser(userMobile).collect { attempts ->
                _userAttempts.value = attempts
            }
        }
    }

    // --- Core Course Items (Admin Only) ---
    fun publishClass(title: String, subject: String, desc: String, instructor: String, type: String, videoId: String, scheduleDesc: String, isLive: Boolean) {
        viewModelScope.launch {
            repository.insertClass(
                ClassItem(
                    title = title,
                    subject = subject,
                    description = desc,
                    instructor = instructor,
                    type = type,
                    videoId = if (videoId.trim().isEmpty()) "dQw4w9WgXcQ" else videoId,
                    liveDateTime = scheduleDesc,
                    isLiveNow = isLive
                )
            )
        }
    }

    fun removeClass(id: Int) {
        viewModelScope.launch {
            repository.deleteClass(id)
        }
    }

    fun setLiveState(id: Int, isLive: Boolean) {
        viewModelScope.launch {
            repository.updateClassLiveStatus(id, isLive)
        }
    }

    fun publishPdf(title: String, subject: String, desc: String, pages: Int, size: String) {
        viewModelScope.launch {
            repository.insertPdf(
                PdfItem(
                    title = title,
                    subject = subject,
                    description = desc,
                    pageCount = if (pages <= 0) 1 else pages,
                    fileSize = if (size.trim().isEmpty()) "1.5 MB" else size
                )
            )
        }
    }

    fun removePdf(id: Int) {
        viewModelScope.launch {
            repository.deletePdf(id)
        }
    }

    // Publishes a quiz test with serialized interactive questions
    fun publishQuiz(title: String, subject: String, duration: Int, questions: List<QuizQuestion>) {
        viewModelScope.launch {
            val questionsJson = repository.serializeQuestions(questions)
            repository.insertQuiz(
                QuizItem(
                    title = title,
                    subject = subject,
                    durationMinutes = duration,
                    totalQuestions = questions.size,
                    maxMarks = questions.size * 10,
                    questionsJson = questionsJson
                )
            )
        }
    }

    fun removeQuiz(id: Int) {
        viewModelScope.launch {
            repository.deleteQuiz(id)
        }
    }

    // --- Interactive Quiz Taking Mechanics ---
    fun startQuiz(quiz: QuizItem) {
        timerJob?.cancel()
        _activeQuiz.value = quiz
        val list = repository.deserializeQuestions(quiz.questionsJson)
        _quizQuestions.value = list
        _currentQuestionIndex.value = 0
        _selectedAnswers.value = emptyMap()
        _quizTimerSeconds.value = quiz.durationMinutes * 60
        _quizFinished.value = false
        _quizResultMessage.value = ""
        _quizFinalScore.value = 0

        // Start countdown timer
        timerJob = viewModelScope.launch {
            while (_quizTimerSeconds.value > 0 && !_quizFinished.value) {
                delay(1000)
                _quizTimerSeconds.value = _quizTimerSeconds.value - 1
            }
            if (_quizTimerSeconds.value == 0 && !_quizFinished.value) {
                submitQuizAnswers()
            }
        }
    }

    fun selectQuizAnswer(questionIdx: Int, optionIndex: Int) {
        val updated = _selectedAnswers.value.toMutableMap()
        updated[questionIdx] = optionIndex
        _selectedAnswers.value = updated
    }

    fun nextQuizQuestion() {
        if (_currentQuestionIndex.value < _quizQuestions.value.size - 1) {
            _currentQuestionIndex.value = _currentQuestionIndex.value + 1
        }
    }

    fun prevQuizQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value = _currentQuestionIndex.value - 1
        }
    }

    fun submitQuizAnswers() {
        timerJob?.cancel()
        val currentQuiz = _activeQuiz.value ?: return
        val questions = _quizQuestions.value
        val userChoices = _selectedAnswers.value

        var correctCount = 0
        questions.forEachIndexed { idx, q ->
            val userSelect = userChoices[idx]
            if (userSelect != null && userSelect == q.correctAnswerIndex) {
                correctCount++
            }
        }

        val marksScored = correctCount * 10
        val outOf = questions.size * 10

        _quizFinalScore.value = marksScored
        _quizFinished.value = true
        _quizResultMessage.value = "आपने ${questions.size} में से $correctCount प्रश्नों का सही उत्तर दिया है!"

        // Save Attempt in Room DB asynchronously
        val student = _currentUser.value
        if (student != null) {
            viewModelScope.launch {
                repository.insertAttempt(
                    QuizAttempt(
                        quizId = currentQuiz.id,
                        userMobile = student.mobile,
                        quizTitle = currentQuiz.title,
                        score = marksScored,
                        maxMarks = outOf
                    )
                )
                // Refresh past records
                loadUserAttempts(student.mobile)
            }
        }
    }

    fun cancelQuiz() {
        timerJob?.cancel()
        timerJob = null
        _activeQuiz.value = null
        _quizQuestions.value = emptyList()
        _currentQuestionIndex.value = 0
        _selectedAnswers.value = emptyMap()
        _quizFinished.value = false
    }
}
