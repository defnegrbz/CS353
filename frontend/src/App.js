import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './components/auth/LoginPage';
import WorkoutPage from './components/workout/WorkoutsPage';
import WorkoutCreatePage from './components/workout/WorkoutCreatePage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/api/login" element={<LoginPage />} />
        <Route path="/api/workouts" element={<WorkoutPage />} />
        <Route path="/api/createWorkout" element={<WorkoutCreatePage />} />
      </Routes>
    </Router>
  );
}

export default App;
