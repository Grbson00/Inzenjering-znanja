import { Button, Container } from "@mui/material";
import CPUForm from "./CPUForm"
import { Form } from 'react-final-form';
import REGEX from "../../regex";
import MotherBoardForm from "./MotherBoardForm";

const numberRegex = new RegExp(REGEX.NUMBER)

const MotherBoardSelection = () => {
    const onSubmit = (data) => {
        console.log(data);
    }
    const validate = (values) => {
        let returnObject = {}
        if (!values.manufacturer) {
            returnObject.manufacturer = 'This field is required!'
        }
        if (!values.cpuSocket) {
            returnObject.cpuSocket = 'This field is required!'
        }
        if (!values.ddr) {
            returnObject.ddr = 'This field is required!'
        }
        if (!values.formFactor) {
            returnObject.formFactor = 'This field is required!'
        }
        if (!values.chipset) {
            returnObject.chipset = 'This field is required!'
        }
        if (!numberRegex.test(values.ramSlots )) {
            returnObject.ramSlots  = 'Numerical characters only!'
        }
        if (!numberRegex.test(values.sataSlots )) {
            returnObject.sataSlots  = 'Numerical characters only!'
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
                        <MotherBoardForm />
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

export default MotherBoardSelection;