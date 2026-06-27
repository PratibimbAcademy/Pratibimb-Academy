import { ArrowRight, Clock, Users } from 'lucide-react';

const courses = [
  {
    title: 'Foundation Program',
    category: 'Class 8-10',
    description: 'Build a strong academic foundation early on. Focuses on conceptual clarity and critical thinking.',
    duration: '1 Year',
    students: 'Batch of 30',
    image: 'https://images.unsplash.com/photo-1427504494785-3a9ca7044f45?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80'
  },
  {
    title: 'Board Excellence',
    category: 'Class 11-12',
    description: 'Comprehensive preparation for board examinations with regular mock tests and personalized feedback.',
    duration: '1-2 Years',
    students: 'Batch of 40',
    image: 'https://images.unsplash.com/photo-1453733190371-0a9bedd82893?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80'
  },
  {
    title: 'Competitive Prep',
    category: 'JEE / NEET',
    description: 'Rigorous coaching for medical and engineering entrance exams by top-tier faculty members.',
    duration: '2 Years',
    students: 'Batch of 50',
    image: 'https://images.unsplash.com/photo-1434030216411-0b793f4b4173?ixlib=rb-4.0.3&auto=format&fit=crop&w=600&q=80'
  }
];

export default function Courses() {
  return (
    <div id="courses" className="bg-slate-50 py-24 sm:py-32">
      <div className="mx-auto max-w-7xl px-6 lg:px-8">
        <div className="mx-auto max-w-2xl lg:text-center mb-16">
          <h2 className="text-base font-semibold leading-7 text-blue-600 uppercase tracking-wide">Our Programs</h2>
          <p className="mt-2 text-3xl font-serif font-bold tracking-tight text-slate-900 sm:text-4xl">
            Discover Your Perfect Path
          </p>
          <p className="mt-6 text-lg leading-8 text-slate-600">
            Choose from our range of meticulously designed programs tailored to different educational stages and goals.
          </p>
        </div>
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
          {courses.map((course) => (
            <div key={course.title} className="bg-white rounded-2xl shadow-sm border border-slate-100 overflow-hidden hover:shadow-md transition-shadow flex flex-col">
              <div className="relative h-48">
                <img src={course.image} alt={course.title} className="w-full h-full object-cover" />
                <div className="absolute top-4 left-4 bg-white/90 backdrop-blur-sm px-3 py-1 rounded-full text-xs font-semibold text-slate-900">
                  {course.category}
                </div>
              </div>
              <div className="p-8 flex flex-col flex-grow">
                <h3 className="font-serif text-xl font-bold text-slate-900 mb-3">{course.title}</h3>
                <p className="text-slate-600 text-sm leading-relaxed mb-6 flex-grow">{course.description}</p>
                
                <div className="flex items-center justify-between text-sm text-slate-500 mb-6 pt-6 border-t border-slate-100">
                  <div className="flex items-center gap-2">
                    <Clock className="w-4 h-4" />
                    <span>{course.duration}</span>
                  </div>
                  <div className="flex items-center gap-2">
                    <Users className="w-4 h-4" />
                    <span>{course.students}</span>
                  </div>
                </div>
                
                <button className="w-full inline-flex items-center justify-center gap-2 px-4 py-2.5 text-sm font-medium text-blue-600 bg-blue-50 rounded-lg hover:bg-blue-100 transition-colors">
                  View Details
                  <ArrowRight className="w-4 h-4" />
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}