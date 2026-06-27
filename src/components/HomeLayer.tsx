import { motion } from 'motion/react';
import { ArrowRight, CheckCircle2, GraduationCap } from 'lucide-react';
import { EXAMS, TabType } from '../types';

interface HomeProps {
  onNavigate: (tab: TabType) => void;
}

export function HomeLayer({ onNavigate }: HomeProps) {
  return (
    <div className="flex flex-col">
      {/* Hero Section */}
      <section className="relative overflow-hidden py-16 md:py-24 bg-white border-b border-slate-200">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <motion.div
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.5 }}
            className="inline-flex items-center gap-2 px-3 py-1 bg-amber-50 border border-amber-200 text-amber-600 text-xs font-bold tracking-widest uppercase mb-8"
          >
            <GraduationCap className="w-4 h-4" />
            <span>Rajasthan's Premier Exam Prep</span>
          </motion.div>
          
          <motion.h1 
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.5, delay: 0.1 }}
            className="text-4xl md:text-5xl lg:text-7xl font-extrabold text-indigo-900 tracking-tighter max-w-4xl mx-auto leading-tight uppercase"
          >
            Your Pathway to <span className="text-amber-500 block sm:inline">Success</span>
          </motion.h1>
          
          <motion.p 
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.5, delay: 0.2 }}
            className="mt-6 text-lg md:text-xl text-slate-500 max-w-2xl mx-auto"
          >
            Comprehensive study materials, mock tests, and expert guidance for REET, CET, Patwar, Police, and more.
          </motion.p>
          
          <motion.div 
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.5, delay: 0.3 }}
            className="mt-10 flex flex-col sm:flex-row items-center justify-center gap-6"
          >
            <button 
              onClick={() => onNavigate('pdfs')}
              className="w-full sm:w-auto px-8 py-4 bg-indigo-900 text-white font-bold tracking-wider uppercase text-sm border-b-4 border-indigo-950 shadow-xl active:translate-y-1 transition-all flex items-center justify-center gap-2"
            >
              Access Study PDFs
              <ArrowRight className="w-4 h-4" />
            </button>
            <button 
              onClick={() => onNavigate('tests')}
              className="w-full sm:w-auto px-8 py-4 bg-amber-500 text-indigo-950 font-bold tracking-wider uppercase text-sm border-b-4 border-amber-600 shadow-xl active:translate-y-1 transition-all flex items-center justify-center gap-2"
            >
              Take Mock Tests
            </button>
          </motion.div>
        </div>
      </section>

      {/* Exams Grid Section */}
      <section className="bg-slate-50 border-b border-slate-200">
        <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-7 border-t border-slate-200">
          {EXAMS.map((exam, index) => (
            <motion.div
              key={exam.id}
              initial={{ opacity: 0 }}
              whileInView={{ opacity: 1 }}
              viewport={{ once: true }}
              transition={{ delay: index * 0.05 }}
              className="border-r border-b lg:border-b-0 border-slate-200 flex flex-col items-center justify-center p-6 hover:bg-white transition-colors cursor-pointer group"
            >
              <span className="text-4xl font-black text-slate-200 group-hover:text-indigo-600 mb-2 transition-colors">
                {String(index + 1).padStart(2, '0')}
              </span>
              <span className="font-bold tracking-wider text-slate-800">{exam.name}</span>
              <span className="text-[10px] text-slate-400 mt-1 uppercase tracking-widest text-center">
                {exam.description.split(' ').slice(0, 2).join(' ')}
              </span>
            </motion.div>
          ))}
        </div>
      </section>
      
      {/* Featured Resources Section */}
      <section className="flex flex-col lg:flex-row min-h-[400px]">
        <div className="w-full lg:w-1/2 p-8 md:p-16 bg-white flex flex-col justify-center border-b lg:border-b-0 lg:border-r border-slate-200">
          <div className="flex items-center gap-4 mb-6">
            <div className="p-4 bg-amber-50 border border-amber-100 text-amber-600 rounded-sm">
              <svg className="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M7 21h10a2 2 0 002-2V9.414a1 1 0 00-.293-.707l-5.414-5.414A1 1 0 0012.586 3H7a2 2 0 00-2 2v14a2 2 0 002 2z"></path></svg>
            </div>
            <h2 className="text-3xl font-extrabold text-slate-900 tracking-tight">STUDY MATERIAL</h2>
          </div>
          <p className="text-slate-500 mb-8 leading-relaxed max-w-lg text-lg">Access organized PDF notes for all Rajasthan state government exams. Expert-curated content updated for 2024 sessions.</p>
          <button onClick={() => onNavigate('pdfs')} className="w-fit px-8 py-4 bg-indigo-900 text-white font-bold tracking-wider uppercase text-sm border-b-4 border-indigo-950 shadow-xl active:translate-y-1 transition-all">
            Open PDF Folder
          </button>
        </div>
        <div className="w-full lg:w-1/2 p-8 md:p-16 bg-indigo-900 flex flex-col justify-center text-white relative overflow-hidden">
          <div className="absolute top-0 right-0 w-64 h-64 bg-indigo-800 rounded-full blur-3xl -translate-y-1/2 translate-x-1/3 opacity-50"></div>
          <div className="relative z-10">
            <div className="flex items-center gap-4 mb-6">
              <div className="p-4 bg-indigo-800 text-amber-400 rounded-sm">
                <svg className="w-8 h-8" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-6 9l2 2 4-4"></path></svg>
              </div>
              <h2 className="text-3xl font-extrabold tracking-tight">PRACTICE TESTS</h2>
            </div>
            <p className="text-indigo-200 mb-8 leading-relaxed max-w-lg text-lg">Attempt mock tests and previous year papers to evaluate your preparation level. Real-time difficulty standards.</p>
            <button onClick={() => onNavigate('tests')} className="w-fit px-8 py-4 bg-amber-500 text-indigo-950 font-bold tracking-wider uppercase text-sm border-b-4 border-amber-600 shadow-xl active:translate-y-1 transition-all">
              Go to Test Series
            </button>
          </div>
        </div>
      </section>
    </div>
  );
}