import { Box, Button, FormControl, InputLabel, MenuItem, Select, Stack, useTheme, Typography } from "@mui/material";
import axios from "axios";
import { useState, useEffect } from "react";
import FiberManualRecordIcon from '@mui/icons-material/FiberManualRecord';


const RecommendUpgradePage = () => {


    const [selectedValues1, setSelectedValues1] = useState();
    const [selectedValues2, setSelectedValues2] = useState();
    const [selectedValues3, setSelectedValues3] = useState();
    const [selectedValues4, setSelectedValues4] = useState();
    const [selectedValues5, setSelectedValues5] = useState();
    const [selectedValues6, setSelectedValues6] = useState();

    const [upgrades, setUpgrades] = useState([]);

    const [motherboards, setMotherboards] = useState([]);
    const [cpus, setCpus] = useState([]);
    const [gpus, setGpus] = useState([]);
    const [rams, setRams] = useState([]);
    const [psus, setPsus] = useState([]);

    useEffect(() => {

        axios.get('http://localhost:8080/api/search/motherboard/all').catch(e => {
            console.error(e)
        }).then((response) => {
                console.log(response)
                setMotherboards(response.data)
        });

        axios.get('http://localhost:8080/api/search/cpu/all').catch(e => {
            console.error(e)
        }).then((response) => {
                console.log(response)
                setCpus(response.data)
        });

        axios.get('http://localhost:8080/api/search/gpu/all').catch(e => {
            console.error(e)
        }).then((response) => {
                console.log(response)
                setGpus(response.data)
        });

        axios.get('http://localhost:8080/api/search/ram/all').catch(e => {
            console.error(e)
        }).then((response) => {
                console.log(response)
                setRams(response.data)
        });

        axios.get('http://localhost:8080/api/search/power/all').catch(e => {
            console.error(e)
        }).then((response) => {
                console.log(response)
                setPsus(response.data)
        });

            
    }, []);


    const handleSelectionChange1= (event) => {
        setSelectedValues1(event.target.value);
    };
    const handleSelectionChange2= (event) => {
        setSelectedValues2(event.target.value);
    };
    const handleSelectionChange3= (event) => {
        setSelectedValues3(event.target.value);
    };
    const handleSelectionChange4= (event) => {
        setSelectedValues4(event.target.value);
    };
    const handleSelectionChange5= (event) => {
        setSelectedValues5(event.target.value);
    };
    const handleSelectionChange6= (event) => {
        setSelectedValues6(event.target.value);
    };

    const handleButtonClick = () => {
        axios.get('http://localhost:8080/api/search/upgrade/'+ selectedValues1 + '/' + selectedValues2 + '/' + selectedValues3 + '/' + selectedValues4 + '/' + selectedValues5 + '/' + selectedValues6)
            .catch(e => {
                console.error(e)
            })
            .then((response) => {
                console.log(response.data);
                setUpgrades(response.data)
            })
        console.log(selectedValues1);
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
            <Stack direction={"column"} spacing={4} width={"70%"} mt={10}>
                <FormControl>
                    <InputLabel id="multi-select-label">Select component to upgrade!</InputLabel>
                    <Select
                        labelId="multi-select-label"
                        value={selectedValues1}
                        onChange={handleSelectionChange1}
                        required
                    >
                            <MenuItem value="CPU"> CPU </MenuItem>
                            <MenuItem value="GPU"> GPU </MenuItem>
                            <MenuItem value="RAM"> RAM </MenuItem>
                            <MenuItem value="PSU"> PSU </MenuItem>
                    </Select>
                </FormControl>

                <FormControl>
                    <InputLabel id="1">Select Your Motherboard</InputLabel>
                    <Select
                        labelId="1"
                        value={selectedValues2}
                        onChange={handleSelectionChange2}
                        required
                    >
                        {motherboards.map((value) => (
                            <MenuItem key={value} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>

                <FormControl>
                    <InputLabel id="1">Select Your CPU</InputLabel>
                    <Select
                        labelId="1"
                        value={selectedValues3}
                        onChange={handleSelectionChange3}
                    >
                        {cpus.map((value) => (
                            <MenuItem key={value} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>

                <FormControl>
                    <InputLabel id="1">Select Your GPU</InputLabel>
                    <Select
                        labelId="1"
                        value={selectedValues4}
                        onChange={handleSelectionChange4}
                    >
                        {gpus.map((value) => (
                            <MenuItem key={value} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>

                <FormControl>
                    <InputLabel id="1">Select Your RAM</InputLabel>
                    <Select
                        labelId="1"
                        value={selectedValues5}
                        onChange={handleSelectionChange5}
                    >
                        {rams.map((value) => (
                            <MenuItem key={value} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>

                <FormControl>
                    <InputLabel id="1">Select Your PSU</InputLabel>
                    <Select
                        labelId="1"
                        value={selectedValues6}
                        onChange={handleSelectionChange6}
                    >
                        {psus.map((value) => (
                            <MenuItem key={value} value={value}>
                                {value}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>


                <Button variant="contained" color="primary" onClick={handleButtonClick}>
                    Find best upgrade
                </Button>

                {upgrades.map((value, index) => (
                    <Stack direction={"row"} spacing={2} key={index} value={value}>
                        <FiberManualRecordIcon sx={{ color: theme.palette.primary.main }} />
                        <Typography variant="subtitle2" >
                            {capitalizeFirstLetters(value)}
                        </Typography>
                    </Stack>
                ))}
            </Stack >
            <img src="upgrade.jpg" alt="upgrade" width={600} height={400} />
        </Stack>
     );
}
 
export default RecommendUpgradePage;