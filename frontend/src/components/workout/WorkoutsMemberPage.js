import React, { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { 
  filterWorkoutsByCalories, 
  filterWorkoutsByIntensityLevel, 
  filterWorkoutsByTargetAudience, 
  filterWorkoutsByType, 
  sortWorkoutsByCalorie, 
  sortWorkoutsByIntensity, 
  sortWorkoutsByTime, 
  getWorkouts,
  searchWorkoutsByTitle 
} from '../../api/axiosConfig';
import { useNavigate, Link, useParams } from 'react-router-dom';
import Navbar from '../navbar';
import Grid from '@mui/material/Grid';

const Workout = () => {
  const [workouts, setWorkouts] = useState([]);
  const [filterTargetAudience, setFilterTargetAudience] = useState('');
  const [selectedWorkout, setSelectedWorkout] = useState(null);
  const [filterWorkoutType, setFilterWorkoutType] = useState('');
  const [minCalories, setMinCalories] = useState('');
  const [maxCalories, setMaxCalories] = useState('');
  const [filterWorkoutIntensity, setFilterWorkoutIntensity] = useState('');
  const [sortCalOrder, setSortCalOrder] = useState(''); // Sorting order state
  const [sortIntOrder, setSortIntOrder] = useState(''); // Sorting order state
  const [sortTimeOrder, setSortTimeOrder] = useState(''); 
  const [searchTitle, setSearchTitle] = useState(''); // Search title state
  const trainerId = useParams();
  const trainerID = trainerId.trainerID;

  useEffect(() => {
    fetchWorkouts();
  }, [filterTargetAudience, filterWorkoutType, minCalories, maxCalories, filterWorkoutIntensity, sortCalOrder, sortIntOrder, sortTimeOrder, searchTitle]);

  const fetchWorkouts = async () => {
    try {
      let response;
      if (searchTitle) {
        response = await searchWorkoutsByTitle(searchTitle);
      }
      else if (filterTargetAudience) {
        response = await filterWorkoutsByTargetAudience(filterTargetAudience);
      } else if(filterWorkoutType){
        response = await filterWorkoutsByType(filterWorkoutType);
      } else if (minCalories && maxCalories){
        response = await filterWorkoutsByCalories(minCalories, maxCalories);
      } else if (filterWorkoutIntensity){
        response = await filterWorkoutsByIntensityLevel(filterWorkoutIntensity);
      } 
      else if(sortCalOrder){
        console.log("cal sort");
        response = await sortWorkoutsByCalorie(sortCalOrder);
      }else if(sortTimeOrder){
        console.log("time sort");
        response = await sortWorkoutsByTime(sortTimeOrder);
      }else if(sortIntOrder){
        console.log("int sort");
        response = await sortWorkoutsByIntensity(sortIntOrder);
      }else {
        response = await getWorkouts();
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

  const columns = [
    { field: 'workoutID', headerName: 'ID', width: 50, align: 'center', headerAlign: 'center' },
    { field: 'workoutTitle', headerName: 'Title', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'trainerID', headerName: 'Trainer ID', width: 100, align: 'center', headerAlign: 'center' },
    { field: 'workoutType', headerName: 'Type', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'workoutDescription', headerName: 'Description', width: 150, align: 'center', headerAlign: 'center'},
    { field: 'equipments', headerName: 'Equipments', width: 150, align: 'center', headerAlign: 'center'},
    { field: 'targetAudience', headerName: 'Target Audience', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'workoutEstimatedTime', headerName: 'Estimated Time', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'calorieBurnPerUnitTime', headerName: 'Calories Burnt', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'intensityLevel', headerName: 'Intensity Level', width: 130, align: 'center', headerAlign: 'center' },
  ];

  return (
    <>
      <Navbar />
      <Grid container>
        <Grid item xs={12}>
          <h1 style={{ textAlign: 'center' }}>Workouts</h1>
        </Grid>
      </Grid>
      <div style={{ display: 'flex', justifyContent: 'space-between', margin: '20px' }}>
        <div>
          <h3>Filter Options:</h3>
          <div>
            <label>
              Target Audience:
              <select value={filterTargetAudience} onChange={(e) => setFilterTargetAudience(e.target.value)}>
                <option value="">All</option>
                <option value="beginner">Beginner</option>
                <option value="intermediate">Intermediate</option>
                <option value="advanced">Advanced</option>
              </select>
            </label>
          </div>
          <div>
            <label>
              Workout Type:
              <select value={filterWorkoutType} onChange={(e) => setFilterWorkoutType(e.target.value)}>
                <option value="">All</option>
                <option value="muscle_gain">Muscle Gain</option>
                <option value="lose_weight">Lose Weight</option>
                <option value="endurance_training">Endurance Training</option>
              </select>
            </label>
          </div>
          <div>
            <label>
              Min Calories:
              <input type="number" value={minCalories} onChange={(e) => setMinCalories(e.target.value)} />
            </label>
            <label>
              Max Calories:
              <input type="number" value={maxCalories} onChange={(e) => setMaxCalories(e.target.value)} />
            </label>
          </div>
          <div>
            <label>
              Intensity Level:
              <input type="number" value={filterWorkoutIntensity} onChange={(e) => setFilterWorkoutIntensity(e.target.value)} />
            </label>
          </div>
        </div>
        <div>
          <h3>Search Options:</h3>
          <div>
            <label>
              Search by Title:
              <input type="text" value={searchTitle} onChange={(e) => setSearchTitle(e.target.value)} placeholder="Enter workout title" />
            </label>
          </div>
        </div>
      </div>
      <div style={{ height: 400, width: '80%', margin: '0 auto' }}>
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
      </Grid>
    </>
  );
};

export default Workout;

