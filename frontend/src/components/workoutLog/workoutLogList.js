import React, { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { getWorkoutLogs, deleteWorkoutLog } from '../../api/axiosConfig'; // Import API functions
import Navbar from '../navbar';
import Grid from '@mui/material/Grid';
import Dialog from '@mui/material/Dialog';
import WorkoutLogForm from './workoutLogForm';
import Button from '@mui/material/Button';

const WorkoutLog = () => {
  const [workoutLogs, setWorkoutLogs] = useState([]);
  const [open, setOpen] = useState(false);
  const [selectedWorkoutLog, setSelectedWorkoutLog] = useState(null);

  useEffect(() => {
    fetchWorkoutLogs();
  }, []);

  const fetchWorkoutLogs = async () => {
    try {
      const response = await getWorkoutLogs(); // Call the getWorkoutLogs function
      const logsWithIds = response.data.map((log, index) => ({
        ...log,
        id: index + 1 // Generate unique ID (assuming index starts from 0)
      }));
      setWorkoutLogs(logsWithIds); // Set the workout logs state with the data from the response
    } catch (error) {
      console.error('Error fetching workout logs:', error);
    }
  };

  const handleCloseDialog = () => {
    setOpen(false);
    fetchWorkoutLogs(); // Fetch updated workout logs
  };

  const handleDelete = (id) => {
    deleteWorkoutLog(id)
      .then(response => {
        console.log('Workout log deleted successfully:', response);
        fetchWorkoutLogs();
      })
      .catch(error => {
        console.error('Error deleting workout log:', error);
        fetchWorkoutLogs();
      });
  };

  const columns = [
    { field: 'logID', headerName: 'ID', width: 50, align: 'center', headerAlign: 'center' },
    { field: 'date', headerName: 'Date', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'duration', headerName: 'Duration', width: 100, align: 'center', headerAlign: 'center' },
    { field: 'status', headerName: 'Status', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'totalCalories', headerName: 'Total Calories', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'member', headerName: 'Member', width: 150, align: 'center', headerAlign: 'center' },
  ];

  return (
    <>
      <Navbar />
      <Grid container>
        <Grid item xs={12}>
          <h1 style={{ textAlign: 'center' }}>Workout Logs</h1>
        </Grid>
        <Grid item xs={12} style={{ textAlign: 'center' }}>
          <Dialog open={open} onClose={() => setOpen(false)}>
            <WorkoutLogForm onClose={handleCloseDialog} />
          </Dialog>
          <button onClick={() => setOpen(true)} style={{ padding: '10px', margin: '10px', fontSize: '16px', cursor: 'pointer' }}>Create Workout Log</button>
        </Grid>
      </Grid>
      <div style={{ height: 400, width: '80%', margin: '0 auto', textAlign: 'center' }}>
        <DataGrid
          rows={workoutLogs}
          columns={columns.map((column) => ({
            ...column,
            align: 'center' // Set alignment to center for all columns
          }))}
          pageSize={5}
          rowsPerPageOptions={[5, 10, 20]}
          onRowClick={(row) => {
            setSelectedWorkoutLog(row);
            console.log('Row clicked:', row);
          }}
        />
      </div>
      <Grid container>
        <Grid item xs={12} style={{ textAlign: 'center' }}>
          <Button
            variant="contained"
            color="secondary"
            onClick={() => handleDelete(selectedWorkoutLog?.row.logID)}
            disabled={!selectedWorkoutLog}
          >
            Delete Workout Log
          </Button>
        </Grid>
      </Grid>
    </>
  );
};

export default WorkoutLog;
