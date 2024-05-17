import axios from 'axios';

export const baseURL = 'http://localhost:8080';

const api = axios.create({
    baseURL: baseURL
});

//member register
export const memberRegister = (fullName, username, password, gender, mail, birthdate, profilePicture, fitnessGoals) => ( 
  axios.post(`${baseURL}/register/member`, {
        fullName,
        username,
        password,
        gender,
        mail,
        birthdate,
        profilePicture,
        fitnessGoals
    })
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
export const userLogin = (username, password) => (
    axios.post(`${baseURL}/auth/login`, {
        username,
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

// Function to delete a workout
export const deleteWorkout = (id) => (
    api.delete(`${baseURL}/workouts/${id}`)
);

// Function to update a workout
export const updateWorkout = (
    id,
    trainerID,
    workoutTitle,
    workoutType,
    targetAudience,
    workoutEstimatedTime,
    workoutDescription,
    calorieBurnPerUnitTime,
    intensityLevel,
    config 
) => (
    api.put(`${baseURL}/workouts/${id}`, {
      trainerID,
      workoutTitle: workoutTitle,
      workoutType: workoutType,
      targetAudience: targetAudience,
      workoutEstimatedTime: workoutEstimatedTime,
      workoutDescription: workoutDescription,
      calorieBurnPerUnitTime: calorieBurnPerUnitTime, // Corrected parameter name
      intensityLevel: intensityLevel
    }, config) // Pass config object here
);


// Function to filter workouts based on calories per unit time
export const filterWorkoutsByCalories = (minCalories, maxCalories) => (
    api.get(`/workouts/minCalories=${minCalories}&maxCalories=${maxCalories}`)
);

// Function to filter workouts based on equipments
export const filterWorkoutsByEquipments = (equipments) => (
    api.get(`/workouts?equipments=${equipments}`)
);

// Function to filter workouts based on target audience
export const filterWorkoutsByTargetAudience = (targetAudience) => (
    api.get(`/workouts/targetAudience=${targetAudience}`)
);

// Function to filter workouts based on target audience
export const filterWorkoutsByType = (workout_type) => (
    api.get(`/workouts/workout_type=${workout_type}`)
);

// Function to filter workouts based on intensity level
export const filterWorkoutsByIntensityLevel = (intensity) => (
    api.get(`/workouts/intensity=${intensity}`)
);
export const getWorkoutLogsByMember = async (userId) => {
    return api.get(`/workoutlogs/${userId}`);
  };

// Add the addWorkout function
export const addWorkout = (
    trainerID,
    workoutTitle,
    workoutType,
    targetAudience,
    workoutEstimatedTime,
    equipments,
    workoutDescription,
    calorieBurnPerUnitTime, // Corrected parameter name
    intensityLevel,
    config // Pass config as the third argument
) => (
    api.post(`${baseURL}/workouts/${trainerID}/createWorkout`, {
      trainerID,
      workoutTitle: workoutTitle,
      workoutType: workoutType,
      equipments: equipments,
      targetAudience: targetAudience,
      workoutEstimatedTime: workoutEstimatedTime,
      workoutDescription: workoutDescription,
      calorieBurnPerUnitTime: calorieBurnPerUnitTime, 
      intensityLevel: intensityLevel
    }, config) 
);

export const addWorkoutLog = async (userId, date, duration, status, caloriesBurnt, workoutId) => {
    try {
      const config = {
        headers: {
          'Content-Type': 'application/json',
        },
      };
      
      const response = await api.post(
        `${baseURL}/workoutlogs/${userId}/createworkoutlog`,
        {
          memberId: userId,
          workoutLogDate: date,
          workoutLogDuration: duration,
          workoutLogStatus: status,
          workoutLogTotalCaloriesBurnt: caloriesBurnt,
          workoutId: workoutId
        },
        config
      );
      
      return response.data; // Return the data for further use if needed
    } catch (error) {
      console.error('Error adding workout log:', error);
      throw error; // Re-throw the error for the caller to handle
    }
  };

export const deleteWorkoutLog = async (id) => {
  try {
    const response = await axios.delete(`/api/workoutLogs/${id}`);
    console.log('Workout log deleted successfully:', response.data);
    return response.data; // You might want to return some part of the response to the caller
  } catch (error) {
    console.error('Error deleting workout log:', error);
    throw error; // Rethrow the error if you want the caller to handle it
  }
};

export default api
