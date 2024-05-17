import React, { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { getAllNutrients, addNutrientLog } from '../../api/axiosConfig';
import Button from '@mui/material/Button';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import MenuItem from '@mui/material/MenuItem';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import List from '@mui/material/List';
import Select from '@mui/material/Select';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';

const NutrientLogCreatePage = ({ memberId, onClose }) => {
  const { register, handleSubmit, reset } = useForm();
  const [loading, setLoading] = useState(false);
  const [nutrients, setNutrients] = useState([]);

  useEffect(() => {
    fetchNutrients();
  }, []);

  const fetchNutrients = async () => {
    try {
      const response = await getAllNutrients();
      setNutrients(response.data);
    } catch (error) {
      console.error('Error fetching nutrients:', error);
    }
  };

  const onSubmit = async (data) => {
    setLoading(true);
    try {
      await addNutrientLog({ ...data, memberId });
      reset();
      onClose();
    } catch (error) {
      console.error('Error adding nutrient log:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <DialogTitle>Create Nutrient Log</DialogTitle>
      <DialogContent>
        <DialogContentText>
          Fill in the details to create a new nutrient log.
        </DialogContentText>
        <form onSubmit={handleSubmit(onSubmit)}>
          <div>
            <label>Date:</label>
            <input type="date" {...register('nutrientLogDate', { required: true })} />
          </div>
          <div>
            <FormControl fullWidth>
              <InputLabel id="nutrient-label">Nutrient</InputLabel>
              <Select
                labelId="nutrient-label"
                {...register('nutrientId', { required: true })}
                defaultValue=""
              >
                <List>
                {nutrients.map((nutrient) => (
                  <ListItem key={nutrient.nutrientId} value={nutrient.nutrientId}>
                    {nutrient.nutrientName}
                    <ListItemText primary={nutrient.nutrientName} />
                  </ListItem>
                ))}
                </List>
              </Select>
            </FormControl>
          </div>
          <DialogActions>
            <Button onClick={onClose} color="primary">Cancel</Button>
            <Button type="submit" color="primary" disabled={loading}>Create</Button>
          </DialogActions>
        </form>
      </DialogContent>
    </div>
  );
};

export default NutrientLogCreatePage;
