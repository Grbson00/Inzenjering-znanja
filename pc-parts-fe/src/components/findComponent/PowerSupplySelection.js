import { Button, Container } from "@mui/material";
import { Form } from 'react-final-form';
import REGEX from "../../regex";
import PowerSupplyForm from "./PowerSupplyForm";
import axios from "axios";

const numberRegex = new RegExp(REGEX.NUMBER)

const PowerSupplySelection = () => {

    /*
    power
    efficiency - shouldn't search by this
    connector - shouldn't search by this
    manufacturer
    */

    const onSubmit = (data) => {
        axios.post('http://localhost:8080/api/search/power', data)
            .catch(e => {
                console.error(e)
            })
            .then((response) => {
                console.log(response);
            })
        console.log(data);
        console.log(data);
    }
    const validate = (values) => {
        let returnObject = {}
        if (!values.manufacturer) {
            returnObject.manufacturer = 'This field is required!'
        }
        if (!numberRegex.test(values.wattage)) {
            returnObject.wattage = 'Numerical characters only!'
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
                        <PowerSupplyForm />
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

export default PowerSupplySelection;