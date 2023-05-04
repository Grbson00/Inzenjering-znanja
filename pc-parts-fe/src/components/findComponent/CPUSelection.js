import { Button, Container } from "@mui/material";
import CPUForm from "./CPUForm"
import { Form } from 'react-final-form';
import REGEX from "../../regex";
import axios from "axios";

const numberRegex = new RegExp(REGEX.NUMBER)

const CPUSelection = () => {
    const onSubmit = (data) => {
        axios.post('http://localhost:8080/api/search/cpu', data)
            .catch(e => {
                console.error(e)
            })
            .then((response) => {
                console.log(response);
            })
        console.log(data);
    }
    const validate = (values) => {
        let returnObject = {}
        if (!values.manufacturer) {
            returnObject.manufacturer = 'This field is required!'
        }
        if (!values.coreNumber) {
            returnObject.coreNumber = 'This field is required!'
        }
        if (!values.threadNumber) {
            returnObject.threadNumber = 'This field is required!'
        }
        if (parseInt(values.fromSpeed) > parseInt(values.toSpeed)) {
            returnObject.fromSpeed = "From speed can't be faster than to speed"
            returnObject.toSpeed = "From speed can't be faster than to speed"
        }
        if (!numberRegex.test(values.fromSpeed)) {
            returnObject.fromSpeed = 'Numerical characters only!'
        }
        if (!numberRegex.test(values.toSpeed)) {
            returnObject.toSpeed = 'Numerical characters only!'
        }
        if (!numberRegex.test(values.cacheMemory)) {
            returnObject.cacheMemory = 'Numerical characters only!'
        }

        return returnObject
    }

    return (
        <>
            <Form
                onSubmit={onSubmit}
                validate={validate}
                render={({ handleSubmit, values }) => (
                    <form onSubmit={handleSubmit} noValidate>
                        <CPUForm />
                        <Container sx={{ display: 'grid', placeItems: 'center' }}>
                            <Button variant="outlined" color="primary" type='submit'>
                                Search
                            </Button>
                        </Container>
                    </form>)}
            >
            </Form>

        </>
    );
}

export default CPUSelection;