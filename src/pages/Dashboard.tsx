import { useEffect, useState } from 'react';
import { motion } from 'motion/react';
import { useAuthStore } from '../store/auth';
import { LogOut, BarChart3, BookOpen, Trophy } from 'lucide-react';

export function Dashboard() {
  const { user, logout } = useAuthStore();
  const [stats, setStats] = useState({
    quizzesAttempted: 0,
    avgScore: 0,
    bestScore: 0,
    studyStreak: 0,
  });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadStats = async () => {
      try {
        const token = localStorage.getItem('token');
        if (!token) return;

        const response = await fetch(
          `${process.env.REACT_APP_API_URL || 'http://localhost:5000/api'}/user/stats`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        const data = await response.json();
        setStats(data);
      } catch (error) {
        console.error('Failed to load stats', error);
      } finally {
        setLoading(false);
      }
    };

    loadStats();
  }, []);

  if (!user) return null;

  return (
    <div className="max-w-7xl mx-auto px-4 py-8">
      <div className="flex justify-between items-center mb-8">
        <div>
          <h1 className="text-4xl font-extrabold text-indigo-900 uppercase">Welcome, {user.name}! 👋</h1>
          <p className="text-slate-500 mt-2">Role: <span className="font-bold uppercase">{user.role}</span></p>
        </div>
        <button
          onClick={logout}
          className="flex items-center gap-2 px-6 py-3 bg-red-500 text-white font-bold rounded-lg hover:bg-red-600"
        >
          <LogOut className="w-4 h-4" /> Logout
        </button>
      </div>

      {loading ? (
        <p className="text-center text-slate-500">Loading stats...</p>
      ) : (
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
                <p className="text-3xl font-extrabold text-amber-600 mt-2">{stats.avgScore.toFixed(1)}%</p>
              </div>
              <BarChart3 className="w-12 h-12 text-amber-200" />
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
              <div className="w-12 h-12 bg-red-200 rounded-lg flex items-center justify-center text-red-600 font-bold">🔥</div>
            </div>
          </motion.div>
        </div>
      )}
    </div>
  );
}