import { Grid, MenuItem, Typography, Divider } from '@mui/material';
import { TextField, Select } from 'mui-rff';

const RAMForm = () => {

    /*
    DDR
    Memory
    Dual/Quad
    DIMM / SO-DIMM
    manufacturer    
    */

    return (
        <>
            <Grid container spacing={1}>
                <Typography variant="subtitle1" color="primary">
                    RAM requirements:
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
                        <MenuItem value={'Kingston'}>Kingston</MenuItem>
                        <MenuItem value={'Transcend'}>Transcend</MenuItem>
                        <MenuItem value={'Siliconpower'}>Siliconpower</MenuItem>
                    </Select>
                </Grid>
                <Grid item xs={0} sm={9} />
                <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='ddr'
                        label='Double Data Rate (DDR)'
                        required
                        fullWidth
                    >
                        <MenuItem value={'DDR3'}>DDR3</MenuItem>
                        <MenuItem value={'DDR4'}>DDR4</MenuItem>
                        <MenuItem value={'DDR5'}>DDR5</MenuItem>
                    </Select>
                </Grid>
                <Grid item xs={12} sm={3}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="memory"
                        label="Single stick memory (GB)"
                    />
                </Grid>
                <Grid item xs={0} sm={6} />
                <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='size'
                        label='Size'
                        required
                        fullWidth
                    >
                        <MenuItem value={'SO-DIMM'}>SO-DIMM (small)</MenuItem>
                        <MenuItem value={'DIMM'}>DIMM (large)</MenuItem>
                    </Select>
                </Grid>
            </Grid>
        </>
    );
}

export default RAMForm;