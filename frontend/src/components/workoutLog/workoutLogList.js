import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { DataGrid } from '@mui/x-data-grid';
import { getWorkoutLogsByMember, deleteWorkoutLog } from '../../api/axiosConfig'; // Ensure these are correctly defined
import Navbar from '../navbar';
import Grid from '@mui/material/Grid';
import Dialog from '@mui/material/Dialog';
import WorkoutLogForm from './workoutLogForm';
import Button from '@mui/material/Button';

const WorkoutLog = () => {
  const [workoutLogs, setWorkoutLogs] = useState([]);
  const [open, setOpen] = useState(false);
  const [selectedWorkoutLog, setSelectedWorkoutLog] = useState(null);
  const { memberId } = useParams(); // Now properly imported and used

  useEffect(() => {
    if (memberId) {
      fetchWorkoutLogs(memberId);
    }
  }, [memberId]);

  const fetchWorkoutLogs = async (memberId) => {
    try {
      const response = await getWorkoutLogsByMember(memberId); // Assuming this is correctly defined
      const logsWithIds = response.data.map((log, index) => ({
        ...log,
        id: index + 1
      }));
      setWorkoutLogs(logsWithIds);
    } catch (error) {
      console.error('Error fetching workout logs:', error);
    }
  };

  const handleCloseDialog = () => {
    setOpen(false);
    if (memberId) {
      fetchWorkoutLogs(memberId); // Now correctly refetches with memberId
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteWorkoutLog(id);
      console.log('Deletion successful');
      if (memberId) {
        fetchWorkoutLogs(memberId); // Refresh the list after deletion
      }
    } catch (error) {
      console.error('Failed to delete workout log:', error);
    }
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
          <Dialog open={open} onClose={handleCloseDialog}>
            <WorkoutLogForm onClose={handleCloseDialog} />
          </Dialog>
          <Button onClick={() => setOpen(true)} variant="contained" color="primary" style={{ margin: '10px' }}>
            Create Workout Log
          </Button>
        </Grid>
      </Grid>
      <div style={{ height: 400, width: '80%', margin: '0 auto', textAlign: 'center' }}>
        <DataGrid
          rows={workoutLogs}
          columns={columns}
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
            onClick={() => handleDelete(selectedWorkoutLog?.id)}
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
