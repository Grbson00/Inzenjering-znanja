import { Button, Container, Grid, Typography } from "@mui/material";
import { Form } from 'react-final-form';
import REGEX from "../../regex";
import RAMForm from "./RAMForm";
import axios from "axios";
import { useState } from "react";
import SimpleCard from "../common/SimpleCard";

const numberRegex = new RegExp(REGEX.NUMBER)

const RAMSelection = () => {
    const [ram, setRam] = useState()

    const onSubmit = (data) => {
        console.log(data);

        axios.post('http://localhost:8080/api/search/ram', data)
            .catch(e => {
                console.error(e)
            })
            .then((response) => {
                console.log(response);
                setRam(response.data)
            })
    }
    const validate = (values) => {
        let returnObject = {}
        if (!values.manufacturer) {
            returnObject.manufacturer = 'This field is required!'
        }
        if (!numberRegex.test(values.memory)) {
            returnObject.memory = 'Numerical characters only!'
        }
        if (!values.ddr) {
            returnObject.ddr = 'This field is required!'
        }
        if (!values.size) {
            returnObject.size = 'This field is required!'
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
                        <RAMForm />
                        <Container sx={{ display: 'grid', placeItems: 'center' }}>
                            <Button variant="outlined" color="primary" type='submit'>
                                Search
                            </Button>
                        </Container>
                    </form>)}
            >
            </Form>
            {
                ram && <Typography mt={6} variant="h5" color="initial">Your results:</Typography>
            }
            <Grid container spacing={2} mt={4}>
                {
                    ram && ram.map((ram) => {
                        return (<Grid item xs={4} key={ram}>
                            <SimpleCard content={replaceUnderscoresWithSpaces(ram)} />
                        </Grid>)
                    })
                }
            </Grid>
        </>
    );
}

export default RAMSelection;