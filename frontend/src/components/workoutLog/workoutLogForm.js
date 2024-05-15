import React, { useState, useEffect } from 'react';
import { Dialog, DialogTitle, DialogContent, Button, List, ListItem, ListItemText } from '@mui/material';
import { getWorkouts, addWorkoutLog } from '../../api/axiosConfig';
import { useParams } from 'react-router-dom';

const WorkoutLogForm = ({ onClose }) => {
  const [date, setDate] = useState('');
  const [duration, setDuration] = useState('');
  const [status, setStatus] = useState('');
  const [totalCalories, setTotalCalories] = useState('');
  const [workouts, setWorkouts] = useState([]);
  const [openSelectWorkout, setOpenSelectWorkout] = useState(false);
  const [selectedWorkout, setSelectedWorkout] = useState(null);
  const [workoutId, setWorkoutId] = useState(null);
  const { userId } = useParams();  // Assuming your route parameter is named `userId`

  const calculateCaloriesBurnt = () => {
    if (duration && selectedWorkout) {
        const caloriesBurnt = duration * selectedWorkout.calorieBurnPerUnitTime;
        setTotalCalories(caloriesBurnt);
    }
  };

  useEffect(() => {
    const fetchWorkouts = async () => {
      try {
        const response = await getWorkouts();
        setWorkouts(response.data);
      } catch (error) {
        console.error('Error fetching workouts:', error);
      }
    };
    fetchWorkouts();
  }, []);

  const handleSelectWorkout = (workout) => {
    setSelectedWorkout(workout);
    setWorkoutId(workout.id);
    setOpenSelectWorkout(false);
    calculateCaloriesBurnt();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selectedWorkout) {
      alert('Please select a workout.');
      return;
    }
    try {
      await addWorkoutLog(userId,
        date,
        duration,
        status,
        totalCalories, // Use totalCalories instead of caloriesBurnt
        workoutId
      );
      console.log('Workout log created successfully!');
      resetForm();
      onClose();
    } catch (error) {
      console.error('Error:', error);
      alert('Failed to create the workout log. Please try again.');
    }
  };

  const resetForm = () => {
    setDate('');
    setDuration('');
    setStatus('');
    setTotalCalories('');
    setSelectedWorkout(null);
  };

  return (
    <div style={{ maxWidth: '500px', margin: 'auto' }}>
      <h2>Create a New Workout Log</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <span>Workout:</span>
          <Button onClick={() => setOpenSelectWorkout(true)}>
            {selectedWorkout ? selectedWorkout.title : 'Select Workout'}
          </Button>
        </div>
        <input type="date" value={date} onChange={e => setDate(e.target.value)} required />
        <input type="text" value={duration} onChange={e => setDuration(e.target.value)} required />
        <input type="text" value={status} onChange={e => setStatus(e.target.value)} required />
        <input type="number" value={totalCalories} onChange={e => setTotalCalories(e.target.value)} required />
        <Button type="submit">Create Workout Log</Button>
        <Button onClick={onClose}>Cancel</Button>
        <Button onClick={resetForm}>Reset</Button>
      </form>
      <Dialog open={openSelectWorkout} onClose={() => setOpenSelectWorkout(false)}>
        <DialogTitle>Select a Workout</DialogTitle>
        <DialogContent>
          <List>
            {workouts.map(workout => (
              <ListItem key={workout.id} button onClick={() => handleSelectWorkout(workout)}>
                <ListItemText primary={workout.title} />
              </ListItem>
            ))}
          </List>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default WorkoutLogForm;
