import React, { useState, useEffect } from 'react';
import { DataGrid, GridActionsCell } from '@mui/x-data-grid';
import { filterWorkoutsByCalories, filterWorkoutsByIntensityLevel, filterWorkoutsByTargetAudience, filterWorkoutsByType, getWorkouts } from '../../api/axiosConfig'; // Import the new function
import { useNavigate, Link, useParams } from 'react-router-dom';
import Navbar from '../navbar';
import Grid from '@mui/material/Grid';
import Dialog from '@mui/material/Dialog';
import WorkoutCreatePage from './WorkoutCreatePage';
import Button from '@mui/material/Button';
import { DialogContent } from '@mui/material';
import { DialogActions } from '@mui/material';
import { DialogTitle } from '@mui/material';
import { DialogContentText } from '@mui/material';
import { deleteWorkout } from '../../api/axiosConfig';

const Workout = () => {
  const [workouts, setWorkouts] = useState([]);
  const [open, setOpen] = useState(false);
  const [selectedWorkout, setSelectedWorkout] = useState(null);
  const [filterTargetAudience, setFilterTargetAudience] = useState(''); 
  const [filterWorkoutType, setFilterWorkoutType] = useState(''); 
  const [minCalories, setMinCalories] = useState('');
  const [maxCalories, setMaxCalories] = useState('');
  const [filterWorkoutIntensity, setFilterWorkoutIntensity] = useState('');
  const trainerId = useParams();
  const trainerID = trainerId.trainerID;

  useEffect(() => {
    fetchWorkouts();
  }, [filterTargetAudience, filterWorkoutType, minCalories, maxCalories, filterWorkoutIntensity]); // Refetch workouts when filter changes

  const fetchWorkouts = async () => {
    try {
      let response;
      if (filterTargetAudience) {
        console.log(filterTargetAudience ," a");
        response = await filterWorkoutsByTargetAudience(filterTargetAudience); // Use the filter function if target audience is selected
      } 
      else if(filterWorkoutType){
        console.log(filterWorkoutType ," a");
        response = await filterWorkoutsByType(filterWorkoutType); // Use the filter function if target audience is selected
      }
      else if (minCalories && maxCalories){
        console.log(minCalories, "-" , maxCalories);
        response = await filterWorkoutsByCalories(minCalories, maxCalories);
      }
      else if (filterWorkoutIntensity){
        console.log("Intensity", filterWorkoutIntensity);
        response = await filterWorkoutsByIntensityLevel(filterWorkoutIntensity);
      }
      else {
        response = await getWorkouts(); // Otherwise, get all workouts
      }
      const workoutsWithIds = response.data.map((workout, index) => ({
        ...workout,
        id: index + 1
      }));
      setWorkouts(workoutsWithIds);
    } catch (error) {
      console.error('Error fetching workouts:', error);
    }
  };

  const handleCloseDialog = () => {
    setOpen(false);
    fetchWorkouts();
  };

  const handleDelete = (id) => {
    deleteWorkout(id)
      .then(response => {
        console.log('Workout deleted successfully:', response);
        fetchWorkouts();
      })
      .catch(error => {
        console.error('Error deleting workout:', error);
        fetchWorkouts();
      });
  };

  const columns = [
    { field: 'workoutID', headerName: 'ID', width: 50, align: 'center', headerAlign: 'center' },
    { field: 'workoutTitle', headerName: 'Title', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'trainerID', headerName: 'Trainer ID', width: 100, align: 'center', headerAlign: 'center' },
    { field: 'workoutType', headerName: 'Type', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'workoutDescription', headerName: 'Description', width: 150, align: 'center', headerAlign: 'center'},
    { field: 'equipments', headerName: 'Equipments', width: 150, align: 'center', headerAlign: 'center'},
    { field: 'targetAudience', headerName: 'Target Audience', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'workoutEstimatedTime', headerName: 'Estimated Time', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'caloriesBurnPerUnitTime', headerName: 'Calories Burnt', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'intensityLevel', headerName: 'Intensity Level', width: 130, align: 'center', headerAlign: 'center' },
  ];

  return (
    <>
      <Navbar />
      <Grid container>
        <Grid item xs={12}>
          <h1 style={{ textAlign: 'center' }}>Workouts</h1>
        </Grid>
        <Grid item xs={12} style={{ textAlign: 'center' }}>
          <Dialog open={open} onClose={() => setOpen(false)}>
            <WorkoutCreatePage trainerId={trainerID} onClose={() => handleCloseDialog()} />
          </Dialog>
          <button onClick={() => setOpen(true)} style={{ padding: '10px', margin: '10px', fontSize: '16px', cursor: 'pointer' }}>Create Workout</button>
        </Grid>
      </Grid>
      <div style={{ height: 400, width: '80%', margin: '0 auto', textAlign: 'center' }}>
        <div style={{ marginBottom: '10px' }}>
          <span style={{ marginRight: '10px' }}>Filter by Target Audience:</span>
          <select value={filterTargetAudience} onChange={(e) => setFilterTargetAudience(e.target.value)}>
            <option value="">All</option>
            <option value="beginner">Beginner</option>
            <option value="intermediate">Intermediate</option>
            <option value="advanced">Advanced</option>
          </select>
          <span style={{ marginRight: '10px' }}>Filter by Workout Type:</span>
          <select value={filterWorkoutType} onChange={(e) => setFilterWorkoutType(e.target.value)}>
            <option value="">All</option>
            <option value="muscle gain">Muscle Gain</option>
            <option value="lose weight">Lose Weight</option>
            <option value="endurance training">Endurance Training</option>
          </select>
          <span style={{ marginRight: '10px', marginLeft: '20px' }}>Min Calories:</span>
          <input type="number" value={minCalories} onChange={(e) => setMinCalories(e.target.value)} style={{ marginRight: '10px' }} />
          <span style={{ marginRight: '10px' }}>Max Calories:</span>
          <input type="number" value={maxCalories} onChange={(e) => setMaxCalories(e.target.value)} />
          <span style={{ marginRight: '10px' }}>Intensity Level:</span>
          <input type="number" value={filterWorkoutIntensity} onChange={(e) => setFilterWorkoutIntensity(e.target.value)} />
        </div>
        <DataGrid
          rows={workouts}
          columns={columns.map((column) => ({
            ...column,
            align: 'center'
          }))}
          pageSize={5}
          rowsPerPageOptions={[5, 10, 20]}
          onRowClick={(row) => {
            setSelectedWorkout(row);
            console.log('Row clicked:', row);
          }}
        />
      </div>
      <Grid container>
        <Grid item xs={12} style={{ textAlign: 'center' }}>
          <Button
            variant="contained"
            color="secondary"
            onClick={() => handleDelete(selectedWorkout?.row.workoutID)}
            disabled={!selectedWorkout}
          >
            Delete Workout
          </Button>
        </Grid>
      </Grid>
    </>
  );
};

export default Workout;

