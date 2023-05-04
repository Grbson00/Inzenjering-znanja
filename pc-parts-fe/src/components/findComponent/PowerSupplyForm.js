import { Grid, MenuItem, Typography, Divider } from '@mui/material';
import { TextField, Select } from 'mui-rff';

const PowerSupplyForm = () => {

    /*
    power
    efficiency - shouldn't search by this
    connector - shouldn't search by this
    manufacturer
    */

    return (
        <>
            <Grid container spacing={1}>
                <Typography variant="subtitle1" color="primary">
                    Power Supply information:
                </Typography>
                <Divider variant='fullWidth' sx={{ width: '100%' }} />
                <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='manufacturer'
                        label='Manufacturer'
                        required
                        fullWidth
                    >
                        <MenuItem value={2}>Intel</MenuItem>
                        <MenuItem value={4}>AMD</MenuItem>
                        <MenuItem value={8}>Apple</MenuItem>
                        <MenuItem value={16}>Fujitsu</MenuItem>
                    </Select>
                </Grid> 
                <Grid item xs={0} sm={9} />
                <Grid item xs={12} sm={3}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="wattage"
                        label="Lowest wattage"
                    />
                </Grid>
            </Grid>
        </>
    );
}

export default PowerSupplyForm;