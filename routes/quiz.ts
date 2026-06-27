import express from 'express';
import Quiz from '../models/Quiz';
import QuizResult from '../models/QuizResult';
import { authMiddleware } from '../middleware/auth';

const router = express.Router();

// Get all quizzes
router.get('/', async (req, res) => {
  try {
    const quizzes = await Quiz.find().select('-answers');
    res.json(quizzes);
  } catch (error) {
    res.status(500).json({ error: 'Failed to fetch quizzes' });
  }
});

// Get single quiz
router.get('/:id', async (req, res) => {
  try {
    const quiz = await Quiz.findById(req.params.id).select('-correctAnswers');
    res.json(quiz);
  } catch (error) {
    res.status(500).json({ error: 'Failed to fetch quiz' });
  }
});

// Submit quiz
router.post('/:id/submit', authMiddleware, async (req, res) => {
  try {
    const { answers } = req.body;
    const quiz = await Quiz.findById(req.params.id);

    if (!quiz) {
      return res.status(404).json({ error: 'Quiz not found' });
    }

    let score = 0;
    quiz.questions.forEach((question: any, index: number) => {
      if (answers[index] === question.correctAnswer) {
        score++;
      }
    });

    const percentage = (score / quiz.questions.length) * 100;

    const result = new QuizResult({
      userId: (req as any).userId,
      quizId: req.params.id,
      score,
      totalQuestions: quiz.questions.length,
      percentage,
      answers,
      completedAt: new Date()
    });

    await result.save();
    res.json({ result, passed: percentage >= 50 });
  } catch (error) {
    res.status(500).json({ error: 'Failed to submit quiz' });
  }
});

// Get user's results
router.get('/results/my', authMiddleware, async (req, res) => {
  try {
    const results = await QuizResult.find({ userId: (req as any).userId }).populate('quizId');
    res.json(results);
  } catch (error) {
    res.status(500).json({ error: 'Failed to fetch results' });
  }
});

export default router;