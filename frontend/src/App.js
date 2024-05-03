import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import WorkoutPage from './components/workout/WorkoutsPage';
import WorkoutCreatePage from './components/workout/WorkoutCreatePage';
import MemberLoginPage from './auth/MemberLogin';
import TrainerLoginPage from './auth/TrainerLogin';
import TrainerRegisterPage from './auth/RegisterTrainer';
import MemberRegisterPage from './auth/RegisterMember';
import HealthDataCreatePage from './components/workout/HealthDataCreatePage';
import HealthDataPage from './components/workout/HealthDataPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/workouts" element={<WorkoutPage />} />
        <Route path="/workouts/:trainerID/createWorkout" element={<WorkoutCreatePage />} />
        <Route path="/users/login/member-login" element={<MemberLoginPage />} />
        <Route path="/users/login/trainer-login" element={<TrainerLoginPage />} />
        <Route path="/users/register/trainer" element={<TrainerRegisterPage />} />
        <Route path="/users/register/member" element={<MemberRegisterPage />} />
        <Route path="/healthdatas/:userId/create" element={<HealthDataCreatePage />} />
        <Route path="/healthdatas/:userId" element={<HealthDataPage />} />
      </Routes>
    </Router>
  );
}

export default App;
