/**
 * @license
 * SPDX-License-Identifier: Apache-2.0
 */

import { useState } from 'react';
import Navbar from './components/Navbar';
import Hero from './components/Hero';
import Features from './components/Features';
import Courses from './components/Courses';
import Footer from './components/Footer';
import JokeGenerator from './components/JokeGenerator';

export default function App() {
  const [showJokeGenerator, setShowJokeGenerator] = useState(false);

  return (
    <div className="min-h-screen bg-white font-sans selection:bg-blue-100 selection:text-blue-900">
      <Navbar onJokeClick={() => setShowJokeGenerator(!showJokeGenerator)} />
      
      {/* Joke Generator Modal */}
      {showJokeGenerator && (
        <div className="fixed inset-0 bg-black/50 backdrop-blur-sm z-40 flex items-center justify-center p-4">
          <div className="relative max-w-md w-full">
            <button
              onClick={() => setShowJokeGenerator(false)}
              className="absolute -top-4 -right-4 bg-white rounded-full p-2 shadow-lg hover:bg-slate-100 z-50"
            >
              ✕
            </button>
            <JokeGenerator />
          </div>
        </div>
      )}

      <main>
        <Hero />
        <Features />
        <Courses />
      </main>
      <Footer />
    </div>
  );
}
