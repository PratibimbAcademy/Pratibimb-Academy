import { motion } from 'motion/react';
import { Youtube, Twitter, Send, Mail } from 'lucide-react';
import { LINKS } from '../types';

export function ConnectLayer() {
  const socials = [
    {
      name: 'YouTube',
      description: 'Subscribe for live classes, strategy videos, and daily current affairs.',
      icon: <Youtube className="w-8 h-8" />,
      link: LINKS.youtube,
      color: 'bg-white text-red-600 border-slate-200 hover:border-red-600 hover:shadow-xl',
      badge: 'YT'
    },
    {
      name: 'Telegram',
      description: 'Join our channel for instant updates, daily quizzes, and fast PDF sharing.',
      icon: <Send className="w-8 h-8" />,
      link: LINKS.telegram,
      color: 'bg-white text-blue-500 border-slate-200 hover:border-blue-500 hover:shadow-xl',
      badge: 'TG'
    },
    {
      name: 'Twitter (X)',
      description: 'Follow Subodh for educational updates and exam notifications.',
      icon: <Twitter className="w-8 h-8" />,
      link: LINKS.twitter,
      color: 'bg-white text-slate-900 border-slate-200 hover:border-slate-900 hover:shadow-xl',
      badge: 'TW'
    }
  ];

  return (
    <motion.div 
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      exit={{ opacity: 0, y: -20 }}
      className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12 md:py-20"
    >
      <div className="text-center mb-16">
        <h2 className="text-3xl md:text-5xl font-extrabold text-indigo-900 tracking-tighter uppercase mb-4">
          Connect With Us
        </h2>
        <div className="w-24 h-1 bg-amber-500 mx-auto mb-6"></div>
        <p className="text-slate-500 text-lg max-w-2xl mx-auto">
          Join our growing community of thousands of aspirants. Stay updated with the latest study materials and classes.
        </p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
        {socials.map((social, index) => (
          <motion.a
            key={social.name}
            href={social.link}
            target="_blank"
            rel="noopener noreferrer"
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: index * 0.1 }}
            className={`group flex flex-col p-8 border-2 transition-all duration-300 relative ${social.color}`}
          >
            <div className="absolute top-0 right-0 bg-slate-100 text-slate-400 font-bold text-[10px] tracking-widest px-3 py-1 group-hover:bg-slate-800 group-hover:text-white transition-colors">
              {social.badge}
            </div>
            <div className="mb-6 transition-transform duration-300 group-hover:-translate-y-1">
              {social.icon}
            </div>
            <h3 className="text-xl font-extrabold uppercase tracking-tight text-slate-900 mb-2">{social.name}</h3>
            <p className="text-sm text-slate-500 leading-relaxed group-hover:text-slate-700">
              {social.description}
            </p>
          </motion.a>
        ))}
      </div>
      
      {/* Contact Form / Email section */}
      <div className="mt-16 bg-indigo-900 text-white border-b-4 border-indigo-950 p-8 md:p-12 text-center shadow-xl">
         <div className="w-16 h-16 bg-white/10 flex items-center justify-center mx-auto mb-6 text-amber-400">
           <Mail className="w-8 h-8" />
         </div>
         <h3 className="text-2xl font-extrabold tracking-tight uppercase mb-4">Have Questions?</h3>
         <p className="text-indigo-200 mb-8 max-w-lg mx-auto">
           If you need guidance for a specific exam or have inquiries regarding our study materials, feel free to drop an email.
         </p>
         <a 
           href="mailto:subodhkumar062005@gmail.com" 
           className="inline-flex items-center justify-center gap-2 px-8 py-4 bg-amber-500 text-indigo-950 font-bold uppercase tracking-wider text-sm shadow-xl hover:bg-amber-400 active:translate-y-1 transition-all"
         >
           Email Us
           <Mail className="w-4 h-4" />
         </a>
      </div>
    </motion.div>
  );
}