import { motion } from 'motion/react';
import { ExternalLink, BookOpen, Target } from 'lucide-react';
import { LINKS } from '../types';

export function TestLayer() {
  return (
    <motion.div 
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      exit={{ opacity: 0, y: -20 }}
      className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12 md:py-20"
    >
      <div className="text-center mb-16">
        <div className="w-16 h-16 bg-amber-500 text-indigo-950 flex items-center justify-center mx-auto mb-6 -rotate-12 shadow-lg">
          <BookOpen className="w-8 h-8 rotate-12" />
        </div>
        <h2 className="text-3xl md:text-5xl font-extrabold tracking-tighter uppercase text-indigo-900 mb-4">
          Mock Tests
        </h2>
        <div className="w-24 h-1 bg-amber-500 mx-auto mb-6"></div>
        <p className="text-slate-500 text-lg max-w-2xl mx-auto">
          Evaluate your preparation level with our carefully curated mock tests and previous year question papers.
        </p>
      </div>

      <div className="bg-white border-2 border-slate-200 shadow-xl overflow-hidden flex flex-col md:flex-row">
        <div className="p-8 md:p-12 flex-1 space-y-6">
          <h3 className="text-2xl font-extrabold uppercase tracking-tight text-slate-900">Test Series Folder</h3>
          <p className="text-slate-500 leading-relaxed">
            Find complete length mock tests, sectional practice tests, and OMR sheets to simulate the real exam environment at home.
          </p>
          <ul className="text-slate-600 text-sm space-y-3 font-medium">
            <li className="flex items-center gap-3">
              <div className="p-1 bg-indigo-100 text-indigo-600 rounded-sm">
                <Target className="w-4 h-4"/>
              </div>
              Full-length Mock Tests
            </li>
            <li className="flex items-center gap-3">
              <div className="p-1 bg-indigo-100 text-indigo-600 rounded-sm">
                <Target className="w-4 h-4"/>
              </div>
              Previous Year Papers with Solutions
            </li>
            <li className="flex items-center gap-3">
              <div className="p-1 bg-indigo-100 text-indigo-600 rounded-sm">
                <Target className="w-4 h-4"/>
              </div>
              Subject-wise Question Banks
            </li>
          </ul>
        </div>
        
        <div className="bg-indigo-900 md:w-1/3 border-t-2 md:border-t-0 md:border-l-2 border-slate-200 p-8 md:p-12 flex flex-col items-center justify-center text-center">
          <div className="w-20 h-20 bg-white/10 text-amber-400 rounded-sm flex items-center justify-center mb-6 shadow-md">
            <svg className="w-10 h-10" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-6 9l2 2 4-4"></path></svg>
          </div>
          <a 
            href={LINKS.tests}
            target="_blank"
            rel="noopener noreferrer"
            className="w-full px-8 py-4 bg-amber-500 text-indigo-950 font-bold uppercase tracking-wider text-sm border-b-4 border-amber-600 shadow-xl active:translate-y-1 transition-all flex items-center justify-center gap-2"
          >
            Open Tests Folder
            <ExternalLink className="w-4 h-4" />
          </a>
        </div>
      </div>
    </motion.div>
  );
}