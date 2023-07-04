import { Button, Container, Grid, Typography } from "@mui/material";
import { Form } from 'react-final-form';
import REGEX from "../../regex";
import PowerSupplyForm from "./PowerSupplyForm";
import axios from "axios";
import { useState } from "react";
import SimpleCard from "../common/SimpleCard";

const numberRegex = new RegExp(REGEX.NUMBER)

const PowerSupplySelection = () => {
    const [psu, setPsu] = useState()

    const onSubmit = (data) => {
        console.log(data);
        axios.post('http://localhost:8080/api/search/power', data)
            .catch(e => {
                console.error(e)
            })
            .then((response) => {
                console.log(response);
                setPsu(response.data)
            })
    }
    const validate = (values) => {
        let returnObject = {}
        if (!numberRegex.test(values.fromWattage)) {
            returnObject.fromWattage = 'Numerical characters only!'
        }
        if (!numberRegex.test(values.toWattage)) {
            returnObject.toWattage = 'Numerical characters only!'
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
                        <PowerSupplyForm />
                        <Container sx={{ display: 'grid', placeItems: 'center' }}>
                            <Button variant="outlined" color="primary" type='submit'>
                                Search
                            </Button>
                        </Container>
                    </form>)}
            >
            </Form>
            {
                psu && <Typography mt={6} variant="h5" color="initial">Your results:</Typography>
            }
            <Grid container spacing={2} mt={4}>
                {
                    psu && psu.map((psu) => {
                        return (<Grid item xs={4} key={psu}>
                            <SimpleCard content={replaceUnderscoresWithSpaces(psu)} />
                        </Grid>)
                    })
                }
            </Grid>
        </>
    );
}

export default PowerSupplySelection;