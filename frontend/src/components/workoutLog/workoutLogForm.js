import React, { useState, useEffect } from 'react';
import { Dialog, DialogTitle, DialogContent, Button, List, ListItem, ListItemText, TextField } from '@mui/material';
import { getWorkouts, addWorkoutLog } from '../../api/axiosConfig';

const WorkoutLogForm = ({ onClose, userId }) => {
  const [date, setDate] = useState('');
  const [duration, setDuration] = useState('');
  const [status, setStatus] = useState('');
  const [totalCalories, setTotalCalories] = useState(0);
  const [workouts, setWorkouts] = useState([]);
  const [openSelectWorkout, setOpenSelectWorkout] = useState(false);
  const [selectedWorkout, setSelectedWorkout] = useState(null);

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
    setOpenSelectWorkout(false);
    if (duration) {
      setTotalCalories(duration * workout.calorieBurnPerUnitTime);
    }
  };

  const handleDurationChange = (e) => {
    const newDuration = e.target.value;
    setDuration(newDuration);
    if (selectedWorkout) {
      setTotalCalories(newDuration * selectedWorkout.calorieBurnPerUnitTime);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!selectedWorkout) {
      alert('Please select a workout.');
      return;
    }
    try {
      await addWorkoutLog(userId, date, duration, status, totalCalories, selectedWorkout.workoutID);
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
    setTotalCalories(0);
    setSelectedWorkout(null);
  };

  return (
    <div style={{ maxWidth: '500px', margin: 'auto' }}>
      <h2>Create a New Workout Log</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <span>Workout:</span>
          <Button onClick={() => setOpenSelectWorkout(true)}>
            {selectedWorkout ? selectedWorkout.workoutTitle : 'Select Workout'}
          </Button>
        </div>
        <TextField
          type="date"
          value={date}
          onChange={e => setDate(e.target.value)}
          required
          fullWidth
          margin="normal"
        />
        <TextField
          type="number"
          label="Duration"
          value={duration}
          onChange={handleDurationChange}
          required
          fullWidth
          margin="normal"
        />
        <TextField
          type="text"
          label="Status"
          value={status}
          onChange={e => setStatus(e.target.value)}
          required
          fullWidth
          margin="normal"
        />
        <Button type="submit" variant="contained" color="primary">
          Create Workout Log
        </Button>
        <Button onClick={onClose} variant="contained" color="secondary" style={{ marginLeft: '10px' }}>
          Cancel
        </Button>
        <Button onClick={resetForm} variant="contained" style={{ marginLeft: '10px' }}>
          Reset
        </Button>
      </form>
      <Dialog open={openSelectWorkout} onClose={() => setOpenSelectWorkout(false)}>
        <DialogTitle>Select a Workout</DialogTitle>
        <DialogContent>
          <List>
            {workouts.map(workout => (
              <ListItem key={workout.workoutID} button onClick={() => handleSelectWorkout(workout)}>
                <ListItemText primary={workout.workoutTitle} />
              </ListItem>
            ))}
          </List>
        </DialogContent>
      </Dialog>
    </div>
  );
};

export default WorkoutLogForm;
