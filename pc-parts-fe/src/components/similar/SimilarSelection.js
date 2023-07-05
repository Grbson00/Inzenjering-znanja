import { Button, Container, Grid, Typography } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import { Form } from 'react-final-form';
import SimilarForm from "./SimilarForm";
import REGEX from "../../regex";
import PcCard from "./PcCard";

const regex = new RegExp(REGEX.NUMBER)

const SimilarSelection = () => {
    const [pcList, setPcList] = useState()
    const [showList, setShowList] = useState()
    const onSubmit = (data) => {
        axios.post('http://localhost:8080/api/search/similarPCs', data)
            .catch(e => {
                console.error(e)
                return
            })
            .then((response) => {
                console.log(response.data);
                setPcList(response.data)
            })
    }
    const validate = (values) => {
        let returnObject = {}
        if (!values.cpuSpeed) {
            returnObject.cpuSpeed = 'This field is required!'
        }
        if (!values.gpuSpeed) {
            returnObject.gpuSpeed = "From speed can't be faster than to speed"
        }
        if (!values.ramSize) {
            returnObject.ramSize = 'Numerical characters only!'
        }


        return returnObject
    }

    useEffect(() => {
        if (!pcList) return
        parseList(pcList)
    }, [pcList])

    const objectList = []
    const parseList = (list) => {
        for (let i = 0; i < list.length; i++) {
            console.log(i);
            var resultArray = list[i].split("|");
            let obj = {
                cpuName: resultArray[0],
                cpuSpeed: resultArray[1],
                gpuName: resultArray[2],
                gpuSpeed: resultArray[3],
                ramName: resultArray[4],
                ramSize: resultArray[5],
                coef: (1 - resultArray[6]).toFixed(2) * 100
            }
            console.log(obj);
            objectList.push(obj)
        }
        setShowList(objectList)
    }

    return (
        <>
            <Form
                onSubmit={onSubmit}
                validate={validate}
                render={({ handleSubmit, values }) => (
                    <form onSubmit={handleSubmit} noValidate>
                        <SimilarForm />
                        <Container sx={{ display: 'grid', placeItems: 'center' }}>
                            <Button variant="outlined" color="primary" type='submit'>
                                Search
                            </Button>
                        </Container>
                    </form>)}
            >
            </Form>
            {
                showList && <Typography mt={6} variant="h5" color="initial">Your results:</Typography>
            }
            <Grid container spacing={2} mt={4}>
                {
                    showList && showList.map((pc, index) => {
                        return (
                            <Grid item xs={4} key={index}>
                                <PcCard pc={pc} index={index + 1} />
                            </Grid>)
                    })
                }
            </Grid>
        </>
    );
}

export default SimilarSelection;