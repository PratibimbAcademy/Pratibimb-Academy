import { BookOpen, Target, Users, Award } from 'lucide-react';

const features = [
  {
    name: 'Expert Faculty',
    description: 'Learn from industry veterans and highly qualified educators dedicated to your success.',
    icon: Users,
  },
  {
    name: 'Comprehensive Curriculum',
    description: 'Structured programs designed to cover syllabus thoroughly while building foundational concepts.',
    icon: BookOpen,
  },
  {
    name: 'Personalized Attention',
    description: 'Small batch sizes ensure every student gets the focus and doubt-clearing sessions they need.',
    icon: Target,
  },
  {
    name: 'Proven Track Record',
    description: 'Consistently producing top rankers and successful professionals across various fields.',
    icon: Award,
  },
];

export default function Features() {
  return (
    <div id="about" className="py-24 bg-white sm:py-32">
      <div className="mx-auto max-w-7xl px-6 lg:px-8">
        <div className="mx-auto max-w-2xl lg:text-center">
          <h2 className="text-base font-semibold leading-7 text-blue-600 uppercase tracking-wide">Why Choose Us</h2>
          <p className="mt-2 text-3xl font-serif font-bold tracking-tight text-slate-900 sm:text-4xl">
            Everything you need to excel
          </p>
          <p className="mt-6 text-lg leading-8 text-slate-600">
            At Pratibimb Academy, we go beyond traditional teaching. Our holistic approach ensures that students are not just prepared for exams, but for life.
          </p>
        </div>
        <div className="mx-auto mt-16 max-w-2xl sm:mt-20 lg:mt-24 lg:max-w-none">
          <dl className="grid max-w-xl grid-cols-1 gap-x-8 gap-y-16 lg:max-w-none lg:grid-cols-4">
            {features.map((feature) => (
              <div key={feature.name} className="flex flex-col items-center text-center">
                <div className="mb-6 flex h-16 w-16 items-center justify-center rounded-2xl bg-blue-50">
                  <feature.icon className="h-8 w-8 text-blue-600" aria-hidden="true" />
                </div>
                <dt className="text-xl font-semibold leading-7 text-slate-900">
                  {feature.name}
                </dt>
                <dd className="mt-4 flex flex-auto flex-col text-base leading-7 text-slate-600">
                  <p className="flex-auto">{feature.description}</p>
                </dd>
              </div>
            ))}
          </dl>
        </div>
      </div>
    </div>
  );
}