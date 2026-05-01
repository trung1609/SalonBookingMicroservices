import React from 'react';
import {Button, Container, TextField, Typography} from "@mui/material";
import {useFormik} from "formik";
import {useDispatch} from "react-redux";
import {useNavigate} from "react-router-dom";
import {registerUser} from "../Redux/Auth/action";

const SignupForm = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const formik = useFormik(
        {
            initialValues: {
                fullName: "",
                email: "",
                password: "",
                role: "CUSTOMER",
            },
            onSubmit: (values) => {
                console.log("Submitting values: ", values)
                dispatch(registerUser({userData: values, navigate}))
            }
        }
    );
    return (
        <Container component={'main'} maxWidth={'xs'}>
            <div className={'space-y-5'}>
                <Typography className={'text-center'} variant={'h5'}>
                    Signup
                </Typography>

                <form
                    className={'space-y-5'}
                    action="" onSubmit={formik.handleSubmit}>
                    <TextField
                        variant={'outlined'}
                        fullWidth
                        name={'fullName'}
                        id={'fullName'}
                        label={'Full Name'}
                        onChange={formik.handleChange}
                        value={formik.values.fullName}
                        required
                    />
                    <TextField
                        variant={'outlined'}
                        fullWidth
                        name={'email'}
                        id={'email'}
                        label={'Email Address'}
                        onChange={formik.handleChange}
                        value={formik.values.email}
                        required
                    />

                    <TextField
                        variant={'outlined'}
                        fullWidth
                        name={'password'}
                        id={'password'}
                        label={'Password'}
                        onChange={formik.handleChange}
                        value={formik.values.password}
                        type={'password'}
                        required
                    />

                    <Button sx={{py:".8rem"}} fullWidth variant={'contained'} type={'submit'}>Signup</Button>
                </form>
            </div>
        </Container>
    );
};

export default SignupForm;