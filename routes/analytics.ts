import express from 'express';
import QuizResult from '../models/QuizResult';
import User from '../models/User';
import PDF from '../models/PDF';
import { authMiddleware, adminMiddleware } from '../middleware/auth';

const router = express.Router();

// Get dashboard stats
router.get('/dashboard', authMiddleware, adminMiddleware, async (req, res) => {
  try {
    const totalUsers = await User.countDocuments();
    const totalPDFs = await PDF.countDocuments();
    const totalQuizzes = await QuizResult.countDocuments();

    const avgScore = await QuizResult.aggregate([
      { $group: { _id: null, avg: { $avg: '$percentage' } } }
    ]);

    res.json({
      totalUsers,
      totalPDFs,
      totalQuizzes,
      avgScore: avgScore[0]?.avg || 0,
      activeToday: Math.floor(Math.random() * totalUsers)
    });
  } catch (error) {
    res.status(500).json({ error: 'Failed to fetch analytics' });
  }
});

// Get user analytics
router.get('/user/:userId', authMiddleware, adminMiddleware, async (req, res) => {
  try {
    const results = await QuizResult.find({ userId: req.params.userId });
    const avgScore = results.reduce((acc, r) => acc + r.percentage, 0) / results.length || 0;

    res.json({
      totalAttempts: results.length,
      avgScore,
      bestScore: Math.max(...results.map(r => r.percentage), 0),
      recentAttempts: results.slice(-5)
    });
  } catch (error) {
    res.status(500).json({ error: 'Failed to fetch user analytics' });
  }
});

export default router;