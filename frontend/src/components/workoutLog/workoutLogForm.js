import React, { useState } from 'react';
import { addWorkoutLog } from '../../api/axiosConfig'; 

const WorkoutLogForm = ({ onClose }) => {
  const [date, setDate] = useState('');
  const [duration, setDuration] = useState('');
  const [status, setStatus] = useState('');
  const [totalCalories, setTotalCalories] = useState('');
  const [member, setMember] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await addWorkoutLog({
        date,
        duration,
        status,
        totalCalories,
        member,
      });
      console.log('Workout log created successfully!');
      onClose();
    } catch (error) {
      console.error('Error:', error);
      alert('Failed to create the workout log. Please try again.');
    }
  };

  return (
    <div style={{ fontFamily: 'Arial, sans-serif', maxWidth: '500px', margin: '0 auto' }}>
      <h2 style={{ textAlign: 'center', color: '#333' }}>Create a New Workout Log</h2>
      <form onSubmit={handleSubmit} style={{ backgroundColor: '#f9f9f9', padding: '20px', borderRadius: '8px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)' }}>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Date:</span>
          <input type="date" value={date} onChange={(e) => setDate(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Duration:</span>
          <input type="text" value={duration} onChange={(e) => setDuration(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Status:</span>
          <input type="text" value={status} onChange={(e) => setStatus(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Total Calories:</span>
          <input type="number" value={totalCalories} onChange={(e) => setTotalCalories(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Member:</span>
          <input type="text" value={member} onChange={(e) => setMember(e.target.value)} required />
        </label>
        <button type="button" onClick={onClose} style={{ backgroundColor: '#f9f9f9', color: '#333', padding: '8px 16px', borderRadius: '5px', border: '1px solid #333', cursor: 'pointer', marginRight: '10px' }}>Cancel</button>
        <button type="submit" style={{ backgroundColor: '#007bff', color: '#fff', padding: '8px 16px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px' }}>Create Workout Log</button>
      </form>
    </div>
  );
};

export default WorkoutLogForm;
