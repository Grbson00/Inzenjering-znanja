import { Grid, Typography, Divider } from '@mui/material';
import { TextField } from 'mui-rff';

const PowerSupplyForm = () => {

    return (
        <>
            <Grid container spacing={1}>
                <Typography variant="subtitle1" color="primary">
                    Power Supply information:
                </Typography>
                <Divider variant='fullWidth' sx={{ width: '100%' }} />
                <Grid item xs={12} sm={3}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="fromWattage"
                        label="Lowest wattage"
                    />
                </Grid>
                <Grid item xs={12} sm={3}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="toWattage"
                        label="Highest wattage"
                    />
                </Grid>
            </Grid>
        </>
    );
}

export default PowerSupplyForm;