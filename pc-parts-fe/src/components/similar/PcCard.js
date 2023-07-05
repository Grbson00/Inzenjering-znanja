import { Container, Typography } from "@mui/material";

const PcCard = ({ pc, index }) => {
    const replaceUnderscoresWithSpaces = (str) => {
        return str.replace(/_/g, ' ');
    };

    return (
        <Container sx={{ height: '420px', backgroundColor: 'lightblue', display: 'grid', placeItems: 'center', boxShadow: 'rgba(0, 0, 0, 0.35) 0px 5px 15px' }} >
            <Typography variant="h6" fontWeight={"bold"}>
                Configuration {index}:
            </Typography>
            <Container sx={{ backgroundColor: "#7eb0bf", color: '#fefefe', borderRadius: '5px' }}>
                <Typography variant="h6">
                    CPU
                </Typography>
                <Typography variant="subtitle1">
                    {replaceUnderscoresWithSpaces(pc.cpuName)}
                </Typography>
                <Typography variant="subtitle1">
                    Speed: {pc.cpuSpeed}gHZ
                </Typography>
            </Container>
            <Container sx={{ backgroundColor: "#7eb0bf", color: '#fefefe', borderRadius: '5px' }}>
                <Typography variant="h6">
                    GPU
                </Typography>
                <Typography variant="subtitle1">
                    {replaceUnderscoresWithSpaces(pc.gpuName)}
                </Typography>
                <Typography variant="subtitle1">
                    Speed: {pc.gpuSpeed}gHZ
                </Typography>
            </Container>
            <Container sx={{ backgroundColor: "#7eb0bf", color: '#fefefe', borderRadius: '5px' }}>
                <Typography variant="h6">
                    RAM
                </Typography>
                <Typography variant="subtitle1">
                    {replaceUnderscoresWithSpaces(pc.ramName)}
                </Typography>
                <Typography variant="subtitle1">
                    Size: {pc.ramSize}GB
                </Typography>
            </Container>
            <Typography variant="subtitle1">
                Similarity: {pc.coef}%
            </Typography>
        </Container>
    );
}

export default PcCard;