import { Grid, MenuItem, Typography, Divider } from '@mui/material';
import { TextField, Select } from 'mui-rff';

const CPUForm = () => {

    /*
    speed gHz
    core number
    thread number
    cache memory
    manufacturer
*/

    return (
        <>
            <Grid container spacing={1}>
                <Typography variant="subtitle1" color="primary">
                    CPU information:
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
                        name='coreNumber'
                        label='Core Number'
                        required
                        fullWidth
                    >
                        <MenuItem value={2}>2</MenuItem>
                        <MenuItem value={4}>4</MenuItem>
                        <MenuItem value={8}>8</MenuItem>
                        <MenuItem value={16}>16</MenuItem>
                    </Select>
                </Grid>
                <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='threadNumber'
                        label='Threads per core'
                        required
                        fullWidth
                    >
                        <MenuItem value={4}>4</MenuItem>
                        <MenuItem value={8}>8</MenuItem>
                        <MenuItem value={16}>16</MenuItem>
                        <MenuItem value={32}>32</MenuItem>
                    </Select>
                </Grid>
                <Grid item xs={0} sm={6} />
                <Grid item xs={12} sm={6}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="cacheMemory"
                        label="Cache memory from (GB)"
                    />
                </Grid>
            </Grid>
        </>
    );
}

export default CPUForm;