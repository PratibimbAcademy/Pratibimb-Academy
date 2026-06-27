import { MapPin, Phone, Mail, Instagram, Facebook, Twitter } from 'lucide-react';

export default function Footer() {
  return (
    <footer className="bg-slate-900 text-slate-300">
      <div className="mx-auto max-w-7xl px-6 pb-8 pt-16 sm:pt-24 lg:px-8 lg:pt-32">
        <div className="xl:grid xl:grid-cols-3 xl:gap-8">
          <div className="space-y-8">
            <span className="font-serif font-bold text-2xl text-white">
              Pratibimb Academy
            </span>
            <p className="text-sm leading-6 max-w-xs">
              Empowering students with knowledge, skills, and values to excel in their academic and professional journeys.
            </p>
            <div className="flex space-x-6">
              <a href="#" className="hover:text-white transition-colors">
                <span className="sr-only">Facebook</span>
                <Facebook className="h-6 w-6" />
              </a>
              <a href="#" className="hover:text-white transition-colors">
                <span className="sr-only">Instagram</span>
                <Instagram className="h-6 w-6" />
              </a>
              <a href="#" className="hover:text-white transition-colors">
                <span className="sr-only">Twitter</span>
                <Twitter className="h-6 w-6" />
              </a>
            </div>
          </div>
          <div className="mt-16 grid grid-cols-2 gap-8 xl:col-span-2 xl:mt-0">
            <div className="md:grid md:grid-cols-2 md:gap-8">
              <div>
                <h3 className="text-sm font-semibold leading-6 text-white uppercase tracking-wider">Quick Links</h3>
                <ul role="list" className="mt-6 space-y-4">
                  <li>
                    <a href="#home" className="text-sm leading-6 hover:text-white transition-colors">Home</a>
                  </li>
                  <li>
                    <a href="#about" className="text-sm leading-6 hover:text-white transition-colors">About Us</a>
                  </li>
                  <li>
                    <a href="#courses" className="text-sm leading-6 hover:text-white transition-colors">Courses</a>
                  </li>
                  <li>
                    <a href="#testimonials" className="text-sm leading-6 hover:text-white transition-colors">Success Stories</a>
                  </li>
                </ul>
              </div>
              <div className="mt-10 md:mt-0">
                <h3 className="text-sm font-semibold leading-6 text-white uppercase tracking-wider">Programs</h3>
                <ul role="list" className="mt-6 space-y-4">
                  <li>
                    <a href="#" className="text-sm leading-6 hover:text-white transition-colors">Foundation Classes</a>
                  </li>
                  <li>
                    <a href="#" className="text-sm leading-6 hover:text-white transition-colors">Board Preparation</a>
                  </li>
                  <li>
                    <a href="#" className="text-sm leading-6 hover:text-white transition-colors">JEE / NEET Coaching</a>
                  </li>
                  <li>
                    <a href="#" className="text-sm leading-6 hover:text-white transition-colors">Crash Courses</a>
                  </li>
                </ul>
              </div>
            </div>
            <div>
              <h3 className="text-sm font-semibold leading-6 text-white uppercase tracking-wider">Contact Us</h3>
              <ul role="list" className="mt-6 space-y-4">
                <li className="flex gap-x-3">
                  <MapPin className="h-5 w-5 flex-none text-slate-400" />
                  <span className="text-sm leading-6">123 Education Hub, Knowledge Park<br />New Delhi, India 110001</span>
                </li>
                <li className="flex gap-x-3">
                  <Phone className="h-5 w-5 flex-none text-slate-400" />
                  <span className="text-sm leading-6">+91 98765 43210</span>
                </li>
                <li className="flex gap-x-3">
                  <Mail className="h-5 w-5 flex-none text-slate-400" />
                  <span className="text-sm leading-6">admissions@pratibimbacademy.com</span>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div className="mt-16 border-t border-white/10 pt-8 sm:mt-20 lg:mt-24">
          <p className="text-xs leading-5 text-slate-400 text-center">
            &copy; {new Date().getFullYear()} Pratibimb Academy. All rights reserved.
          </p>
        </div>
      </div>
    </footer>
  );
}