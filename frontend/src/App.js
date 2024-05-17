import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import WorkoutPage from './components/workout/WorkoutsPage';
import WorkoutCreatePage from './components/workout/WorkoutCreatePage';
import UserLoginPage from './components/auth/UserLogin';
import TrainerRegisterPage from './components/auth/RegisterTrainer';
import MemberRegisterPage from './components/auth/RegisterMember';
import HealthDataCreatePage from './components/users/HealthDataCreatePage';
import HealthDataPage from './components/users/HealthDataPage';
import InitialPage from './components/auth/InitialPage';
import WorkoutLogs from './components/workoutLog/workoutLogList';
import WorkoutLogForm from './components/workoutLog/workoutLogForm';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/workouts/:trainerID" element={<WorkoutPage />} />
        <Route path="/workouts/:trainerID/createWorkout" element={<WorkoutCreatePage />} />
        <Route path="/auth/login" element={<UserLoginPage />} />
        <Route path="/users/register/trainer" element={<TrainerRegisterPage />} />
        <Route path="/users/register/member" element={<MemberRegisterPage />} />
        <Route path="/healthdatas/:userId/create" element={<HealthDataCreatePage />} />
        <Route path="/healthdatas/:userId" element={<HealthDataPage />} />
        <Route path="/nutritionalPlan/:userId" element={<HealthDataPage />} />
        <Route path="/" element={<InitialPage />} />
        <Route path="/workoutlogs/:userId" element={<WorkoutLogs />} />
        <Route path="/workoutlogs/:userId/createWorkoutLog" element={<WorkoutLogForm />} />
      </Routes>
    </Router>
  );
}

export default App;
