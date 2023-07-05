import { Grid, Typography, Divider } from '@mui/material';
import { TextField } from 'mui-rff';

const SimilarForm = () => {

    return (
        <>
            <Grid container spacing={1}>
                <Typography variant="subtitle1" color="primary">
                    GPU information:
                </Typography>
                <Divider variant='fullWidth' sx={{ width: '100%' }} />
                <Grid item xs={12}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="cpuSpeed"
                        label="Your cpu speed"

                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="gpuSpeed"
                        label="Your gpu speed"
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="ramSize"
                        label="Your ram size"
                    />
                </Grid>
            </Grid>
        </>
    );
}

export default SimilarForm;