import { Box, Button, Container } from "@mui/material";
import { Form } from 'react-final-form';
import axios from "axios";
import EvaluateForm from "../components/evalaute/EvaluateForm";
import { useState } from "react";
import ResultChart from "../components/evalaute/ResultChart";

const EvaluatePCPage = () => {
    const [chartData, setChartData] = useState()
    const onSubmit = (data) => {
        const newData = {
            cpuSpeed: parseFloat(data.cpuSpeed),
            threadNum: parseInt(data.threadNum),
            coreNum: parseInt(data.coreNum),
            storageSize: parseInt(data.storageSize),
            gpuSize: parseInt(data.gpuMemory),
            ramSize: parseInt(data.ramSize),
            integrated: true,
            hdd: true
        }

        axios.post('http://localhost:8080/api/evaluate/', newData)
            .catch(e => {
                console.error(e)
            })
            .then((response) => {
                console.log('Development: ' + response.data[0]);
                console.log('Gaming: ' + response.data[1]);
                console.log('Crypto: ' + response.data[2]);
                console.log('Home: ' + response.data[3]);
                console.log('Work: ' + response.data[4]);
                console.log('Hosting: ' + response.data[5]);

                setChartData({
                    development: response.data[0],
                    gaming: response.data[1],
                    crypto: response.data[2],
                    home: response.data[3],
                    work: response.data[4],
                    hosting: response.data[5]
                })
            })
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
        if (!values.gpuMemory) {
            returnObject.gpuMemory = 'This field is required!'
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
                        {
                            chartData && (
                                <ResultChart chartData={chartData} />
                            )
                        }
                        <Container sx={{ display: 'grid', placeItems: 'center' }} mt={4}>
                            <Button variant="outlined" color="primary" type='submit'>
                                {chartData ? "Re-evaluate" : "Evaluate"}
                            </Button>
                        </Container>
                    </form>)}
            >
            </Form>
        </Box>
    );
}

export default EvaluatePCPage;