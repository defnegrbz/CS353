import React, { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { getTrainers } from '../../api/axiosConfig';
import { useParams } from 'react-router-dom';
import Navbar from '../navbar';
import Grid from '@mui/material/Grid';
import Dialog from '@mui/material/Dialog';
import Button from '@mui/material/Button';
import { DialogContent, DialogActions, DialogTitle } from '@mui/material';
import ConsultationDate from './ConsultationDate';

const Trainers = () => {
    const [trainers, setTrainers] = useState([]);
    const [searchTitle, setSearchTitle] = useState('');
    const [openDialog, setOpenDialog] = useState(false);
    const [selectedTrainer, setSelectedTrainer] = useState(null);
    const { userId } = useParams();

    useEffect(() => {
        fetchTrainers();
    }, []);

    const fetchTrainers = async () => {
        console.log(userId);
        try {
            console.log("here")
            const response = await getTrainers();
            console.log("here2")
            console.log(response);
            setTrainers(response.data);
        } catch (error) {
            console.error('Error fetching trainers:', error);
        }
    };

    const handleOpenDialog = (trainer) => {
        setSelectedTrainer(trainer);
        setOpenDialog(true);
    };

    const handleCloseDialog = () => {
        setOpenDialog(false);
        setSelectedTrainer(null);
    };

    const columns = [
        { field: 'trainerID', headerName: 'ID', width: 50, align: 'center', headerAlign: 'center' },
        { field: 'full_name', headerName: 'Full Name', width: 150, align: 'center', headerAlign: 'center' },
        { field: 'gender', headerName: 'Gender', width: 150, align: 'center', headerAlign: 'center' },
        { field: 'trainer_description', headerName: 'Description', width: 150, align: 'center', headerAlign: 'center' },
        { field: 'specialization', headerName: 'Specialization', width: 150, align: 'center', headerAlign: 'center' },
        { field: 'trainer_experience', headerName: 'Experience', width: 150, align: 'center', headerAlign: 'center' },
        { field: 'trainer_rating', headerName: 'Rating', width: 150, align: 'center', headerAlign: 'center' },
        {
            field: 'actions',
            headerName: 'Actions',
            width: 150,
            renderCell: (params) => (
                <Button variant="contained" color="primary" onClick={() => handleOpenDialog(params.row)}>
                    Consultate
                </Button>
            ),
        },
    ];

    return (
        <>
            <Navbar />
            <Grid container>
                <Grid item xs={12}>
                    <h1 style={{ textAlign: 'center' }}>Trainers</h1>
                </Grid>
            </Grid>
          <div style={{ height: 400, width: '80%', margin: '0 auto', textAlign: 'center' }}>
            <div style={{ marginBottom: '10px' }}>
                <label>
                  Search by Name:
                  <input type="text" value={searchTitle} onChange={(e) => setSearchTitle(e.target.value)} placeholder="Enter name" />
                </label>
            </div>
            <Dialog open={openDialog} onClose={handleCloseDialog}>
                <DialogTitle>Consultation Date Selector</DialogTitle>
                <DialogContent>
                    {selectedTrainer && (
                        <ConsultationDate
                            trainerID={selectedTrainer.trainerID}
                            onClose={(selectedDate) => {
                                console.log('Selected date:', selectedDate);
                                handleCloseDialog();
                            }}
                        />
                    )}
                </DialogContent>
            </Dialog>
        </>
    );
};

export default Trainers;
