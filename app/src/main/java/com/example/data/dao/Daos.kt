package com.example.data.dao

import androidx.room.*
import com.example.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE mobile = :mobile LIMIT 1")
    suspend fun getUserByMobile(mobile: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUserCount(): Int
}

@Dao
interface ClassDao {
    @Query("SELECT * FROM classes ORDER BY timestamp DESC")
    fun getAllClasses(): Flow<List<ClassItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClass(classItem: ClassItem)

    @Query("DELETE FROM classes WHERE id = :id")
    suspend fun deleteClassById(id: Int)

    @Query("UPDATE classes SET isLiveNow = :isLiveNow WHERE id = :id")
    suspend fun updateLiveStatus(id: Int, isLiveNow: Boolean)
}

@Dao
interface PdfDao {
    @Query("SELECT * FROM pdfs ORDER BY timestamp DESC")
    fun getAllPdfs(): Flow<List<PdfItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPdf(pdfItem: PdfItem)

    @Query("DELETE FROM pdfs WHERE id = :id")
    suspend fun deletePdfById(id: Int)
}

@Dao
interface QuizDao {
    @Query("SELECT * FROM quizzes ORDER BY timestamp DESC")
    fun getAllQuizzes(): Flow<List<QuizItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quizItem: QuizItem)

    @Query("DELETE FROM quizzes WHERE id = :id")
    suspend fun deleteQuizById(id: Int)
}

@Dao
interface QuizAttemptDao {
    @Query("SELECT * FROM quiz_attempts WHERE userMobile = :mobile ORDER BY timestamp DESC")
    fun getAttemptsForUser(mobile: String): Flow<List<QuizAttempt>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(attempt: QuizAttempt)
}
