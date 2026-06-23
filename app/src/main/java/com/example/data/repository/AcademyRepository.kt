package com.example.data.repository

import android.util.Log
import com.example.data.db.AppDatabase
import com.example.data.model.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow

class AcademyRepository(private val db: AppDatabase) {

    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val questionsType = Types.newParameterizedType(List::class.java, QuizQuestion::class.java)
    private val adapter = moshi.adapter<List<QuizQuestion>>(questionsType)

    val classes: Flow<List<ClassItem>> = db.classDao().getAllClasses()
    val pdfs: Flow<List<PdfItem>> = db.pdfDao().getAllPdfs()
    val quizzes: Flow<List<QuizItem>> = db.quizDao().getAllQuizzes()

    suspend fun getUserByMobile(mobile: String): User? {
        return db.userDao().getUserByMobile(mobile)
    }

    suspend fun registerUser(user: User) {
        db.userDao().insertUser(user)
    }

    suspend fun insertClass(classItem: ClassItem) {
        db.classDao().insertClass(classItem)
    }

    suspend fun deleteClass(id: Int) {
        db.classDao().deleteClassById(id)
    }

    suspend fun updateClassLiveStatus(id: Int, isLiveNow: Boolean) {
        db.classDao().updateLiveStatus(id, isLiveNow)
    }

    suspend fun insertPdf(pdfItem: PdfItem) {
        db.pdfDao().insertPdf(pdfItem)
    }

    suspend fun deletePdf(id: Int) {
        db.pdfDao().deletePdfById(id)
    }

    suspend fun insertQuiz(quizItem: QuizItem) {
        db.quizDao().insertQuiz(quizItem)
    }

    suspend fun deleteQuiz(id: Int) {
        db.quizDao().deleteQuizById(id)
    }

    fun getAttemptsForUser(mobile: String): Flow<List<QuizAttempt>> {
        return db.quizAttemptDao().getAttemptsForUser(mobile)
    }

    suspend fun insertAttempt(attempt: QuizAttempt) {
        db.quizAttemptDao().insertAttempt(attempt)
    }

    fun serializeQuestions(questions: List<QuizQuestion>): String {
        return try {
            adapter.toJson(questions)
        } catch (e: Exception) {
            Log.e("AcademyRepository", "Serialization failed", e)
            "[]"
        }
    }

    fun deserializeQuestions(json: String): List<QuizQuestion> {
        return try {
            adapter.fromJson(json) ?: emptyList()
        } catch (e: Exception) {
            Log.e("AcademyRepository", "Deserialization failed", e)
            emptyList()
        }
    }

    suspend fun prepopulateIfEmpty() {
        val userCount = db.userDao().getUserCount()
        if (userCount == 0) {
            Log.d("AcademyRepository", "Prepopulating educational database...")
            // Create default user accounts
            db.userDao().insertUser(User(mobile = "7691021159", name = "Pretibimb Owner Admin", password = "owner", role = "ADMIN"))
            db.userDao().insertUser(User(mobile = "9876543210", name = "Ramesh Kumar Sharma", password = "user", role = "USER"))

            // Prepopulate some interactive online classes
            db.classDao().insertClass(ClassItem(
                title = "UP Board Target 2026: Modern Physics & Bohr's Model",
                subject = "Physics",
                description = "Complete masterclass on Atoms, Bohr's postulates, energy levels, and spectral lines emission formula, incorporating standard diagrams.",
                instructor = "Er. Subodh Kumar (MR Sir)",
                type = "LIVE",
                videoId = "e87N6O_mR_k", // Mock or test video id
                liveDateTime = "Scheduled Live: June 25, 11:30 AM",
                isLiveNow = true
            ))

            db.classDao().insertClass(ClassItem(
                title = "LMT-04: Trigonometry Ratios and Euler Form Identity",
                subject = "Mathematics",
                description = "A deep-dive on Euler's identity, complex trigonometry ratios, and height & distance compound angles shortcut formulas.",
                instructor = "Dr. S.K. Jha (Maths Expert)",
                type = "RECORDED",
                videoId = "dQw4w9WgXcQ",
                liveDateTime = "Recorded Lecture - 2 Hours",
                isLiveNow = false
            ))

            db.classDao().insertClass(ClassItem(
                title = "UPSC Pre-2026: Modern India Revolt of 1857 Causes",
                subject = "History",
                description = "Comprehensive historical analysis of the political, economic, military and immediate social spark points of the 1857 Revolt.",
                instructor = "Vikas Divyakirti Sir",
                type = "RECORDED",
                videoId = "Sc6-Sc6",
                liveDateTime = "Recorded Lecture - 1 Hour 40 Mins",
                isLiveNow = false
            ))

            // Prepopulate study notes (PDFs)
            db.pdfDao().insertPdf(PdfItem(
                title = "Physics Master Formula Book - Class 11 & 12 Board",
                subject = "Physics",
                description = "Compact hand-written formula book containing electrodynamics, optics, heat laws, modern physics equations and unit dimensions.",
                fileSize = "4.2 MB",
                pageCount = 42
            ))

            db.pdfDao().insertPdf(PdfItem(
                title = "Pretibimb Mock Biology MCQ Bank with Solutions",
                subject = "Biology",
                description = "Chapter-wise highly curated Biology practice questions covering Human Physiology and Plant Genetics with detailed diagram logs.",
                fileSize = "2.8 MB",
                pageCount = 28
            ))

            db.pdfDao().insertPdf(PdfItem(
                title = "Weekly Core Current Affairs Bulletin - June 2026",
                subject = "General Knowledge",
                description = "Summarized breakdown of space missions, G20 outcomes, defense updates, and crucial national schemes for academic competitive exams.",
                fileSize = "1.5 MB",
                pageCount = 18
            ))

            // Prepopulate some interactive test quizzes
            val mathQuizQuestions = listOf(
                QuizQuestion(
                    id = 1,
                    text = "If sin(θ) + cos(θ) = √2, then what is the value of θ in degrees?",
                    options = listOf("30°", "45°", "60°", "90°"),
                    correctAnswerIndex = 1
                ),
                QuizQuestion(
                    id = 2,
                    text = "What is the value of log(e) of e?",
                    options = listOf("0", "1", "e", "Infinity"),
                    correctAnswerIndex = 1
                ),
                QuizQuestion(
                    id = 3,
                    text = "In a triangle, if the sides are 3cm, 4cm, and 5cm, what is the circumradius?",
                    options = listOf("1.5 cm", "2.0 cm", "2.5 cm", "5.0 cm"),
                    correctAnswerIndex = 2
                )
            )

            db.quizDao().insertQuiz(QuizItem(
                title = "Weekly Quiz 01: Mathematics Compound Angles Booster",
                subject = "Mathematics",
                durationMinutes = 10,
                totalQuestions = 3,
                maxMarks = 30,
                questionsJson = serializeQuestions(mathQuizQuestions)
            ))

            val scienceQuestions = listOf(
                QuizQuestion(
                    id = 1,
                    text = "What is the SI unit of electric potential difference?",
                    options = listOf("Ampere", "Ohm", "Volt", "Watt"),
                    correctAnswerIndex = 2
                ),
                QuizQuestion(
                    id = 2,
                    text = "Which of the following has the highest electrical conductivity?",
                    options = listOf("Copper", "Silver", "Gold", "Aluminum"),
                    correctAnswerIndex = 1
                ),
                QuizQuestion(
                    id = 3,
                    text = "Light-year is a unit of which of the following physical quantities?",
                    options = listOf("Time", "Intense Luminosity", "Distance", "Mass / Gravitational Pull"),
                    correctAnswerIndex = 2
                )
            )

            db.quizDao().insertQuiz(QuizItem(
                title = "Science Capsule: Electromagnetic Force & Units",
                subject = "Science",
                durationMinutes = 15,
                totalQuestions = 3,
                maxMarks = 30,
                questionsJson = serializeQuestions(scienceQuestions)
            ))
        }
    }
}
