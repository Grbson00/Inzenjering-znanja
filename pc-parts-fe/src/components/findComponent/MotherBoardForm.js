import { Grid, MenuItem, Typography, Divider } from '@mui/material';
import { TextField, Select } from 'mui-rff';

const MotherBoardForm = () => {

    /* 
    manuf
    CPU socket
    DDR
    RAM Slots
    Form factor
    Chipset
    SATA slots
    */

    return (
        <>
            <Grid container spacing={1}>
                <Typography variant="subtitle1" color="primary">
                    Motherboard requirements:
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
                <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='cpuSocket'
                        label='CPU Socket type'
                        required
                        fullWidth
                    >
                        <MenuItem value={'LGA'}>LGA</MenuItem>
                        <MenuItem value={'AM'}>AM</MenuItem>
                        <MenuItem value={'sXe'}>sXe</MenuItem>
                    </Select>
                </Grid> 
                <Grid item xs={0} sm={6}/>
                <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='ddr'
                        label='Double Data Rate (DDR)'
                        required
                        fullWidth
                    >
                        <MenuItem value={3}>DDR3</MenuItem>
                        <MenuItem value={4}>DDR4</MenuItem>
                        <MenuItem value={5}>DDR5</MenuItem>
                    </Select>
                </Grid>
                <Grid item xs={12} sm={3}>
                    <TextField
                        autoComplete='off'
                        fullWidth
                        required
                        margin="normal"
                        name="ramSlots"
                        label="Number of RAM slots"
                    />
                </Grid>
                <Grid item xs={0} sm={6}/>
                <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='formFactor'
                        label='Form Factor (Size)'
                        required
                        fullWidth
                    >
                        <MenuItem value={'E-ATX'}>E-ATX</MenuItem>
                        <MenuItem value={'ATX'}>ATX</MenuItem>
                        <MenuItem value={'Micro-ATX'}>Micro-ATX</MenuItem>
                        <MenuItem value={'Mini-ITX'}>Mini-ITX</MenuItem>
                    </Select>
                </Grid>
                <Grid item xs={12} sm={3} sx={{ marginTop: '1rem' }}>
                    <Select
                        formControlProps={{ margin: 'none' }}
                        name='chipset'
                        label='Chipset'
                        required
                        fullWidth
                    >
                        <MenuItem value={'Intel'}>Intel</MenuItem>
                        <MenuItem value={'AMD'}>AMD</MenuItem>
                    </Select>
                </Grid>
                <Grid item xs={12} sm={3}>
                    <TextField
                        fullWidth
                        required
                        margin="normal"
                        name="sataSlots"
                        label="Number of SATA slots"
                    />
                </Grid>
            </Grid>
        </>
    );
}

export default MotherBoardForm;