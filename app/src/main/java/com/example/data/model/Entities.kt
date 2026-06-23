package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class User(
    @PrimaryKey val mobile: String,
    val name: String,
    val password: String,
    val role: String, // "ADMIN" or "USER"
    val timestamp: Long = System.currentTimeMillis()
) : Serializable

@Entity(tableName = "classes")
data class ClassItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val subject: String,
    val description: String,
    val instructor: String,
    val type: String, // "LIVE" or "RECORDED"
    val videoId: String, // YouTube video ID or video token
    val liveDateTime: String, // "Live at 11:30 AM Today" or cached description
    val isLiveNow: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "pdfs")
data class PdfItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val subject: String,
    val description: String,
    val fileSize: String,
    val pageCount: Int,
    val localPdfPath: String = "", // Simulator download key or actual text structure
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "quizzes")
data class QuizItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val subject: String,
    val durationMinutes: Int,
    val totalQuestions: Int,
    val maxMarks: Int,
    val questionsJson: String, // Serialized list of questions
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "quiz_attempts")
data class QuizAttempt(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val quizId: Int,
    val userMobile: String,
    val quizTitle: String,
    val score: Int,
    val maxMarks: Int,
    val timestamp: Long = System.currentTimeMillis()
)

// Helper structure for Quiz questions
data class QuizQuestion(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
