import { useState } from 'react';
import { motion } from 'motion/react';
import { LogIn, UserPlus } from 'lucide-react';

interface AuthProps {
  onClose: () => void;
}

export function AuthModal({ onClose }: AuthProps) {
  const [isLogin, setIsLogin] = useState(true);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    // API call here
    setTimeout(() => setLoading(false), 1000);
  };

  return (
    <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      className="fixed inset-0 bg-black/50 flex items-center justify-center z-50"
      onClick={onClose}
    >
      <motion.div
        initial={{ scale: 0.95 }}
        animate={{ scale: 1 }}
        className="bg-white rounded-lg shadow-xl max-w-md w-full mx-4"
        onClick={(e) => e.stopPropagation()}
      >
        <div className="p-8">
          <h2 className="text-2xl font-bold text-center mb-6 text-indigo-900">
            {isLogin ? 'Login' : 'Sign Up'}
          </h2>

          <form onSubmit={handleSubmit} className="space-y-4">
            {!isLogin && (
              <input
                type="text"
                placeholder="Full Name"
                className="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:border-indigo-900"
              />
            )}
            <input
              type="email"
              placeholder="Email"
              className="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:border-indigo-900"
            />
            <input
              type="password"
              placeholder="Password"
              className="w-full px-4 py-2 border border-slate-300 rounded-lg focus:outline-none focus:border-indigo-900"
            />
            <button
              type="submit"
              disabled={loading}
              className="w-full px-6 py-2 bg-indigo-900 text-white font-bold rounded-lg hover:bg-indigo-800 disabled:opacity-50"
            >
              {loading ? 'Loading...' : isLogin ? 'Login' : 'Sign Up'}
            </button>
          </form>

          <button
            onClick={() => setIsLogin(!isLogin)}
            className="w-full mt-4 text-center text-indigo-900 font-bold text-sm hover:underline"
          >
            {isLogin ? "Don't have an account? Sign Up" : 'Already have an account? Login'}
          </button>
        </div>
      </motion.div>
    </motion.div>
  );
}