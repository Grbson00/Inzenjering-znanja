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
        // if (!values.manufacturer) {
        //     returnObject.manufacturer = 'This field is required!'
        // }
        // if (parseInt(values.fromSpeed) > parseInt(values.toSpeed)) {
        //     returnObject.fromSpeed = "Invalid value!"
        //     returnObject.toSpeed = "Invalid value!"
        // }
        // if (!numberRegex.test(values.fromSpeed)) {
        //     returnObject.fromSpeed = 'Numerical characters only!'
        // }
        // if (!numberRegex.test(values.toSpeed)) {
        //     returnObject.toSpeed = 'Numerical characters only!'
        // }
        // if (parseInt(values.coreNumberFrom) > parseInt(values.coreNumberTo)) {
        //     returnObject.coreNumberFrom = "Invalid value!"
        //     returnObject.coreNumberTo = "Invalid value!"
        // }
        // if (!numberRegex.test(values.coreNumberFrom)) {
        //     returnObject.coreNumberFrom = 'Numerical characters only!'
        // }
        // if (!numberRegex.test(values.coreNumberTo)) {
        //     returnObject.coreNumberTo = 'Numerical characters only!'
        // }
        // if (parseInt(values.threadNumberFrom) > parseInt(values.threadNumberTo)) {
        //     returnObject.threadNumberFrom = "Invalid value!"
        //     returnObject.threadNumberTo = "Invalid value!"
        // }
        // if (!numberRegex.test(values.threadNumberFrom)) {
        //     returnObject.threadNumberFrom = 'Numerical characters only!'
        // }
        // if (!numberRegex.test(values.threadNumberTo)) {
        //     returnObject.threadNumberTo = 'Numerical characters only!'
        // }
        // if (parseInt(values.cacheMemoryFrom) > parseInt(values.cacheMemoryTo)) {
        //     returnObject.cacheMemoryFrom = "Invalid value!"
        //     returnObject.cacheMemoryTo = "Invalid value!"
        // }
        // if (!numberRegex.test(values.cacheMemoryFrom)) {
        //     returnObject.cacheMemoryFrom = 'Numerical characters only!'
        // }
        // if (!numberRegex.test(values.cacheMemoryTo)) {
        //     returnObject.cacheMemoryTo = 'Numerical characters only!'
        // }

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