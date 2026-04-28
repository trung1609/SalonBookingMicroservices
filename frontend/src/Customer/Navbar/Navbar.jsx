import React from 'react';
import {Avatar, Badge, Button, IconButton, Menu, MenuItem} from "@mui/material";
import {NotificationsActive, AccountCircle} from "@mui/icons-material";
const Navbar = () => {
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };
    return (
        <div className={'z-50 px-6 flex items-center justify-between py-2'}>
            <div className={'flex items-center gap-10'}>
                <h1 className={'cursor-pointer font-bold text-2xl'}>
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

                <IconButton>
                    <Badge badgeContent={5}>
                        <NotificationsActive color={'primary'}/>
                    </Badge>
                </IconButton>

                {true ? <div className={'flex gap-1 items-center'}>
                    <h1 className={'text-lg font-semibold'}>Trung</h1>
                    <IconButton id="basic-button"
                                aria-controls={open ? 'basic-menu' : undefined}
                                aria-haspopup="true"
                                aria-expanded={open ? 'true' : undefined}
                                onClick={handleClick}>
                        <Avatar sx={{bgcolor: "green"}}>
                            T
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
                        <MenuItem onClick={handleClose}>My Bookings</MenuItem>
                        <MenuItem onClick={handleClose}>Logout</MenuItem>
                    </Menu>
                </div> :

                <IconButton>
                    <AccountCircle sx={{fontSize: "45px" , color: "green"}}/>
                </IconButton>}
            </div>
        </div>
    );
};

export default Navbar;