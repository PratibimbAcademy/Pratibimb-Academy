import mongoose from 'mongoose';

const analyticsSchema = new mongoose.Schema({
  eventType: String,
  userId: mongoose.Schema.Types.ObjectId,
  data: mongoose.Schema.Types.Mixed,
  timestamp: { type: Date, default: Date.now },
  metadata: mongoose.Schema.Types.Mixed
});

export default mongoose.model('Analytics', analyticsSchema);