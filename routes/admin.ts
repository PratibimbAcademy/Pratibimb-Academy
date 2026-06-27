import express from 'express';
import PDF from '../models/PDF';
import Quiz from '../models/Quiz';
import User from '../models/User';
import { authMiddleware, adminMiddleware } from '../middleware/auth';

const router = express.Router();

// Add PDF
router.post('/pdf', authMiddleware, adminMiddleware, async (req, res) => {
  try {
    const { title, description, exam, subject, url, downloadUrl } = req.body;

    const pdf = new PDF({
      title,
      description,
      exam,
      subject,
      url,
      downloadUrl,
      uploadedBy: (req as any).userId,
      uploadedAt: new Date()
    });

    await pdf.save();
    res.json({ message: 'PDF uploaded successfully', pdf });
  } catch (error) {
    res.status(500).json({ error: 'Failed to upload PDF' });
  }
});

// Add Quiz
router.post('/quiz', authMiddleware, adminMiddleware, async (req, res) => {
  try {
    const { title, description, exam, questions, duration } = req.body;

    const quiz = new Quiz({
      title,
      description,
      exam,
      questions,
      duration,
      createdBy: (req as any).userId,
      createdAt: new Date()
    });

    await quiz.save();
    res.json({ message: 'Quiz created successfully', quiz });
  } catch (error) {
    res.status(500).json({ error: 'Failed to create quiz' });
  }
});

// Get all users
router.get('/users', authMiddleware, adminMiddleware, async (req, res) => {
  try {
    const users = await User.find().select('-password');
    res.json(users);
  } catch (error) {
    res.status(500).json({ error: 'Failed to fetch users' });
  }
});

// Delete PDF
router.delete('/pdf/:id', authMiddleware, adminMiddleware, async (req, res) => {
  try {
    await PDF.findByIdAndDelete(req.params.id);
    res.json({ message: 'PDF deleted successfully' });
  } catch (error) {
    res.status(500).json({ error: 'Failed to delete PDF' });
  }
});

export default router;