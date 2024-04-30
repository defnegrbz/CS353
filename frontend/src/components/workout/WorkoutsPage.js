import React, { useState, useEffect } from 'react';
import { getWorkouts} from '../../api/axiosConfig'; 
import { useNavigate, Link } from 'react-router-dom';

const Workout = () => {
  const [workouts, setWorkouts] = useState([]);

  useEffect(() => {
    fetchWorkouts();
  }, []); // Empty dependency array means this effect runs only once, similar to componentDidMount

  const fetchWorkouts = async () => {
    try {
      const response = await axios.get('/workout'); // Assuming '/workout' is the endpoint to fetch workouts
      setWorkouts(response.data);
    } catch (error) {
      console.error('Error fetching workout data:', error);
    }
  };

  return (
    <div>
      <h1>Workout Tables</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Trainer ID</th>
            <th>Type</th>
            <th>Target Audience</th>
            <th>Count</th>
            <th>Estimated Time</th>
            <th>Equipments</th>
            <th>Calories Burnt</th>
            <th>Intensity Level</th>
          </tr>
        </thead>
        <tbody>
          {workouts.map(workout => (
            <tr key={workout.workoutID}>
              <td>{workout.workoutID}</td>
              <td>{workout.workoutTitle}</td>
              <td>{workout.workoutCount}</td>
              <td>{workout.workoutEstimatedTime}</td>
              <td>{workout.workoutEstimatedTime}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Workout;

