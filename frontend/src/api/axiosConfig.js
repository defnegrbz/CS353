import axios from 'axios';

export const baseURL = 'http://localhost:8080';

const api = axios.create({
    baseURL: baseURL
});

//Workout info

export const getWorkout = (id) => (
    axios.get(`${baseURL}/workout/${id}`)
);

export const getWorkouts = () => (
    axios.get(`${baseURL}/workouts`)
);

export default api
