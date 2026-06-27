import { RefreshCw, Loader } from 'lucide-react';
import { useState, useEffect } from 'react';

interface Joke {
  type: string;
  setup?: string;
  delivery?: string;
  joke?: string;
}

export default function JokeGenerator() {
  const [joke, setJoke] = useState<Joke | null>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const fetchJoke = async () => {
    setLoading(true);
    setError(null);
    try {
      const response = await fetch(
        'https://v2.jokeapi.dev/joke/Any?type=single'
      );
      if (!response.ok) throw new Error('Failed to fetch joke');
      const data = await response.json();
      setJoke(data);
    } catch (err) {
      setError('Failed to load joke. Please try again.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchJoke();
  }, []);

  return (
    <div className="min-h-screen bg-gradient-to-br from-purple-50 via-blue-50 to-pink-50 flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        <div className="bg-white rounded-2xl shadow-xl p-8 md:p-12">
          {/* Header */}
          <div className="text-center mb-8">
            <h1 className="text-4xl font-serif font-bold text-slate-900 mb-2">
              😂 Joke Generator
            </h1>
            <p className="text-slate-600">Get a random laugh every time!</p>
          </div>

          {/* Joke Display */}
          <div className="bg-gradient-to-br from-purple-50 to-blue-50 rounded-xl p-8 mb-8 min-h-40 flex items-center justify-center">
            {loading ? (
              <div className="flex flex-col items-center justify-center">
                <Loader className="w-8 h-8 text-blue-600 animate-spin mb-3" />
                <p className="text-slate-600 font-medium">Loading a joke...</p>
              </div>
            ) : error ? (
              <p className="text-red-600 text-center font-medium">{error}</p>
            ) : joke ? (
              <div className="text-center">
                {joke.type === 'twopart' ? (
                  <>
                    <p className="text-lg text-slate-900 font-medium mb-4">
                      {joke.setup}
                    </p>
                    <p className="text-xl text-blue-600 font-bold">
                      {joke.delivery}
                    </p>
                  </>
                ) : (
                  <p className="text-xl text-slate-900 font-medium">
                    {joke.joke}
                  </p>
                )}
              </div>
            ) : null}
          </div>

          {/* Get New Joke Button */}
          <button
            onClick={fetchJoke}
            disabled={loading}
            className="w-full inline-flex items-center justify-center gap-2 px-6 py-3 bg-blue-600 hover:bg-blue-700 disabled:bg-blue-400 text-white font-semibold rounded-lg transition-colors duration-200 shadow-md hover:shadow-lg"
          >
            <RefreshCw className={`w-5 h-5 ${loading ? 'animate-spin' : ''}`} />
            {loading ? 'Loading...' : 'Get Another Joke'}
          </button>

          {/* API Info */}
          <p className="text-xs text-slate-500 text-center mt-6">
            Powered by <a href="https://jokeapi.dev" target="_blank" rel="noopener noreferrer" className="text-blue-600 hover:underline">JokeAPI</a>
          </p>
        </div>
      </div>
    </div>
  );
}
