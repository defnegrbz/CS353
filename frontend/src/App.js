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
import NutrientLogCreatePage from './components/nutritions/NutrientLogCreatePage';
import UserPage from './components/users/UserProfile';
import HomePage from './components/users/HomePage';
import TrainersPage from './components/users/TrainersPage';
import TrainerPage from './components/users/TrainerProfile';
import ConsultationDate from './components/users/ConsultationDate';
import BusyDate from './components/users/busyDates';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/homepage" element={<HomePage />} />
        <Route path="/workouts-trainer/:trainerID" element={<WorkoutPage />} />
        <Route path="/workouts-member/:memberID" element={<WorkoutMemberPage />} />
        <Route path="/workouts/:trainerID/createWorkout" element={<WorkoutCreatePage />} />
        <Route path="/auth/login" element={<UserLoginPage />} />
        <Route path="/register/trainer" element={<TrainerRegisterPage />} />
        <Route path="/register/member" element={<MemberRegisterPage />} />
        <Route path="/healthdatas/:userId/create" element={<HealthDataCreatePage />} />
        <Route path="/healthdatas/:userId" element={<HealthDataPage />} />
        <Route path="/nutritionalPlan/:userId" element={<HealthDataPage />} />
        <Route path="/" element={<InitialPage />} />
        <Route path="/nutrientLogCreatePage" element={<NutrientLogCreatePage />} />
        <Route path="/workoutlogs/:userId" element={<WorkoutLogs />} />
        <Route path="/workoutlogs/:userId/createWorkoutLog" element={<WorkoutLogForm />} />
        <Route path="/nutrientLogs/createNutrientLog" element={<NutritionalLogAddPage/>} />
        <Route path="/nutrientLogs/:nutrientLogId/addNutrients" element={<NutritionalLogAddPage/>}/>
        <Route path="/members/:userId" element={<UserPage/>}/>
        <Route path="/trainers/:userId" element={<TrainerPage/>}/>
        <Route path="/trainers-member/:userId" element={<TrainersPage/>}/>
        <Route path="/trainers-consultation/:trainerId/:userId" element={<ConsultationDate/>}/>
        <Route path="/trainers/busyDate/:trainerId" element={<BusyDate/>}/>
      </Routes>
    </Router>
  );
}

export default App;
