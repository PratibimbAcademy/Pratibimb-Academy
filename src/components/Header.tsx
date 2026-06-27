import { motion } from 'motion/react';
import { BookOpen, FileText, LayoutDashboard, Share2, Menu, X } from 'lucide-react';
import { useState } from 'react';
import { TabType } from '../types';

interface HeaderProps {
  activeTab: TabType;
  onTabChange: (tab: TabType) => void;
}

export function Header({ activeTab, onTabChange }: HeaderProps) {
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  const navItems = [
    { id: 'home' as TabType, label: 'Home', icon: <LayoutDashboard className="w-4 h-4" /> },
    { id: 'pdfs' as TabType, label: 'Study PDFs', icon: <FileText className="w-4 h-4" /> },
    { id: 'tests' as TabType, label: 'Mock Tests', icon: <BookOpen className="w-4 h-4" /> },
    { id: 'connect' as TabType, label: 'Connect', icon: <Share2 className="w-4 h-4" /> },
  ];

  return (
    <header className="sticky top-0 z-50 w-full bg-white border-b-4 border-indigo-900 relative">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-24 md:h-28">
          {/* Logo */}
          <div 
            className="flex items-center gap-4 md:gap-6 cursor-pointer" 
            onClick={() => onTabChange('home')}
          >
            <div className="w-10 h-10 md:w-16 md:h-16 bg-indigo-900 flex items-center justify-center rounded-sm rotate-45 shadow-lg shrink-0 ml-2">
              <span className="-rotate-45 text-white font-bold text-lg md:text-2xl">PA</span>
            </div>
            <div>
              <h1 className="text-xl md:text-4xl font-extrabold tracking-tighter text-indigo-900 uppercase">Pratibimb Academy</h1>
              <p className="text-[9px] md:text-sm font-medium text-amber-600 tracking-widest uppercase">Rajasthan Competitive Exam Specialist</p>
            </div>
          </div>

          {/* Desktop Nav */}
          <nav className="hidden lg:flex items-center gap-2">
            {navItems.map((item) => (
              <button
                key={item.id}
                onClick={() => onTabChange(item.id)}
                className={`relative flex items-center gap-2 px-4 py-3 text-xs font-bold uppercase tracking-widest transition-colors ${
                  activeTab === item.id 
                    ? 'text-indigo-900' 
                    : 'text-slate-400 hover:text-indigo-600'
                }`}
              >
                {item.icon}
                {item.label}
                {activeTab === item.id && (
                  <motion.div
                    layoutId="activeTab"
                    className="absolute bottom-0 left-0 right-0 h-1 bg-amber-500"
                    transition={{ type: 'spring', bounce: 0.2, duration: 0.6 }}
                  />
                )}
              </button>
            ))}
          </nav>

          {/* Mobile menu button */}
          <div className="flex lg:hidden">
            <button
              onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
              className="p-2 rounded-sm text-slate-600 hover:text-indigo-900 hover:bg-slate-100 focus:outline-none"
            >
              {mobileMenuOpen ? <X className="w-6 h-6" /> : <Menu className="w-6 h-6" />}
            </button>
          </div>
        </div>
      </div>
      {/* Decorative line */}
      <div className="absolute bottom-0 right-0 w-1/3 h-1 bg-amber-500 hidden md:block"></div>

      {/* Mobile Nav */}
      {mobileMenuOpen && (
        <motion.div 
          initial={{ opacity: 0, y: -10 }}
          animate={{ opacity: 1, y: 0 }}
          className="lg:hidden border-t-2 border-slate-200 bg-white"
        >
          <div className="px-4 py-4 space-y-2">
            {navItems.map((item) => (
              <button
                key={item.id}
                onClick={() => {
                  onTabChange(item.id);
                  setMobileMenuOpen(false);
                }}
                className={`flex items-center gap-3 w-full px-4 py-3 rounded-sm text-sm font-bold uppercase tracking-widest ${
                  activeTab === item.id
                    ? 'text-indigo-900 bg-indigo-50 border-l-4 border-indigo-900'
                    : 'text-slate-500 hover:text-indigo-900 hover:bg-slate-50'
                }`}
              >
                {item.icon}
                {item.label}
              </button>
            ))}
          </div>
        </motion.div>
      )}
    </header>
  );
}