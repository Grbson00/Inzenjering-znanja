import { Button, Container } from "@mui/material";
import { Form } from 'react-final-form';
import REGEX from "../../regex";
import GPUForm from "./GPUForm";

const numberRegex = new RegExp(REGEX.NUMBER)

const GPUSelection = () => {

    /*
        clock speed
        DDR tip
        GPU memory
        manufacturer
    */

    const onSubmit = (data) => {
        console.log(data);
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
        if (!numberRegex.test(values.fromSpeed)) {
            returnObject.fromSpeed = 'Numerical characters only!'
        }
        if (!numberRegex.test(values.toSpeed)) {
            returnObject.toSpeed = 'Numerical characters only!'
        }
        if (!numberRegex.test(values.gpuMemory)) {
            returnObject.gpuMemory = 'Numerical characters only!'
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
                        <GPUForm />
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

export default GPUSelection;