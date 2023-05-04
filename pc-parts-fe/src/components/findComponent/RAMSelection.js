import { Button, Container } from "@mui/material";
import { Form } from 'react-final-form';
import REGEX from "../../regex";
import GPUForm from "./GPUForm";
import RAMForm from "./RAMForm";

const numberRegex = new RegExp(REGEX.NUMBER)

const RAMSelection = () => {

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
        if (!numberRegex.test(values.memory)) {
            returnObject.memory = 'Numerical characters only!'
        }
        if (!values.ddr) {
            returnObject.ddr = 'This field is required!'
        }
        if (!values.size) {
            returnObject.size = 'This field is required!'
        }
        if (!values.mode) {
            returnObject.mode = 'This field is required!'
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
                        <RAMForm />
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

export default RAMSelection;