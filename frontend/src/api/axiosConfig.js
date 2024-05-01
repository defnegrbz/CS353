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

// Add the addWorkout function
export const addWorkout = (trainerID, workoutData) => (
    api.put(`${baseURL}/workouts/${trainerID}/createWorkout`, workoutData)
);

// Function to filter workouts based on type
export const filterWorkoutsByType = (type) => (
    api.get(`/workouts?type=${type}`)
);

// Function to filter workouts based on calories per unit time
export const filterWorkoutsByCalories = (minCalories, maxCalories) => (
    api.get(`/workouts?minCalories=${minCalories}&maxCalories=${maxCalories}`)
);

// Function to filter workouts based on equipments
export const filterWorkoutsByEquipments = (equipments) => (
    api.get(`/workouts?equipments=${equipments}`)
);

// Function to filter workouts based on target audience
export const filterWorkoutsByTargetAudience = (targetAudience) => (
    api.get(`/workouts?targetAudience=${targetAudience}`)
);

// Function to filter workouts based on intensity level
export const filterWorkoutsByIntensityLevel = (minIntensity, maxIntensity) => (
    api.get(`/workouts?minIntensity=${minIntensity}&maxIntensity=${maxIntensity}`)
);

export default api
