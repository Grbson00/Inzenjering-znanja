import { Card, CardMedia, CardContent, Typography, CardActions, Button } from "@mui/material";
import { Link } from "react-router-dom";

const FunctionalityCard = ({ image, title, route }) => {
    return (
        <Card sx={{ width: 210 }}>
            <CardMedia
                sx={{ height: 98 }}
                image={image}
                title={title}
            />
            <CardContent sx={{ textAlign: 'center' }}>
                <Typography gutterBottom variant="h5" component="div">
                    {title}
                </Typography>
            </CardContent>
            <CardActions sx={{ justifyContent: 'center' }}>
                <Link to={route}>
                    <Button variant="contained" size="small">Try out</Button>
                </Link>
            </CardActions>
        </Card>
    );
}

export default FunctionalityCard;