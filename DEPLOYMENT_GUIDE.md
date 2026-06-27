# Pratibimb Academy - Complete Setup Guide

## 🚀 Deployment Status

✅ **Frontend:** Deployed on Vercel
✅ **Backend:** Ready for Railway.app deployment
✅ **Database:** MongoDB Atlas setup guide below
✅ **Authentication:** JWT + Bcrypt implemented
✅ **Admin Panel:** Ready to use
✅ **Analytics:** Dashboard implemented

---

## 📋 Quick Setup Guide

### Step 1: MongoDB Atlas (Free)

1. Go to [mongodb.com/cloud/atlas](https://mongodb.com/cloud/atlas)
2. Sign up with GitHub (Easy!)
3. Create a free cluster
4. Get connection string:
   ```
   mongodb+srv://username:password@cluster.mongodb.net/pratibimb-academy?retryWrites=true&w=majority
   ```
5. Add IP whitelist: **0.0.0.0/0** (for development)

### Step 2: Gemini API

1. Go to [ai.google.dev](https://ai.google.dev)
2. Click "Get API Key"
3. Create new project or use existing
4. Copy API key

### Step 3: Railway.app Deployment

1. Go to [railway.app](https://railway.app)
2. Sign up with GitHub
3. Click "New Project" → "Deploy from GitHub"
4. Select `PratibimbAcademy/Pratibimb-Academy`
5. Add Environment Variables:
   ```
   MONGODB_URI=mongodb+srv://...
   JWT_SECRET=super-secret-key-123
   GEMINI_API_KEY=your-api-key
   PORT=5000
   NODE_ENV=production
   ```
6. Deploy!

### Step 4: Update Vercel

1. Go to [vercel.com](https://vercel.com)
2. Select your project
3. Settings → Environment Variables
4. Add:
   ```
   REACT_APP_API_URL=https://your-railway-domain.railway.app
   ```
5. Redeploy

---

## 🎯 API Endpoints

### Authentication
- `POST /api/auth/register` - Register
- `POST /api/auth/login` - Login

### Quiz
- `GET /api/quiz` - Get all quizzes
- `POST /api/quiz/:id/submit` - Submit answers
- `GET /api/quiz/results/my` - Get results

### PDF
- `GET /api/pdf` - Get all PDFs
- `POST /api/pdf/:id/favorite` - Add favorite

### Dashboard
- `GET /api/user/stats` - Get user statistics

### Admin
- `POST /api/admin/pdf` - Upload PDF
- `POST /api/admin/quiz` - Create quiz

---

## 🔐 Default Admin Account

Email: `admin@pratibimbacademy.com`
Password: Set in `JWT_SECRET` during deployment

To create admin account:
1. Register normally
2. Update in MongoDB:
   ```javascript
   db.users.updateOne(
     { email: 'admin@pratibimbacademy.com' },
     { $set: { role: 'admin' } }
   )
   ```

---

## 📊 Project Structure

```
Pratibimb-Academy/
├── src/
│   ├── components/       # React components
│   ├── pages/            # Page components
│   ├── services/         # API services
│   ├── store/            # Zustand stores
│   └── App.tsx
├── routes/               # Express routes
├── models/               # MongoDB schemas
├── middleware/           # Auth middleware
├── server.ts             # Express server
├── package.json
└── README.md
```

---

## 🧪 Testing

### Local Testing
```bash
npm install
npm run dev              # Backend
npm run client:dev       # Frontend
```

### Production Testing
1. Visit: `https://your-vercel-domain.vercel.app`
2. Register account
3. Login
4. Check dashboard
5. Try quiz
6. Check admin panel

---

## 🐛 Troubleshooting

### MongoDB Connection Failed
- Check IP whitelist in Atlas
- Verify connection string
- Check username/password

### API 401 Error
- Check JWT token in localStorage
- Verify JWT_SECRET matches
- Re-login

### CORS Error
- Update backend CORS settings
- Check frontend API URL

---

## 📞 Support

Email: `subodhkumar062005@gmail.com`
GitHub: `https://github.com/PratibimbAcademy`

---

## ✅ Checklist

- [ ] MongoDB Atlas account created
- [ ] Gemini API key generated
- [ ] Railway.app account created
- [ ] Backend deployed on Railway
- [ ] Frontend deployed on Vercel
- [ ] Environment variables configured
- [ ] Login working
- [ ] Dashboard loading
- [ ] Quizzes visible
- [ ] PDFs accessible
- [ ] Admin panel functional

---

**Happy Learning! 🎓**
