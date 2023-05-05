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
                {/* <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='manufacturer'
                        label='Manufacturer'
                        required
                        fullWidth
                    >
                        <MenuItem value={'Intel'}>Intel</MenuItem>
                        <MenuItem value={'AMD'}>AMD</MenuItem>
                    </Select>
                </Grid> 
                <Grid item xs={0} sm={9} /> */}
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
                <Grid item xs={12} sm={3}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="coreNumberFrom"
                        label="Core number from"

                    />
                </Grid>
                <Grid item xs={12} sm={3}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="coreNumberTo"
                        label="Core number to"
                    />
                </Grid>
                <Grid item xs={0} sm={6} />
                <Grid item xs={12} sm={3}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="threadNumberFrom"
                        label="Thread number from"

                    />
                </Grid>
                <Grid item xs={12} sm={3}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="threadNumberTo"
                        label="Thread number to"
                    />
                </Grid>
                <Grid item xs={0} sm={6} />
                <Grid item xs={12} sm={3}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="cacheMemoryFrom"
                        label="Cache memory from (GB)"

                    />
                </Grid>
                <Grid item xs={12} sm={3}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="cacheMemoryTo"
                        label="Cache memory to (GB)"
                    />
                </Grid>
                <Grid item xs={0} sm={6} />
            </Grid>
        </>
    );
}

export default CPUForm;