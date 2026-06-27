import { motion } from 'motion/react';
import { ExternalLink, FileText, DownloadCloud } from 'lucide-react';
import { LINKS } from '../types';

export function PdfLayer() {
  return (
    <motion.div 
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      exit={{ opacity: 0, y: -20 }}
      className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12 md:py-20"
    >
      <div className="text-center mb-16">
        <div className="w-16 h-16 bg-indigo-900 text-white flex items-center justify-center mx-auto mb-6 rotate-12 shadow-lg">
          <FileText className="w-8 h-8 -rotate-12" />
        </div>
        <h2 className="text-3xl md:text-5xl font-extrabold tracking-tighter uppercase text-indigo-900 mb-4">
          Study Materials
        </h2>
        <div className="w-24 h-1 bg-amber-500 mx-auto mb-6"></div>
        <p className="text-slate-500 text-lg max-w-2xl mx-auto">
          Access our comprehensive collection of handwritten notes, syllabus guides, and chapter-wise study materials tailored for all Rajasthan exams.
        </p>
      </div>

      <div className="bg-white border-2 border-slate-200 shadow-xl overflow-hidden flex flex-col md:flex-row">
        <div className="p-8 md:p-12 flex-1 space-y-6">
          <h3 className="text-2xl font-extrabold uppercase tracking-tight text-slate-900">Official Drive Folder</h3>
          <p className="text-slate-500 leading-relaxed">
            All our PDFs are organized securely in a Google Drive folder. You can view them online or download them directly to your device for offline studying.
          </p>
          <ul className="text-slate-600 text-sm space-y-3 font-medium">
            <li className="flex items-center gap-3">
              <div className="p-1 bg-amber-100 text-amber-600 rounded-sm">
                <DownloadCloud className="w-4 h-4"/>
              </div>
              Organized by subject and exam
            </li>
            <li className="flex items-center gap-3">
              <div className="p-1 bg-amber-100 text-amber-600 rounded-sm">
                <DownloadCloud className="w-4 h-4"/>
              </div>
              High-quality scanned notes
            </li>
            <li className="flex items-center gap-3">
              <div className="p-1 bg-amber-100 text-amber-600 rounded-sm">
                <DownloadCloud className="w-4 h-4"/>
              </div>
              Free to access and download
            </li>
          </ul>
        </div>
        
        <div className="bg-indigo-50 md:w-1/3 border-t-2 md:border-t-0 md:border-l-2 border-slate-200 p-8 md:p-12 flex flex-col items-center justify-center text-center">
          <div className="w-20 h-20 bg-indigo-900 text-white rounded-sm flex items-center justify-center mb-6 shadow-md">
            <svg className="w-10 h-10" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M7 21h10a2 2 0 002-2V9.414a1 1 0 00-.293-.707l-5.414-5.414A1 1 0 0012.586 3H7a2 2 0 00-2 2v14a2 2 0 002 2z"></path></svg>
          </div>
          <a 
            href={LINKS.pdfs}
            target="_blank"
            rel="noopener noreferrer"
            className="w-full px-8 py-4 bg-indigo-900 text-white font-bold uppercase tracking-wider text-sm border-b-4 border-indigo-950 shadow-xl active:translate-y-1 transition-all flex items-center justify-center gap-2"
          >
            Open Folder
            <ExternalLink className="w-4 h-4" />
          </a>
        </div>
      </div>
      
      <div className="mt-8 bg-amber-50 border-l-4 border-amber-500 p-6 flex items-start gap-4 text-sm text-slate-700 shadow-sm">
        <div className="p-2 bg-amber-200 text-amber-800 shrink-0">
          <svg className="w-5 h-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
        </div>
        <p className="mt-1">
          <strong className="uppercase tracking-wide text-amber-900 font-bold text-xs">Pro Tip:</strong><br/> Ensure you are signed into your Google account for the best experience. If a folder appears empty, wait a few seconds for it to load.
        </p>
      </div>
    </motion.div>
  );
}