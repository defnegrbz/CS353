import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { DataGrid } from '@mui/x-data-grid';
import { getWorkoutLogsByMember, deleteWorkoutLog } from '../../api/axiosConfig';
import Navbar from '../navbar';
import Grid from '@mui/material/Grid';
import Dialog from '@mui/material/Dialog';
import WorkoutLogForm from './workoutLogForm';
import Button from '@mui/material/Button';

const WorkoutLog = () => {
  const [workoutLogs, setWorkoutLogs] = useState([]);
  const [open, setOpen] = useState(false);
  const [selectedWorkoutLog, setSelectedWorkoutLog] = useState(null);
  const { userId } = useParams();

  useEffect(() => {
    console.log("Member ID:", userId);
    if (userId) {
      fetchWorkoutLogs(userId);
    }
  }, [userId]);

  const fetchWorkoutLogs = async (userId) => {
    try {
      const response = await getWorkoutLogsByMember(userId);
      console.log('API response:', response);
      console.log('Data:', response.data);
      if (response && response.data) {
        const logsWithIds = response.data.map(log => ({
          ...log,
          id: log.workoutLogId, // Use workoutLogId as the unique identifier
          date: log.workoutLogDate,
          duration: log.workoutLogDuration,
          status: log.workoutLogStatus,
          totalCalories: log.workoutLogTotalCaloriesBurnt,
          member: log.memberId,
        }));
        console.log('Mapped logs:', logsWithIds);
        setWorkoutLogs(logsWithIds);
      } else {
        console.error('No data found in the response');
      }
    } catch (error) {
      console.error('Error fetching workout logs:', error);
    }
  };

  const handleCloseDialog = () => {
    setOpen(false);
    setSelectedWorkoutLog(null); // Clear selected workout log when closing dialog
    if (userId) {
      fetchWorkoutLogs(userId);
    }
  };

  const handleEdit = (id) => {
    const selectedLog = workoutLogs.find(log => log.id === id);
    if (selectedLog) {
      setSelectedWorkoutLog(selectedLog); // Set the selected workout log
      setOpen(true); // Open the dialog
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteWorkoutLog(id);
      console.log('Deletion successful');
      if (userId) {
        await fetchWorkoutLogs(userId); // Await here
      }
    } catch (error) {
      console.error('Failed to delete workout log:', error);
    }
  };

  const columns = [
    { field: 'id', headerName: 'ID', width: 50, align: 'center', headerAlign: 'center' },
    { field: 'date', headerName: 'Date', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'duration', headerName: 'Duration', width: 100, align: 'center', headerAlign: 'center' },
    { field: 'status', headerName: 'Status', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'totalCalories', headerName: 'Total Calories', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'member', headerName: 'Member', width: 150, align: 'center', headerAlign: 'center' },
    // { field: 'workoutTitle', headerName: 'Workout Title', width: 200, align: 'center', headerAlign: 'center' },
    // { field: 'trainerName', headerName: 'Trainer', width: 200, align: 'center', headerAlign: 'center' },
    {
      field: 'actions',
      headerName: 'Actions',
      width: 150,
      align: 'center',
      headerAlign: 'center',
      renderCell: (params) => (
        <Grid container spacing={2}>
          <Grid item>
            <Button
              variant="contained"
              color="primary"
              onClick={() => handleEdit(params.row.id)}
            >
              Edit
            </Button>
          </Grid>
          <Grid item>
            <Button
              variant="contained"
              color="secondary"
              onClick={() => handleDelete(params.row.id)}
            >
              Delete
            </Button>
          </Grid>
        </Grid>
      )
    }
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
            <WorkoutLogForm
              userId={userId}
              onClose={handleCloseDialog}
              initialValues={selectedWorkoutLog}
            />
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
            setSelectedWorkoutLog(row.row);
            console.log('Row clicked:', row);
          }}
        />
      </div>
    </>
  );
};

export default WorkoutLog;
