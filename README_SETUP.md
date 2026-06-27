# Pratibimb Academy - Complete Setup Guide

## 🚀 Project Structure

```
Pratibimb-Academy/
├── server.ts                 # Express backend
├── routes/                   # API routes
│   ├── auth.ts
│   ├── quiz.ts
│   ├── pdf.ts
│   ├── user.ts
│   ├── admin.ts
│   └── analytics.ts
├── models/                   # MongoDB schemas
│   ├── User.ts
│   ├── Quiz.ts
│   ├── QuizResult.ts
│   ├── PDF.ts
│   └── Analytics.ts
├── middleware/               # Auth & security
├── src/                      # React frontend
│   ├── pages/
│   │   ├── Dashboard.tsx     # User dashboard
│   │   └── AdminPanel.tsx    # Admin interface
│   ├── components/           # React components
│   └── App.tsx
└── package.json
```

## 📦 Installation

### 1. Install Dependencies
```bash
npm install
```

### 2. Setup MongoDB
- Create account at [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)
- Get connection string
- Add to `.env.local`

### 3. Get Gemini API Key
- Go to [Google AI Studio](https://ai.google.dev/)
- Generate API key
- Add to `.env.local`

### 4. Environment Variables
Copy `.env.local` and fill in:
```
MONGODB_URI=your-mongodb-connection
JWT_SECRET=your-secret-key
GEMINI_API_KEY=your-gemini-key
PORT=5000
```

## 🎯 Features Implemented

### ✅ Backend (Express + MongoDB)
- User authentication (Register/Login with JWT)
- Quiz management & scoring
- PDF organization & downloads
- Admin panel for content management
- Analytics & user tracking
- Study progress monitoring

### ✅ Frontend (React + Tailwind)
- User dashboard with stats
- Admin panel
- Quiz interface
- PDF viewer
- User authentication
- Responsive design

### ✅ Database (MongoDB)
- User profiles with roles (admin/student)
- Quiz questions & results
- PDF metadata & favorites
- Analytics events

### ✅ Security
- JWT token authentication
- Password hashing (bcrypt)
- Role-based access control
- Admin middleware

## 🚀 Running the Project

### Development Mode
```bash
# Terminal 1 - Backend
npm run dev

# Terminal 2 - Frontend
npm run client:dev
```

### Production Build
```bash
npm run build
npm run start
```

## 📊 API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user

### Quiz
- `GET /api/quiz` - Get all quizzes
- `GET /api/quiz/:id` - Get quiz details
- `POST /api/quiz/:id/submit` - Submit quiz answers
- `GET /api/quiz/results/my` - Get user's results

### PDF
- `GET /api/pdf` - Get all PDFs
- `GET /api/pdf/:id` - Get PDF details
- `POST /api/pdf/:id/favorite` - Add to favorites

### User
- `GET /api/user/profile` - Get profile
- `PUT /api/user/profile` - Update profile
- `GET /api/user/stats` - Get user stats

### Admin
- `POST /api/admin/pdf` - Upload PDF
- `POST /api/admin/quiz` - Create quiz
- `GET /api/admin/users` - Get all users
- `DELETE /api/admin/pdf/:id` - Delete PDF

### Analytics
- `GET /api/analytics/dashboard` - Get dashboard stats
- `GET /api/analytics/user/:userId` - Get user analytics

## 🔧 Configuration

### Database Indexes
```javascript
// User
db.users.createIndex({ email: 1 })

// QuizResult
db.quizresults.createIndex({ userId: 1, quizId: 1 })

// PDF
db.pdfs.createIndex({ exam: 1, subject: 1 })
```

### Default Admin Account
```
Email: admin@pratibimbacademy.com
Password: (Set in .env.local)
```

## 📈 Next Steps

1. **Gemini AI Integration** - Implement doubt solving chatbot
2. **Payment Gateway** - Add premium content
3. **Email Notifications** - Send progress updates
4. **Mobile App** - React Native version
5. **Live Classes** - Video streaming integration
6. **Leaderboard** - Competitive scoring system

## 🐛 Troubleshooting

### MongoDB Connection Issues
- Check connection string in `.env.local`
- Ensure IP whitelist includes your address
- Verify network connectivity

### API Errors
- Check console for error messages
- Verify JWT token in Authorization header
- Check database indexes

### Build Issues
- Clear `node_modules` and reinstall
- Update Node.js to latest version
- Clear Vite cache: `rm -rf dist`

## 📞 Support
For issues: subodhkumar062005@gmail.com
