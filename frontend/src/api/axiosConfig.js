import axios from 'axios';

export const baseURL = 'http://localhost:8080';

const api = axios.create({
    baseURL: baseURL
});

//User Info

//Registers
export const memberRegister = (firstName, LastName, email, userName, password) => (
    axios.post(`${baseURL}/users/register/member`, { firstName, LastName, email, userName, password})
);

export const trainerRegister = (firstName, lastName, email, userName, password, certificateFile) => {
    const formData = new FormData();
    formData.append('firstName', firstName);
    formData.append('lastName', lastName);
    formData.append('email', email);
    formData.append('userName', userName);
    formData.append('password', password);
    formData.append('certificate', certificateFile); // Append certificate file

    return axios.post(`${baseURL}/users/register/trainer`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
};


//Logins 
export const memberLogin = (email, password) => (
    axios.post(`${baseURL}/users/login/member-login`, {
        email,
        password
    })
);

export const trainerLogin = (email, password) => (
    axios.post(`${baseURL}/users/login/trainer-login`, {
        email,
        password
    })
);

//Workout info

export const getWorkout = (id) => (
    axios.get(`${baseURL}/workouts/${id}`)
);

export const getWorkouts = () => (
    axios.get(`${baseURL}/workouts`)
);

// Add the addWorkout function
export const addWorkout = (trainerID,
    workout_title,
    workout_type,
    target_audience,
    workout_estimated_time,
    workout_description,
    equipments,
    calories_burn_per_unit_time,
    intensity_level) => (

    api.post(`${baseURL}/workouts/${trainerID}/createWorkout`, {
        trainerID,
        workout_title,
        workout_type,
        target_audience,
        workout_estimated_time,
        workout_description,
        equipments,
        calories_burn_per_unit_time,
        intensity_level
    })
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
