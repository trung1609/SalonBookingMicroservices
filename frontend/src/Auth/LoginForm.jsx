import React from 'react';
import {Button, Container, TextField, Typography} from "@mui/material";
import {useFormik} from "formik";

const LoginForm = () => {
    const formik = useFormik(
        {
            initialValues: {
                email: "",
                password: "",
            },
            onSubmit: (values) => {
                console.log("Submitting values: ", values)
            }
        }
    );
    return (
        <Container component={'main'} maxWidth={'xs'}>
            <div className={'space-y-5'}>
                <Typography className={'text-center'} variant={'h5'}>
                    Login
                </Typography>

                <form
                    className={'space-y-5'}
                    action="" onSubmit={formik.handleSubmit}>
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

                    <Button sx={{py:".8rem"}} fullWidth variant={'contained'} type={'submit'}>Login</Button>
                </form>
            </div>
        </Container>
    );
};

export default LoginForm;