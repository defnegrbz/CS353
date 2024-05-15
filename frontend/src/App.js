import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import WorkoutPage from './components/workout/WorkoutsPage';
import WorkoutCreatePage from './components/workout/WorkoutCreatePage';
import MemberLoginPage from './components/auth/MemberLogin';
import TrainerLoginPage from './components/auth/TrainerLogin';
import TrainerRegisterPage from './components/auth/RegisterTrainer';
import MemberRegisterPage from './components/auth/RegisterMember';
import HealthDataCreatePage from './components/users/HealthDataCreatePage';
import HealthDataPage from './components/users/HealthDataPage';
import InitialPage from './components/auth/InitialPage';


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
        <Route path="/nutritionalPlan/:userId" element={<HealthDataPage />} />
        <Route path="/" element={<InitialPage />} />
      </Routes>
    </Router>
  );
}

export default App;
