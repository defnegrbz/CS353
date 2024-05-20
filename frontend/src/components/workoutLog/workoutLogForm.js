import React, { useState, useEffect } from 'react';
import { Dialog, DialogTitle, DialogContent, Button, List, ListItem, ListItemText, TextField } from '@mui/material';
import { getWorkouts, addWorkoutLog, updateWorkoutLog } from '../../api/axiosConfig';

const WorkoutLogForm = ({ userId, onClose, initialValues }) => {
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

    useEffect(() => {
        if (initialValues) {
            setDate(initialValues.date);
            setDuration(initialValues.duration);
            setStatus(initialValues.status);
            setTotalCalories(initialValues.caloriesBurnt);
            setSelectedWorkout(initialValues.workout);
        }
    }, [initialValues]);

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
        if (!date || !duration || !status) {
            alert('Please fill in all the fields.');
            return;
        }
        const workoutLog = {
            workoutLogDate: date,
            workoutLogDuration: duration,
            workoutLogStatus: status,
            workoutLogTotalCaloriesBurnt: totalCalories,
            workoutId: selectedWorkout.workoutID,
        };
        if (initialValues && initialValues.workoutLogId) {
            try {
                await updateWorkoutLog(initialValues.workoutLogId, workoutLog);
                console.log('Workout log updated successfully!');
                resetForm();
                onClose();
            } catch (error) {
                console.error('Error updating workout log:', error);
                alert('Failed to update the workout log. Please try again.');
            }
        } else {
            try {
                await addWorkoutLog(userId, workoutLog);
                console.log('Workout log created successfully!');
                resetForm();
                onClose();
            } catch (error) {
                console.error('Error creating workout log:', error);
                alert('Failed to create the workout log. Please try again.');
            }
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
            <h2>{initialValues ? 'Edit Workout Log' : 'Create a New Workout Log'}</h2>
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
                    {initialValues ? 'Update Workout Log' : 'Create Workout Log'}
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
