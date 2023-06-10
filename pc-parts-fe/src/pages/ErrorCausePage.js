import { Button, FormControl, InputLabel, MenuItem, Select, Stack } from "@mui/material";
import axios from "axios";
import { useState, useEffect } from "react";

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

    return (
        <Stack direction={"row"} spacing={4}>
            <Stack direction={"column"} spacing={4} width={"70%"} mt={15}>
                <FormControl>
                    <InputLabel id="multi-select-label">Select Values</InputLabel>
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
                {causes.map((value) => (
                            <MenuItem key={value} value={value}>
                                {value.name} : {value.percentage}
                            </MenuItem>
                        ))}
            </Stack >
            <img src="blue_screen.webp" alt="blue_screen" width={600} height={400} />
        </Stack>
    );
};

export default ErrorCausePage;