import { useState } from 'react';
import { AnimatePresence, motion } from 'motion/react';
import { Header } from './components/Header';
import { HomeLayer } from './components/HomeLayer';
import { PdfLayer } from './components/PdfLayer';
import { TestLayer } from './components/TestLayer';
import { ConnectLayer } from './components/ConnectLayer';
import { TabType } from './types';

export default function App() {
  const [activeTab, setActiveTab] = useState<TabType>('home');

  return (
    <div className="min-h-screen flex flex-col font-sans selection:bg-indigo-200 selection:text-indigo-900">
      <Header activeTab={activeTab} onTabChange={setActiveTab} />
      
      <main className="flex-1">
        <AnimatePresence mode="wait">
          {activeTab === 'home' && (
            <motion.div key="home" initial={{opacity:0}} animate={{opacity:1}} exit={{opacity:0}}>
              <HomeLayer onNavigate={setActiveTab} />
            </motion.div>
          )}
          {activeTab === 'pdfs' && (
            <motion.div key="pdfs" initial={{opacity:0}} animate={{opacity:1}} exit={{opacity:0}}>
              <PdfLayer />
            </motion.div>
          )}
          {activeTab === 'tests' && (
            <motion.div key="tests" initial={{opacity:0}} animate={{opacity:1}} exit={{opacity:0}}>
              <TestLayer />
            </motion.div>
          )}
          {activeTab === 'connect' && (
            <motion.div key="connect" initial={{opacity:0}} animate={{opacity:1}} exit={{opacity:0}}>
              <ConnectLayer />
            </motion.div>
          )}
        </AnimatePresence>
      </main>

      {/* Footer */}
      <footer className="bg-white border-t-2 border-slate-200 py-8 mt-auto">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 grid grid-cols-1 md:grid-cols-3 gap-8 items-center">
          <div className="text-center md:text-left">
             <span className="text-xs font-bold text-indigo-600 uppercase tracking-widest block mb-1">Pratibimb Academy</span>
             <p className="text-[10px] text-slate-400">Rajasthan Competitive Exam Specialist</p>
          </div>
          <div className="text-center">
             <p className="text-xs text-slate-400 italic mb-2">"Karm hi Pooja hai"</p>
             <p className="text-[10px] text-slate-300">© {new Date().getFullYear()} Pratibimb Academy Rajasthan</p>
          </div>
          <div className="flex justify-center md:justify-end gap-6">
            <span className="text-[10px] font-bold text-slate-600 cursor-pointer hover:text-indigo-900 transition-colors tracking-widest">SUPPORT</span>
            <span className="text-[10px] font-bold text-slate-600 cursor-pointer hover:text-indigo-900 transition-colors tracking-widest">PRIVACY</span>
            <span className="text-[10px] font-bold text-slate-600 cursor-pointer hover:text-indigo-900 transition-colors tracking-widest">TERMS</span>
          </div>
        </div>
      </footer>
    </div>
  );
}