import mongoose from 'mongoose';

const quizResultSchema = new mongoose.Schema({
  userId: { type: mongoose.Schema.Types.ObjectId, required: true },
  quizId: { type: mongoose.Schema.Types.ObjectId, required: true },
  score: Number,
  totalQuestions: Number,
  percentage: Number,
  answers: [String],
  completedAt: Date,
  timeTaken: Number,
  passed: Boolean
});

export default mongoose.model('QuizResult', quizResultSchema);