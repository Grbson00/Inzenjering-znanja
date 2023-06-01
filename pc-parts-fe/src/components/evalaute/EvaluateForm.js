import { Grid, Typography, Divider } from '@mui/material';
import { TextField } from 'mui-rff';
import { Radios } from 'mui-rff';


const EvaluateForm = () => {

    return (
        <>
            <Grid container spacing={2}>
                <Typography variant="subtitle1" color="primary">
                    Configuration information:
                </Typography>
                <Divider variant='fullWidth' sx={{ width: '100%' }} />
                <Grid item xs={12} sm={4}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="cpuSpeed"
                        label="CPU speed (gHz)"

                    />
                </Grid>
                <Grid item xs={12} sm={4}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="threadNum"
                        label="Thread number"
                    />
                </Grid>
                <Grid item xs={12} sm={4}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="coreNum"
                        label="Core number"

                    />
                </Grid>

                <Grid item xs={4} >
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="storageSize"
                        label="Storage size (GB)"

                    />
                </Grid>
                <Grid item xs={12} sm={3}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="ramSize"
                        label="RAM memory size (GB)"
                    />
                </Grid>
                <Grid item xs={2} />
                <Grid item xs={4}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="gpuMemory"
                        label="GPU Dedicated memory size (GB)"
                    />
                </Grid>
                <Grid item xs={2} />

            </Grid>
        </>
    );
}

export default EvaluateForm;