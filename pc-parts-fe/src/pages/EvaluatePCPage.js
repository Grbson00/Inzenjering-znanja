import { Box, Button, Container } from "@mui/material";
import { Form } from 'react-final-form';
import REGEX from "../regex";
import axios from "axios";
import { useState } from "react";
import EvaluateForm from "../components/evalaute/EvaluateForm";

const numberRegex = new RegExp(REGEX.NUMBER)

const EvaluatePCPage = () => {
    const onSubmit = (data) => {
        // axios.post('http://localhost:8080/api/search/cpu', data)
        //     .catch(e => {
        //         console.error(e)
        //     })
        //     .then((response) => {
        //         console.log(response.data);
        //         setCpuArr(response.data)
        //     })
        console.log(data);
    }
    const validate = (values) => {
        let returnObject = {}
        if (!values.cpuSpeed) {
            returnObject.cpuSpeed = 'This field is required!'
        }
        if (!values.threadNum) {
            returnObject.threadNum = 'This field is required!'
        }
        if (!values.coreNum) {
            returnObject.coreNum = 'This field is required!'
        }
        if (!values.storageSize) {
            returnObject.storageSize = 'This field is required!'
        }
        if (!values.storageType) {
            returnObject.storageType = 'This field is required!'
        }
        if (!values.gpuMemory) {
            returnObject.gpuMemory = 'This field is required!'
        }
        if (!values.gpuType) {
            returnObject.gpuType = 'This field is required!'
        }
        if (!values.ramSize) {
            returnObject.ramSize = 'This field is required!'
        }

        return returnObject
    }

    return (
        <Box mt={6}>
            <Form
                onSubmit={onSubmit}
                validate={validate}
                render={({ handleSubmit, values }) => (
                    <form onSubmit={handleSubmit} noValidate>
                        <EvaluateForm />
                        <Container sx={{ display: 'grid', placeItems: 'center' }}>
                            <Button variant="outlined" color="primary" type='submit'>
                                Evaluate
                            </Button>
                        </Container>
                    </form>)}
            >
            </Form>
        </Box>
    );
}

export default EvaluatePCPage;