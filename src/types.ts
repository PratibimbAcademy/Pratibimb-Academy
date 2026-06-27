export type TabType = 'home' | 'pdfs' | 'tests' | 'connect';

export interface Exam {
  id: string;
  name: string;
  description: string;
}

export const EXAMS: Exam[] = [
  { id: 'reet', name: 'REET', description: 'Rajasthan Eligibility Examination for Teachers' },
  { id: 'cet', name: 'CET', description: 'Common Eligibility Test (Graduation & Sr. Secondary)' },
  { id: 'vdo', name: 'VDO', description: 'Village Development Officer' },
  { id: 'ptet', name: 'PTET', description: 'Pre-Teacher Education Test' },
  { id: 'bstc', name: 'BSTC', description: 'Basic School Teaching Certificate (Pre-D.El.Ed)' },
  { id: 'patwar', name: 'Patwar', description: 'Rajasthan Patwari Examination' },
  { id: 'police', name: 'Rajasthan Police', description: 'Constable & Sub-Inspector Exams' },
];

export const LINKS = {
  pdfs: 'https://drive.google.com/drive/folders/1sMUfW66aRW0huisfkmUQ8uUQYyCS6eld',
  tests: 'https://drive.google.com/drive/folders/16owOMMAOqRtYNO6pGjiKq_jDT7KZ9wl0',
  youtube: 'https://youtube.com/@pratibimbacademy?si=MWJB4uZawf7g83BE',
  telegram: 'https://t.me/Pratibimb_Academy',
  twitter: 'https://x.com/Subodh_btp?t=fDBktE6Pj5O88nkQo8Kt3Q&s=09'
};