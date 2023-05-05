import { Box } from "@mui/material";

const SimpleCard = ({content}) => {
    return ( 
        <Box sx={{height: '200px', backgroundColor: 'lightblue', display: 'grid', placeItems: 'center'}}>
            {content}
        </Box>
     );
}
 
export default SimpleCard;