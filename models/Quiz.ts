import mongoose from 'mongoose';

const quizSchema = new mongoose.Schema({
  title: { type: String, required: true },
  description: String,
  exam: { type: String, required: true },
  duration: { type: Number, default: 60 },
  questions: [
    {
      question: String,
      options: [String],
      correctAnswer: String,
      explanation: String
    }
  ],
  createdBy: mongoose.Schema.Types.ObjectId,
  createdAt: { type: Date, default: Date.now },
  difficulty: { type: String, enum: ['easy', 'medium', 'hard'], default: 'medium' },
  totalAttempts: { type: Number, default: 0 },
  avgScore: { type: Number, default: 0 }
});

export default mongoose.model('Quiz', quizSchema);