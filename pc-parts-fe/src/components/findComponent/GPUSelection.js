import { Button, Container, Grid, Typography } from "@mui/material";
import { Form } from 'react-final-form';
import GPUForm from "./GPUForm";
import axios from "axios";
import { useState } from "react";
import SimpleCard from "../common/SimpleCard";


const GPUSelection = () => {
    const [gpu, setGpu] = useState()
    const onSubmit = (data) => {
        axios.post('http://localhost:8080/api/search/gpu', data)
            .catch(e => {
                console.error(e)
            })
            .then((response) => {
                console.log(response);
                setGpu(response.data)
            })
    }
    const validate = (values) => {
        let returnObject = {}
        if (!values.manufacturer) {
            returnObject.manufacturer = 'This field is required!'
        }
        if (parseInt(values.fromSpeed) > parseInt(values.toSpeed)) {
            returnObject.fromSpeed = "From speed can't be faster than to speed"
            returnObject.toSpeed = "From speed can't be faster than to speed"
        }
        if (!values.fromSpeed) {
            returnObject.fromSpeed = 'Numerical characters only!'
        }
        if (!values.toSpeed) {
            returnObject.toSpeed = 'Numerical characters only!'
        }
        if (!values.gpuMemory) {
            returnObject.gpuMemory = 'Numerical characters only!'
        }
        if (!values.integrated) {
            returnObject.integrated = "This field is required!"
        }

        return returnObject
    }
    const replaceUnderscoresWithSpaces = (str) => {
        return str.replace(/_/g, ' ');
    };

    return (
        <>
            <Form
                onSubmit={onSubmit}
                validate={validate}
                render={({ handleSubmit, values }) => (
                    <form onSubmit={handleSubmit} noValidate>
                        <GPUForm />
                        <Container sx={{ display: 'grid', placeItems: 'center' }}>
                            <Button variant="outlined" color="primary" type='submit'>
                                Search
                            </Button>
                        </Container>
                    </form>)}
            >
            </Form>
            {
                gpu && <Typography mt={6} variant="h5" color="initial">Your results:</Typography>
            }
            <Grid container spacing={2} mt={4}>
                {
                    gpu && gpu.map((gpu) => {
                        return (<Grid item xs={4} key={gpu}>
                            <SimpleCard content={replaceUnderscoresWithSpaces(gpu)} />
                        </Grid>)
                    })
                }
            </Grid>
        </>
    );
}

export default GPUSelection;