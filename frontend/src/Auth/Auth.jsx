import React from 'react';
import {useLocation, useNavigate} from "react-router-dom";
import SignupForm from "./SignupForm";
import {Button} from "@mui/material";
import LoginForm from "./LoginForm";

const Auth = () => {
    const location = useLocation();
    const navigate = useNavigate();
    return (
        <div className={'flex justify-center items-center h-[95vh]'}>
            <div className={'shadow-lg p-5'}>
                {location.pathname === '/register'
                    ? (<><SignupForm/>
                        <div className={'text-center pt-5'}>
                            Already Have Account ? <Button onClick={() => navigate("/login")}>Login</Button>
                        </div>
                    </>)
                    : (<><
                        LoginForm/>
                        <div className={'text-center pt-5'}>
                            Not Have Account ? <Button onClick={() => navigate("/register")}>Signup</Button>
                        </div>
                    </>)
                }
            </div>
        </div>
    );
};

export default Auth;