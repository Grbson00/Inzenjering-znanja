import { Grid, MenuItem, Typography, Divider } from '@mui/material';
import { TextField, Select } from 'mui-rff';

const GPUForm = () => {

    return (
        <>
            <Grid container spacing={1}>
                <Typography variant="subtitle1" color="primary">
                    GPU information:
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
                        <MenuItem value={"Intel"}>Intel</MenuItem>
                        <MenuItem value={"AMD"}>AMD</MenuItem>
                        <MenuItem value={"NVidia"}>NVidia</MenuItem>
                    </Select>
                </Grid>
                <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='integrated'
                        label='Integrated'
                        required
                        fullWidth
                    >
                        <MenuItem value={'integrated'}>Integrated</MenuItem>
                        <MenuItem value={'not integrated'}>Not integrated</MenuItem>
                    </Select>
                </Grid>
                <Grid item xs={0} sm={6} />
                <Grid item xs={12} sm={3}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="fromSpeed"
                        label="Clock speed from (gHz)"

                    />
                </Grid>
                <Grid item xs={12} sm={3}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="toSpeed"
                        label="Clock speed to (gHz)"
                    />
                </Grid>
                <Grid item xs={0} sm={6} />
                <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='gpuMemory'
                        label='Memory (GB)'
                        required
                        fullWidth
                    >
                        <MenuItem value={1}>1</MenuItem>
                        <MenuItem value={2}>2</MenuItem>
                        <MenuItem value={4}>4</MenuItem>
                        <MenuItem value={4}>6</MenuItem>
                        <MenuItem value={8}>8</MenuItem>
                        <MenuItem value={8}>12</MenuItem>
                        <MenuItem value={16}>16</MenuItem>
                    </Select>
                </Grid>

            </Grid>
        </>
    );
}

export default GPUForm;