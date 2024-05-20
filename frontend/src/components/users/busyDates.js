
import React, { useState, useEffect } from 'react';
import Calendar from 'react-calendar';
import { getBusyDates, getTrainer } from '../../api/axiosConfig';
import './CalendarStyles.css';


const BusyDate = ({ trainerID,  onClose }) => {
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

    return (
        <div className="calendar-container">
            <h2>Consultation Dates</h2>
            <Calendar
                tileDisabled={({ date }) => busyDates.some(busyDate => new Date(busyDate).toDateString() === date.toDateString())}
            />
        </div>
    );

};

export default BusyDate;