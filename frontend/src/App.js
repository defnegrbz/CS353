import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import WorkoutPage from './components/workout/WorkoutsPage';
import WorkoutCreatePage from './components/workout/WorkoutCreatePage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/workouts" element={<WorkoutPage />} />
        <Route path="/workouts/:trainerID/createWorkout" element={<WorkoutCreatePage />} />
      </Routes>
    </Router>
  );
}

export default App;
