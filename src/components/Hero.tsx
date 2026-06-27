import { ArrowRight, GraduationCap, Users, Trophy } from 'lucide-react';

export default function Hero() {
  return (
    <div id="home" className="relative bg-slate-50 pt-20 pb-16 lg:pt-32 lg:pb-28 overflow-hidden">
      {/* Decorative background elements */}
      <div className="absolute top-0 right-0 -translate-y-12 translate-x-1/3">
        <div className="w-96 h-96 bg-blue-100 rounded-full mix-blend-multiply filter blur-3xl opacity-50 animate-blob" />
      </div>
      <div className="absolute top-0 left-0 -translate-y-12 -translate-x-1/3">
        <div className="w-96 h-96 bg-indigo-100 rounded-full mix-blend-multiply filter blur-3xl opacity-50 animate-blob animation-delay-2000" />
      </div>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 relative z-10">
        <div className="lg:grid lg:grid-cols-12 lg:gap-8 items-center">
          <div className="sm:text-center md:max-w-2xl md:mx-auto lg:col-span-6 lg:text-left">
            <div className="inline-flex items-center px-4 py-2 rounded-full bg-blue-50 text-blue-700 font-medium text-sm mb-6 border border-blue-100">
              <span className="flex h-2 w-2 rounded-full bg-blue-600 mr-2"></span>
              Admissions Open for 2026-2027
            </div>
            <h1 className="text-4xl tracking-tight font-serif font-extrabold text-slate-900 sm:text-5xl md:text-6xl lg:text-5xl xl:text-6xl">
              <span className="block">Illuminate Your Path to</span>
              <span className="block text-blue-600 mt-1">Excellence</span>
            </h1>
            <p className="mt-6 text-base text-slate-600 sm:text-lg sm:max-w-xl sm:mx-auto md:text-xl lg:mx-0 leading-relaxed">
              At Pratibimb Academy, we nurture potential and shape futures. Experience world-class education with expert faculty, comprehensive curriculum, and a track record of remarkable success.
            </p>
            <div className="mt-8 sm:max-w-lg sm:mx-auto sm:text-center lg:text-left lg:mx-0 flex flex-col sm:flex-row gap-4 justify-center lg:justify-start">
              <button className="inline-flex items-center justify-center px-8 py-3.5 border border-transparent text-base font-medium rounded-full shadow-sm text-white bg-blue-600 hover:bg-blue-700 transition-colors duration-200">
                Explore Courses
                <ArrowRight className="ml-2 -mr-1 h-5 w-5" />
              </button>
              <button className="inline-flex items-center justify-center px-8 py-3.5 border-2 border-slate-200 text-base font-medium rounded-full text-slate-700 bg-transparent hover:bg-slate-50 hover:border-slate-300 transition-colors duration-200">
                Contact Us
              </button>
            </div>
          </div>
          <div className="mt-12 relative sm:max-w-lg sm:mx-auto lg:mt-0 lg:max-w-none lg:mx-0 lg:col-span-6 lg:flex lg:items-center">
            <div className="relative mx-auto w-full rounded-2xl shadow-xl lg:max-w-md overflow-hidden bg-white">
              <img
                className="w-full h-auto object-cover"
                src="https://images.unsplash.com/photo-1523050854058-8df90110c9f1?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80"
                alt="Students studying together"
              />
              <div className="absolute bottom-0 left-0 right-0 bg-gradient-to-t from-slate-900/90 to-transparent p-6 pt-24 text-white">
                <div className="flex items-center justify-between">
                  <div className="flex items-center gap-3">
                    <div className="bg-white/20 p-2 rounded-lg backdrop-blur-sm">
                      <Trophy className="h-6 w-6 text-yellow-400" />
                    </div>
                    <div>
                      <p className="font-bold text-xl">Top 1%</p>
                      <p className="text-slate-300 text-sm">Results Nationwide</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}