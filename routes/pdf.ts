import express from 'express';
import PDF from '../models/PDF';
import { authMiddleware } from '../middleware/auth';

const router = express.Router();

// Get all PDFs
router.get('/', async (req, res) => {
  try {
    const { exam, subject } = req.query;
    const filter: any = {};

    if (exam) filter.exam = exam;
    if (subject) filter.subject = subject;

    const pdfs = await PDF.find(filter).sort({ createdAt: -1 });
    res.json(pdfs);
  } catch (error) {
    res.status(500).json({ error: 'Failed to fetch PDFs' });
  }
});

// Get PDF by ID
router.get('/:id', async (req, res) => {
  try {
    const pdf = await PDF.findById(req.params.id);
    if (!pdf) {
      return res.status(404).json({ error: 'PDF not found' });
    }
    res.json(pdf);
  } catch (error) {
    res.status(500).json({ error: 'Failed to fetch PDF' });
  }
});

// Add to favorites
router.post('/:id/favorite', authMiddleware, async (req, res) => {
  try {
    const pdf = await PDF.findById(req.params.id);
    if (!pdf) {
      return res.status(404).json({ error: 'PDF not found' });
    }

    const userId = (req as any).userId;
    if (!pdf.favorites.includes(userId)) {
      pdf.favorites.push(userId);
      await pdf.save();
    }

    res.json({ message: 'Added to favorites', favorites: pdf.favorites.length });
  } catch (error) {
    res.status(500).json({ error: 'Failed to add to favorites' });
  }
});

export default router;