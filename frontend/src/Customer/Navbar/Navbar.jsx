import React, {useEffect} from 'react';
import {Avatar, Badge, Button, IconButton, Menu, MenuItem} from "@mui/material";
import {AccountCircle, NotificationsActive} from "@mui/icons-material";
import {useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {getUser, logout} from "../../Redux/Auth/action";

const Navbar = () => {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const dispatch = useDispatch();
    const {auth, notification} = useSelector(store => store)
    const navigate = useNavigate();
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleLogout = () => {
        dispatch(logout());
        handleClose();
    }

    useEffect(() => {
        dispatch(getUser(localStorage.getItem("jwt")))
    }, [auth.jwt]);
    return (
        <div className={'z-50 px-6 flex items-center justify-between py-2'}>
            <div className={'flex items-center gap-10'}>
                <h1 onClick={() => navigate("/")} className={'cursor-pointer font-bold text-2xl'}>
                    Salon Service
                </h1>
                <div className={'flex items-center gap-5'}>
                    <h1>Home</h1>
                </div>
            </div>
            <div className={'flex items-center gap-3 md:gap-6'}>
                <Button
                    variant={'outlined'}>
                    Become Partner
                </Button>

                <IconButton onClick={() => navigate("/notifications")}>
                    <Badge badgeContent={notification.unreadCount}>
                        <NotificationsActive color={'primary'}/>
                    </Badge>
                </IconButton>

                {auth.user?.id ? <div className={'flex gap-1 items-center'}>
                        <h1 className={'text-lg font-semibold'}>{auth.user?.fullName}</h1>
                        <IconButton id="basic-button"
                                    aria-controls={open ? 'basic-menu' : undefined}
                                    aria-haspopup="true"
                                    aria-expanded={open ? 'true' : undefined}
                                    onClick={handleClick}>
                            <Avatar sx={{bgcolor: "green"}}>
                                {auth.user?.fullName[0]}
                            </Avatar>
                        </IconButton>
                        <Menu
                            id="basic-menu"
                            anchorEl={anchorEl}
                            open={open}
                            onClose={handleClose}
                            slotProps={{
                                list: {
                                    'aria-labelledby': 'basic-button',
                                },
                            }}
                        >
                            <MenuItem onClick={() => {
                                navigate("/bookings")
                                handleClose()
                            }}>
                                My Bookings
                            </MenuItem>
                            <MenuItem onClick={handleLogout}>Logout</MenuItem>
                        </Menu>
                    </div> :

                    <IconButton onClick={() => navigate("/login")}>
                        <AccountCircle sx={{fontSize: "45px", color: "green"}}/>
                    </IconButton>}
            </div>
        </div>
    );
};

export default Navbar;