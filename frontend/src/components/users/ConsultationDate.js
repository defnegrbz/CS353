import React, { useState, useEffect } from 'react';
import Calendar from 'react-calendar';
import { getBusyDates } from '../../api/axiosConfig';

const ConsultationDate = ({ trainerID, userId, onClose }) => {
    const [busyDates, setBusyDates] = useState([]);
    const [selectedDate, setSelectedDate] = useState(null);

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

    return (
        <div>
            <h2>Select Consultation Date</h2>
            <Calendar
                onChange={handleDateChange}
                value={selectedDate}
                tileDisabled={({ date }) => busyDates.some(busyDate => new Date(busyDate).toDateString() === date.toDateString())}
            />
            <button onClick={() => onClose(selectedDate)}>Confirm</button>
        </div>
    );
};

export default ConsultationDate;
