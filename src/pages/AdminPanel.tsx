import { useEffect, useState } from 'react';
import { motion } from 'motion/react';
import { Plus, Trash2, Users, FileText, BookOpen } from 'lucide-react';

export function AdminPanel() {
  const [activeTab, setActiveTab] = useState<'pdf' | 'quiz' | 'users'>('pdf');
  const [loading, setLoading] = useState(false);

  const handleUploadPDF = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    // API call here
    setTimeout(() => setLoading(false), 1000);
  };

  return (
    <div className="max-w-7xl mx-auto px-4 py-8">
      <h1 className="text-4xl font-extrabold text-indigo-900 mb-8 uppercase">Admin Panel</h1>

      <div className="flex gap-4 mb-8">
        <button
          onClick={() => setActiveTab('pdf')}
          className={`flex items-center gap-2 px-6 py-3 font-bold uppercase ${
            activeTab === 'pdf'
              ? 'bg-indigo-900 text-white'
              : 'bg-slate-100 text-slate-600 hover:bg-slate-200'
          }`}
        >
          <FileText className="w-4 h-4" /> PDFs
        </button>
        <button
          onClick={() => setActiveTab('quiz')}
          className={`flex items-center gap-2 px-6 py-3 font-bold uppercase ${
            activeTab === 'quiz'
              ? 'bg-indigo-900 text-white'
              : 'bg-slate-100 text-slate-600 hover:bg-slate-200'
          }`}
        >
          <BookOpen className="w-4 h-4" /> Quizzes
        </button>
        <button
          onClick={() => setActiveTab('users')}
          className={`flex items-center gap-2 px-6 py-3 font-bold uppercase ${
            activeTab === 'users'
              ? 'bg-indigo-900 text-white'
              : 'bg-slate-100 text-slate-600 hover:bg-slate-200'
          }`}
        >
          <Users className="w-4 h-4" /> Users
        </button>
      </div>

      {activeTab === 'pdf' && (
        <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }}>
          <div className="bg-white p-8 rounded-lg shadow-lg">
            <h2 className="text-2xl font-bold mb-6">Upload PDF</h2>
            <form onSubmit={handleUploadPDF} className="space-y-4">
              <input
                type="text"
                placeholder="Title"
                className="w-full px-4 py-2 border border-slate-300 rounded"
              />
              <input
                type="text"
                placeholder="Description"
                className="w-full px-4 py-2 border border-slate-300 rounded"
              />
              <select className="w-full px-4 py-2 border border-slate-300 rounded">
                <option>Select Exam</option>
                <option>REET</option>
                <option>CET</option>
                <option>Patwar</option>
              </select>
              <input
                type="text"
                placeholder="Google Drive URL"
                className="w-full px-4 py-2 border border-slate-300 rounded"
              />
              <button
                type="submit"
                disabled={loading}
                className="w-full px-6 py-3 bg-indigo-900 text-white font-bold uppercase hover:bg-indigo-800 disabled:opacity-50"
              >
                {loading ? 'Uploading...' : 'Upload PDF'}
              </button>
            </form>
          </div>
        </motion.div>
      )}
    </div>
  );
}