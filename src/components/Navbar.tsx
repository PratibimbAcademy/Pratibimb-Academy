import { BookOpen, Menu, X } from 'lucide-react';
import { useState } from 'react';

export default function Navbar() {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <nav className="bg-white shadow-sm fixed w-full z-50 top-0">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-20">
          <div className="flex items-center">
            <div className="flex-shrink-0 flex items-center gap-2 cursor-pointer">
              <div className="bg-blue-600 text-white p-2 rounded-lg">
                <BookOpen className="h-6 w-6" />
              </div>
              <span className="font-serif font-bold text-2xl text-slate-900">
                Pratibimb Academy
              </span>
            </div>
          </div>
          
          <div className="hidden md:flex items-center space-x-8">
            <a href="#home" className="text-slate-600 hover:text-blue-600 font-medium transition-colors">Home</a>
            <a href="#about" className="text-slate-600 hover:text-blue-600 font-medium transition-colors">About Us</a>
            <a href="#courses" className="text-slate-600 hover:text-blue-600 font-medium transition-colors">Courses</a>
            <a href="#testimonials" className="text-slate-600 hover:text-blue-600 font-medium transition-colors">Success Stories</a>
            <button className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2.5 rounded-full font-medium transition-colors">
              Enroll Now
            </button>
          </div>

          <div className="flex items-center md:hidden">
            <button
              onClick={() => setIsOpen(!isOpen)}
              className="text-slate-600 hover:text-slate-900 focus:outline-none"
            >
              {isOpen ? <X className="h-6 w-6" /> : <Menu className="h-6 w-6" />}
            </button>
          </div>
        </div>
      </div>

      {/* Mobile menu */}
      {isOpen && (
        <div className="md:hidden bg-white border-t border-slate-100">
          <div className="px-2 pt-2 pb-3 space-y-1 sm:px-3">
            <a href="#home" className="block px-3 py-2 text-slate-600 hover:bg-slate-50 hover:text-blue-600 rounded-md font-medium">Home</a>
            <a href="#about" className="block px-3 py-2 text-slate-600 hover:bg-slate-50 hover:text-blue-600 rounded-md font-medium">About Us</a>
            <a href="#courses" className="block px-3 py-2 text-slate-600 hover:bg-slate-50 hover:text-blue-600 rounded-md font-medium">Courses</a>
            <a href="#testimonials" className="block px-3 py-2 text-slate-600 hover:bg-slate-50 hover:text-blue-600 rounded-md font-medium">Success Stories</a>
            <button className="w-full mt-4 bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-md font-medium transition-colors text-left">
              Enroll Now
            </button>
          </div>
        </div>
      )}
    </nav>
  );
}