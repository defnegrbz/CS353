import axios from 'axios';

export const baseURL = 'http://localhost:8080';

const api = axios.create({
    baseURL: baseURL,
    headers: {
      'Content-Type': 'application/json',
    },
});

//member register
export const memberRegister = (fullName, username, password, gender, mail, birthdate, height, weight, allergies, diseases, medications, fitnessGoals) => ( 
  axios.post(`${baseURL}/register/member`, {
        fullName,
        username,
        password,
        gender,
        mail,
        birthdate,
        height,
        weight,
        allergies,
        diseases,
        medications,
        fitnessGoals
    })
);

//member register
export const trainerRegister = (fullName, username, password, gender, mail, birthdate, description, specialization, experience, certificate) => ( 
  axios.post(`${baseURL}/register/trainer`, {
        fullName,
        username,
        password,
        gender,
        mail,
        birthdate,
        description, 
        specialization, 
        experience, 
        certificate
    })
);


//Logins 
export const userLogin = (username, password) => (
    axios.post(`${baseURL}/auth/login`, {
        username,
        password
    })
);

export const getMember = (userId) => (
  axios.get(`${baseURL}/members/${userId}`)
);

export const getUser = (userId) => (
    axios.get(`${baseURL}/users/${userId}`)
);

export const getTrainers = () => (
  axios.get(`${baseURL}/trainers`)
);

export const getBusyDates = (trainerID) => {
  return axios.get(`/trainers/${trainerID}/busy-dates`);
};

export const addBusyDate = (trainerID, date) => {
  return axios.post(`/trainers/${trainerID}/add-busy-dates`);
};


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

export const sortWorkoutsByTime = (order) => (
  api.get(`/workouts/timesort/${order}`)
);

export const sortWorkoutsByCalorie = (order) => (
  api.get(`/workouts/caloriesort/${order}`)
);

export const sortWorkoutsByIntensity = (order) => (
  api.get(`/workouts/intensitysort/${order}`)
);

export const getWorkoutLogsByMember = async (userId) => {
    return api.get(`/workoutlogs/${userId}`);
  };

  export const searchWorkoutsByTitle = (title) => {
    return api.get(`/workouts/search`, {
      params: { title },
    });
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

export const deleteWorkoutLog = async (workout_log_id) => {
  try {
    const response = await axios.delete(`${baseURL}/workoutlogs/${workout_log_id}`);
    console.log('Workout log deleted successfully:', response.data);
    return response.data; // You might want to return some part of the response to the caller
  } catch (error) {
    console.error('Error deleting workout log:', error);
    throw error; // Rethrow the error if you want the caller to handle it
  }
};

export const updateWorkoutLog = async (workout_log_id, updatedData) => {
  try {
    const response = await axios.put(`${baseURL}/workoutlogs/${workout_log_id}`, updatedData, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    console.log('Workout log updated successfully:', response.data);
    return response.data;
  } catch (error) {
    console.error('Error updating workout log:', error);
    throw error;
  }
};

// nutrients
export const getNutrient = (id) => (
    axios.get(`${baseURL}/nutrients/${id}`)
);

export const getAllNutrients = () => (
    axios.get(`${baseURL}/nutrients`)
);

export const deleteNutrient = (id) => (
    api.delete(`${baseURL}/nutrients/${id}`)
);

export const filterNutrientsByName = (name) => (
    api.get(`/nutrients?name=${name}`)
);

export const addNutrientLog = (
    member,
    includedNutrients,
    nutrientLogDate,
    totalCalories,
    config
) => (api.post(`${baseURL}/nutrientLogs/${member}/createNutrientLog`, {
        member,
        includedNutrients: includedNutrients,
        nutrientLogDate: nutrientLogDate,
        totalCalories: totalCalories,
    }, config)
);

export const deleteNutrientLog = async (id) => {
    try {
      const response = await axios.delete(`/api/nutrientLogs/${id}`);
      console.log('Nutrient log deleted successfully:', response.data);
      return response.data;
    } catch (error) {
      console.error('Error deleting nutrient log:', error);
      throw error;
    }
  };

  export const getNutrientLogs = async () => {
    return await api.get('/nutrientLogs'); // Adjust the endpoint as needed
  };


export default api
