import { useEffect, useState } from 'react';
import { motion } from 'motion/react';
import { BarChart3, BookOpen, Trophy, TrendingUp } from 'lucide-react';

export function Dashboard() {
  const [stats, setStats] = useState({
    quizzesAttempted: 12,
    avgScore: 78,
    bestScore: 95,
    studyStreak: 7
  });

  return (
    <div className="max-w-7xl mx-auto px-4 py-8">
      <h1 className="text-4xl font-extrabold text-indigo-900 mb-8 uppercase">My Dashboard</h1>

      <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          className="bg-white p-6 rounded-lg shadow-lg border-l-4 border-indigo-900"
        >
          <div className="flex items-center justify-between">
            <div>
              <p className="text-slate-500 text-sm font-bold uppercase">Quizzes Attempted</p>
              <p className="text-3xl font-extrabold text-indigo-900 mt-2">{stats.quizzesAttempted}</p>
            </div>
            <BookOpen className="w-12 h-12 text-indigo-200" />
          </div>
        </motion.div>

        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.1 }}
          className="bg-white p-6 rounded-lg shadow-lg border-l-4 border-amber-500"
        >
          <div className="flex items-center justify-between">
            <div>
              <p className="text-slate-500 text-sm font-bold uppercase">Average Score</p>
              <p className="text-3xl font-extrabold text-amber-600 mt-2">{stats.avgScore}%</p>
            </div>
            <TrendingUp className="w-12 h-12 text-amber-200" />
          </div>
        </motion.div>

        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.2 }}
          className="bg-white p-6 rounded-lg shadow-lg border-l-4 border-green-500"
        >
          <div className="flex items-center justify-between">
            <div>
              <p className="text-slate-500 text-sm font-bold uppercase">Best Score</p>
              <p className="text-3xl font-extrabold text-green-600 mt-2">{stats.bestScore}%</p>
            </div>
            <Trophy className="w-12 h-12 text-green-200" />
          </div>
        </motion.div>

        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.3 }}
          className="bg-white p-6 rounded-lg shadow-lg border-l-4 border-red-500"
        >
          <div className="flex items-center justify-between">
            <div>
              <p className="text-slate-500 text-sm font-bold uppercase">Study Streak</p>
              <p className="text-3xl font-extrabold text-red-600 mt-2">{stats.studyStreak} days</p>
            </div>
            <BarChart3 className="w-12 h-12 text-red-200" />
          </div>
        </motion.div>
      </div>
    </div>
  );
}