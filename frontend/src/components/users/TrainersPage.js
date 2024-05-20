import React, { useState, useEffect } from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { useParams } from 'react-router-dom';
import { getTrainers, voteTrainer } from '../../api/axiosConfig';
import Navbar from '../navbar';
import Grid from '@mui/material/Grid';
import Dialog from '@mui/material/Dialog';
import Button from '@mui/material/Button';
import { DialogContent, DialogActions, DialogTitle } from '@mui/material';
import ConsultationDate from './ConsultationDate';
import { TextField } from '@mui/material';
import { DialogContentText } from '@mui/material';

const Trainers = () => {
    const [trainers, setTrainers] = useState([]);
    const [searchTitle, setSearchTitle] = useState('');
    const [openDialog, setOpenDialog] = useState(false);
    const [openVoteDialog, setOpenVoteDialog] = useState(false);
    const [selectedTrainer, setSelectedTrainer] = useState(null);
    const [vote, setVote] = useState(0);

    useEffect(() => {
        fetchTrainers();
    }, []);

    const fetchTrainers = async () => {
        try {
            console.log("here")
            const response = await getTrainers();
            console.log("here2")
            console.log(response);
            setTrainers(response.data);
            const trainersWithIds = response.data.map((trainer, index) => ({
                ...trainer,
                id: index + 1
            }));
            setTrainers(trainersWithIds);
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

    const handleOpenVoteDialog = (trainer) => {
        setSelectedTrainer(trainer);
        setOpenVoteDialog(true);
    };

    const handleCloseVoteDialog = () => {
        setOpenVoteDialog(false);
        setSelectedTrainer(null);
    };

    const handleVoteSubmit = async () => {
        try {
            await voteTrainer(selectedTrainer.id, vote);
            fetchTrainers();
            handleCloseVoteDialog();
        } catch (error) {
            console.error('Error submitting vote:', error);
        }
    };

    const columns = [
        { field: 'trainerID', headerName: 'ID', width: 50, align: 'center', headerAlign: 'center' },
        { field: 'fullName', headerName: 'Full Name', width: 150, align: 'center', headerAlign: 'center' },
        { field: 'gender', headerName: 'Gender', width: 150, align: 'center', headerAlign: 'center' },
        { field: 'trainerDescription', headerName: 'Description', width: 150, align: 'center', headerAlign: 'center' },
        { field: 'specialization', headerName: 'Specialization', width: 150, align: 'center', headerAlign: 'center' },
        { field: 'trainerExperience', headerName: 'Experience', width: 150, align: 'center', headerAlign: 'center' },
        { field: 'trainerRating', headerName: 'Rating', width: 150, align: 'center', headerAlign: 'center' },
        {
            field: 'actions',
            headerName: 'Actions',
            width: 200,
            renderCell: (params) => (
                <Grid container spacing={2}>
                    <Grid item>
                        <Button variant="contained" color="primary"  onClick={() => handleOpenDialog(params.row)}>
                            Consultate
                        </Button>
                    </Grid>
                    <Grid item>
                        <Button variant="contained" color="primary" onClick={() => handleOpenVoteDialog(params.row)}>
                            Vote
                        </Button>
                    </Grid>
                </Grid>
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
            <div style={{ height: 400, width: '80%', margin: '0 auto' }}>
        <DataGrid
          rows={trainers}
          columns={columns.map((column) => ({
            ...column,
            align: 'center'
          }))}
          pageSize={5}

          rowsPerPageOptions={[5, 10, 20]}
          onRowClick={(row) => {
            setSelectedTrainer(row);
            console.log('Row clicked:', row);
          }}
        />
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
            <Dialog open={openVoteDialog} onClose={handleCloseVoteDialog}>
                <DialogTitle>Vote for Trainer</DialogTitle>
                <DialogContent>
                    {selectedTrainer && (
                        <>
                            <DialogContentText>
                                Rate {selectedTrainer.fullName} between 0 and 5:
                            </DialogContentText>
                            <TextField
                                autoFocus
                                margin="dense"
                                id="vote"
                                label="Vote"
                                type="number"
                                fullWidth
                                value={vote}
                                onChange={(e) => setVote(parseInt(e.target.value))}
                                inputProps={{ min: 0, max: 5 }}
                            />
                        </>
                    )}
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseVoteDialog} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={handleVoteSubmit} color="primary">
                        Submit
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
        </>
    );
};

export default Trainers;
