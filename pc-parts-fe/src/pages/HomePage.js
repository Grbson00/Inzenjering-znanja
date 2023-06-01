import { Box, Button, Grid } from '@mui/material';
import Typography from '@mui/material/Typography'
const HomePage = () => {
    return (
        <Box sx={{ display: 'block', height: '100vh', width: '100%', textAlign: 'center' }}>
            <Typography variant="h2" color="initial">
                Welcome to Build Master
            </Typography>
            <Grid container spacing={8} sx={{ width: '80%', margin: '5rem auto' }}>
                <Grid item xs={12} md={6} sx={{display: 'grid', placeItems: 'center'}}>
                    <Button element={'a'} href='/find' variant="contained" fullWidth color="primary" sx={{height: '6rem', fontSize: '1.5rem'}}>
                        Find a part
                    </Button>
                </Grid>
                <Grid item xs={12} md={6} sx={{display: 'grid', placeItems: 'center'}}>
                    <Button element={'a'} href='/upgrade' variant="contained" fullWidth color="primary" sx={{height: '6rem', fontSize: '1.5rem'}}>
                        Find an upgrade
                    </Button>
                </Grid>
                <Grid item xs={12} md={6} sx={{display: 'grid', placeItems: 'center'}}>
                    <Button element={'a'} href='/evaluate' variant="contained" fullWidth color="primary" sx={{height: '6rem', fontSize: '1rem'}}>
                        Evaluate PC purpose
                    </Button>
                </Grid>
                <Grid item xs={12} md={6} sx={{display: 'grid', placeItems: 'center'}}>
                    <Button variant="contained" fullWidth color="primary" sx={{height: '6rem', fontSize: '1rem'}}>
                        Coming soon...
                    </Button>
                </Grid>
            </Grid>
        </Box>
    );
}

export default HomePage;