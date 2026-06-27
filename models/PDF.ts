import mongoose from 'mongoose';

const pdfSchema = new mongoose.Schema({
  title: { type: String, required: true },
  description: String,
  exam: { type: String, required: true },
  subject: { type: String, required: true },
  url: String,
  downloadUrl: String,
  uploadedBy: mongoose.Schema.Types.ObjectId,
  uploadedAt: { type: Date, default: Date.now },
  downloads: { type: Number, default: 0 },
  favorites: [mongoose.Schema.Types.ObjectId],
  views: { type: Number, default: 0 },
  rating: { type: Number, default: 0 }
});

export default mongoose.model('PDF', pdfSchema);