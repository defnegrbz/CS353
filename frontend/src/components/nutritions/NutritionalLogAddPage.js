import React, { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { getNutrientLogs, deleteNutrientLog } from '../../api/axiosConfig';
import { useNavigate, Link, useParams } from 'react-router-dom';
import Navbar from '../navbar';
import Grid from '@mui/material/Grid';
import Dialog from '@mui/material/Dialog';
import Button from '@mui/material/Button';
import NutrientLogCreatePage from './NutrientLogCreatePage'; // Correct import

const NutritionalLogAddPage = () => {
  const [nutrientLogs, setNutrientLogs] = useState([]);
  const [open, setOpen] = useState(false);
  const [selectedNutrientLog, setSelectedNutrientLog] = useState(null);
  const { memberID } = useParams();

  useEffect(() => {
    fetchNutrientLogs();
    console.log("Member ID:", memberID);
  }, []);

  const fetchNutrientLogs = async () => {
    try {
      const response = await getNutrientLogs();
      const logsWithIds = response.data.map((log, index) => ({
        ...log,
        id: index + 1
      }));
      setNutrientLogs(logsWithIds);
    } catch (error) {
      console.error('Error fetching nutrient logs:', error);
    }
  };

  const handleCloseDialog = () => {
    setOpen(false);
    fetchNutrientLogs();
  };

  const handleDelete = (id) => {
    deleteNutrientLog(id)
      .then(response => {
        console.log('Nutrient log deleted successfully:', response);
        fetchNutrientLogs();
      })
      .catch(error => {
        console.error('Error deleting nutrient log:', error);
        fetchNutrientLogs();
      });
  };

  const columns = [
    { field: 'nutrientLogId', headerName: 'ID', width: 50, align: 'center', headerAlign: 'center' },
    { field: 'memberID', headerName: 'Member ID', width: 100, align: 'center', headerAlign: 'center' },
    { field: 'nutrientLogDate', headerName: 'Date', width: 150, align: 'center', headerAlign: 'center' },
    { field: 'totalCalories', headerName: 'Total Calories', width: 150, align: 'center', headerAlign: 'center' },
  ];

  return (
    <>
      <Navbar />
      <Grid container>
        <Grid item xs={12}>
          <h1 style={{ textAlign: 'center' }}>Nutrient Logs</h1>
        </Grid>
        <Grid item xs={12} style={{ textAlign: 'center' }}>
          <Dialog open={open} onClose={() => setOpen(false)}>
            <NutrientLogCreatePage memberId={memberID} onClose={() => handleCloseDialog()} />
          </Dialog>
          <button onClick={() => setOpen(true)} style={{ padding: '10px', margin: '10px', fontSize: '16px', cursor: 'pointer' }}>Create Nutrient Log</button>
        </Grid>
      </Grid>
      <div style={{ height: 400, width: '80%', margin: '0 auto', textAlign: 'center' }}>
        <DataGrid
          rows={nutrientLogs}
          columns={columns.map((column) => ({
            ...column,
            align: 'center'
          }))}
          pageSize={5}
          rowsPerPageOptions={[5, 10, 20]}
          onRowClick={(row) => {
            setSelectedNutrientLog(row);
            console.log('Row clicked:', row);
          }}
        />
      </div>
      <Grid container>
        <Grid item xs={12} style={{ textAlign: 'center' }}>
          <Button
            variant="contained"
            color="secondary"
            onClick={() => handleDelete(selectedNutrientLog?.row.nutrientLogId)}
            disabled={!selectedNutrientLog}
          >
            Delete Nutrient Log
          </Button>
        </Grid>
      </Grid>
    </>
  );
};

export default NutritionalLogAddPage;
