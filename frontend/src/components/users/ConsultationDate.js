import React, { useState, useEffect } from 'react';
import Calendar from 'react-calendar';
import { getBusyDates, addBusyDate, getTrainer } from '../../api/axiosConfig';
import './CalendarStyles.css';

const ConsultationDate = ({ trainerID, userId, onClose }) => {
    const [busyDates, setBusyDates] = useState([]);
    const [selectedDate, setSelectedDate] = useState(null);
    const [successMessage, setSuccessMessage] = useState('');

    useEffect(() => {
        fetchBusyDates();
    }, []);

    const fetchBusyDates = async () => {
        try {
            const response = await getBusyDates(trainerID);
            setBusyDates(response.data);
        } catch (error) {
            console.error('Error fetching busy dates:', error);
        }
    };

    const handleDateChange = (date) => {
        setSelectedDate(date);
    };

    const handleConfirm = async () => {
        try {
            const response = await addBusyDate(trainerID, selectedDate);
            setBusyDates([...busyDates, selectedDate]);
            setSuccessMessage(`Date successfully added. Trainer's email: ${response.data.email}`);
            onClose(selectedDate);
        } catch (error) {
            console.error('Error adding busy date:', error);
        }
    };

    return (
        <div className="calendar-container">
            <h2>Select Consultation Date</h2>
            <Calendar
                onChange={handleDateChange}
                value={selectedDate}
                tileDisabled={({ date }) => busyDates.some(busyDate => new Date(busyDate).toDateString() === date.toDateString())}
            />
            <button className="confirm-button" onClick={handleConfirm}>Confirm</button>
            {successMessage && <p>{successMessage}</p>}
        </div>
    );
};

export default ConsultationDate;


