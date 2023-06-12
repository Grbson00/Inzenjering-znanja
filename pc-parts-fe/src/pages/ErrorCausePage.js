import { Box, Button, FormControl, InputLabel, MenuItem, Select, Stack, useTheme, Typography } from "@mui/material";
import axios from "axios";
import { useState, useEffect } from "react";
import FiberManualRecordIcon from '@mui/icons-material/FiberManualRecord';

const ErrorCausePage = () => {
    const [selectedValues, setSelectedValues] = useState([]);
    const [causes, setCauses] = useState([]);
    const [symptoms, setSymptoms] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/bayes/').catch(e => {
            console.error(e)
        })
            .then((response) => {
                console.log(response)
                setSymptoms(response.data)
            })
    }, []);
    /*const symptoms = [
        "Blue Screen", "PC not starting", "No internet connection", "Headphones not detected"
    ]*/

    const handleSelectionChange = (event) => {
        setSelectedValues(event.target.value);
    };

    const handleButtonClick = () => {
        axios.post('http://localhost:8080/api/bayes/', { symptomList: selectedValues })
            .catch(e => {
                console.error(e)
            })
            .then((response) => {
                console.log(response.data);
                setCauses(response.data)
            })
        console.log(selectedValues);
    };

    const capitalizeFirstLetters = (str) => {
        return str
            .split(' ')
            .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
            .join(' ');
    };

    const theme = useTheme()

    return (
        <Stack direction={"row"} spacing={4}>
            <Stack direction={"column"} spacing={4} width={"70%"} mt={15}>
                <FormControl>
                    <InputLabel id="multi-select-label">Select the Problems you are having!</InputLabel>
                    <Select
                        labelId="multi-select-label"
                        multiple
                        value={selectedValues}
                        onChange={handleSelectionChange}
                    >
                        {symptoms.map((value) => (
                            <MenuItem key={value} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <Button variant="contained" color="primary" onClick={handleButtonClick}>
                    What's causing my problems?
                </Button>
                {causes.map((value, index) => (
                    <Stack direction={"row"} spacing={2} key={index} value={value}>
                        <FiberManualRecordIcon sx={{ color: theme.palette.primary.main }} />
                        <Typography variant="subtitle2" >
                            {capitalizeFirstLetters(value.name)} : {value.percentage}%
                        </Typography>
                    </Stack>
                ))}
            </Stack >
            <img src="blue_screen.webp" alt="blue_screen" width={600} height={400} />
        </Stack>
    );
};

export default ErrorCausePage;