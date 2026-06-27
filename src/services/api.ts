import axios from 'axios';

const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:5000/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add token to requests
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Auth
export const authAPI = {
  register: (name: string, email: string, password: string) =>
    api.post('/auth/register', { name, email, password }),
  login: (email: string, password: string) =>
    api.post('/auth/login', { email, password }),
};

// Quiz
export const quizAPI = {
  getAll: () => api.get('/quiz'),
  getById: (id: string) => api.get(`/quiz/${id}`),
  submit: (id: string, answers: string[]) =>
    api.post(`/quiz/${id}/submit`, { answers }),
  getResults: () => api.get('/quiz/results/my'),
};

// PDF
export const pdfAPI = {
  getAll: (exam?: string, subject?: string) =>
    api.get('/pdf', { params: { exam, subject } }),
  getById: (id: string) => api.get(`/pdf/${id}`),
  addFavorite: (id: string) => api.post(`/pdf/${id}/favorite`),
};

// User
export const userAPI = {
  getProfile: () => api.get('/user/profile'),
  updateProfile: (data: any) => api.put('/user/profile', data),
  getStats: () => api.get('/user/stats'),
};

export default api;