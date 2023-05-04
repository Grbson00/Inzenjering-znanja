import { Container, Stack, Typography } from "@mui/material";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import { useState } from "react";
import GPUSelection from "../components/findComponent/GPUSelection";
import CPUSelection from "../components/findComponent/CPUSelection";
import MotherBoardSelection from "../components/findComponent/MotherBoardSelection";
import RAMSelection from "../components/findComponent/RAMSelection";
import PowerSupplySelection from "../components/findComponent/PowerSupplySelection";

const FindComponentPage = () => {
    const [element, setElement] = useState('GPU')
    const handleChange = (event) => {
        setElement(event.target.value)
    }

    return (
        <>
            <Stack direction={'row'} spacing={4}>
                <Typography variant="subtitle1" color="initial">
                    What are you looking for?
                </Typography>
                <FormControl sx={{ width: '50%' }}>
                    <InputLabel id="demo-simple-select-label">Age</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={element}
                        label="Age"
                        onChange={handleChange}
                    >
                        <MenuItem value={'GPU'}>GPU</MenuItem>
                        <MenuItem value={'CPU'}>CPU</MenuItem>
                        <MenuItem value={'Motherboard'}>MotherBoard</MenuItem>
                        <MenuItem value={'RAM'}>RAM</MenuItem>
                        <MenuItem value={'PowerSupply'}>Power Supply</MenuItem>
                    </Select>
                </FormControl>
            </Stack>
            <Container sx={{marginTop: '5rem'}}>
                {
                    element === 'GPU' && <GPUSelection />
                }
                {
                    element === 'CPU' && <CPUSelection />
                }
                {
                    element === 'Motherboard' && <MotherBoardSelection />
                }
                {
                    element === 'RAM' && <RAMSelection />
                }
                {
                    element === 'PowerSupply' && <PowerSupplySelection />
                }
            </Container>
        </>
    );
}

export default FindComponentPage;