import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { addWorkout, updateWorkout } from '../../api/axiosConfig'; 

const WorkoutCreationPage = ({ trainerId, onClose, workoutData }) => { 
  const navigate = useNavigate();
  const [workoutTitle, setWorkoutTitle] = useState(workoutData ? workoutData.title : '');
  const [workoutType, setWorkoutType] = useState(workoutData ? workoutData.type : '');
  const [targetAudience, setTargetAudience] = useState(workoutData ? workoutData.audience : '');
  const [workoutEstimatedTime, setWorkoutEstimatedTime] = useState(workoutData ? workoutData.estimatedTime : '');
  const [workoutDescription, setWorkoutDescription] = useState(workoutData ? workoutData.description : '');
  const [equipments, setEquipments] = useState(workoutData ? workoutData.equipments : '');
  const [caloriesBurnPerUnitTime, setCaloriesBurnPerUnitTime] = useState(workoutData ? workoutData.caloriesBurnPerUnitTime : '');
  const [intensityLevel, setIntensityLevel] = useState(workoutData ? workoutData.intensityLevel : '');

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    try {
      // Form validation
      if (!caloriesBurnPerUnitTime) {
        alert('Please enter the calorie burn per unit time.');
        return;
      }
  
      const config = {
        headers: {
          'Content-Type': 'application/json'
        }
      };
        await addWorkout(
          trainerId,
          workoutTitle,
          workoutType,
          targetAudience,
          workoutEstimatedTime,
          workoutDescription,
          equipments,
          caloriesBurnPerUnitTime,
          intensityLevel,
          config
        );
        console.log('Workout created successfully!');
    } catch (error) {
      console.error('Error:', error);
      alert('Failed to perform the operation. Please try again.');
    }
    onClose();
  };

  return (
    <div style={{ fontFamily: 'Arial, sans-serif', maxWidth: '500px', margin: '0 auto' }}>
      <h2 style={{ textAlign: 'center', color: '#333' }}>Create a New Workout</h2>
      <form onSubmit={handleSubmit} style={{ backgroundColor: '#f9f9f9', padding: '20px', borderRadius: '8px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)' }}>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Workout Title:</span>
          <input type="text" value={workoutTitle} onChange={(e) => setWorkoutTitle(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Workout Type:</span>
          <input type="text" value={workoutType} onChange={(e) => setWorkoutType(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Target Audience:</span>
          <input type="text" value={targetAudience} onChange={(e) => setTargetAudience(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Estimated Time:</span>
          <input type="text" value={workoutEstimatedTime} onChange={(e) => setWorkoutEstimatedTime(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Description:</span>
          <textarea value={workoutDescription} onChange={(e) => setWorkoutDescription(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Equipments:</span>
          <input type="text" value={equipments} onChange={(e) => setEquipments(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Calories Burn Per Unit Time:</span>
          <input type="number" value={caloriesBurnPerUnitTime} onChange={(e) => setCaloriesBurnPerUnitTime(e.target.value)} required />
        </label>
        <label style={{ display: 'block', marginBottom: '10px' }}>
          <span style={{ fontWeight: 'bold' }}>Intensity Level:</span>
          <input type="text" value={intensityLevel} onChange={(e) => setIntensityLevel(e.target.value)} required />
        </label>
        <button type="button" onClick={() => onClose()} style={{ backgroundColor: '#f9f9f9', color: '#333', padding: '8px 16px', borderRadius: '5px', border: '1px solid #333', cursor: 'pointer', marginRight: '10px' }}>Cancel</button>
        <button type="reset" style={{ backgroundColor: '#f9f9f9', color: '#333', padding: '8px 16px', borderRadius: '5px', border: '1px solid #333', cursor: 'pointer', marginRight: '10px' }}>Reset</button>

        <button type="submit" style={{ backgroundColor: '#007bff', color: '#fff', padding: '8px 16px', borderRadius: '5px', border: 'none', cursor: 'pointer', marginTop: '10px' }}>Create Workout</button>
      </form>
    </div>
  );
};

export default WorkoutCreationPage;
