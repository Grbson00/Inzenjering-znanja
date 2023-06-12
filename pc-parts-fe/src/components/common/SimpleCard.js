import { Box, Typography } from "@mui/material";

const SimpleCard = ({ content }) => {
    return (
        <Box sx={{ height: '100px', backgroundColor: 'lightblue', display: 'grid', placeItems: 'center', boxShadow: 'rgba(0, 0, 0, 0.35) 0px 5px 15px' }}>
            <Typography variant="subtitle1" fontWeight={"bold"}>
                {content}
            </Typography>
        </Box>
    );
}

export default SimpleCard;