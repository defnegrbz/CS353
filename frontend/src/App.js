import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import WorkoutPage from './components/workout/WorkoutsPage';
import WorkoutMemberPage from './components/workout/WorkoutsMemberPage';
import WorkoutCreatePage from './components/workout/WorkoutCreatePage';
import UserLoginPage from './components/auth/UserLogin';
import TrainerRegisterPage from './components/auth/RegisterTrainer';
import MemberRegisterPage from './components/auth/RegisterMember';
import HealthDataCreatePage from './components/users/HealthDataCreatePage';
import HealthDataPage from './components/users/HealthDataPage';
import InitialPage from './components/auth/InitialPage';
import WorkoutLogs from './components/workoutLog/workoutLogList';
import WorkoutLogForm from './components/workoutLog/workoutLogForm';
import NutritionalLogAddPage from './components/nutritions/NutritionalLogAddPage';
import UserPage from './components/users/UserProfile';
import HomePage from './components/users/HomePage';
import TrainersPage from './components/users/TrainersPage';
import ConsultationDate from './components/users/ConsultationDate';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/workouts/:trainerID" element={<WorkoutPage />} />
        <Route path="/homepage" element={<HomePage />} />
        <Route path="/workoutsT/:trainerID" element={<WorkoutPage />} />
        <Route path="/workouts/:memberID" element={<WorkoutMemberPage />} />
        <Route path="/workouts/:trainerID/createWorkout" element={<WorkoutCreatePage />} />
        <Route path="/auth/login" element={<UserLoginPage />} />
        <Route path="/users/register/trainer" element={<TrainerRegisterPage />} />
        <Route path="/register/member" element={<MemberRegisterPage />} />
        <Route path="/healthdatas/:userId/create" element={<HealthDataCreatePage />} />
        <Route path="/healthdatas/:userId" element={<HealthDataPage />} />
        <Route path="/nutritionalPlan/:userId" element={<HealthDataPage />} />
        <Route path="/" element={<InitialPage />} />
        <Route path="/workoutlogs/:userId" element={<WorkoutLogs />} />
        <Route path="/workoutlogs/:userId/createWorkoutLog" element={<WorkoutLogForm />} />
        <Route path="/nutrientLogs/createNutrientLog" element={<NutritionalLogAddPage/>} />
        <Route path="/nutrientLogs/:nutrientLogId/addNutrients" element={<NutritionalLogAddPage/>}/>
        <Route path="/members/:userId" element={<UserPage/>}/>
        <Route path="/trainers/:userId" element={<TrainersPage/>}/>
        <Route path="/trainer/:trainerId/:userId" element={<TrainersPage/>}/>
      </Routes>
    </Router>
  );
}

export default App;
