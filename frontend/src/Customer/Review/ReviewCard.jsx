import React from 'react';
import {Avatar, Box, Grid, IconButton, Rating} from "@mui/material";
import {red} from "@mui/material/colors";
import {Delete} from "@mui/icons-material";

const ReviewCard = () => {
    return (
        <div className={'flex justify-between'}>
            <div className={'w-[80%]'}>
                <Grid container>
                    <Grid size={1.5}>
                        <Box>
                            <Avatar className={'text-white'} sx={{width: 56, height: 56, bgcolor: "#9155FD"}}>
                                A
                            </Avatar>
                        </Box>
                    </Grid>

                    <Grid size={9}>
                        <div className={'space-y-2'}>
                            <p className={'font-semibold text-lg'}>Code with Trung</p>
                            <p className={'opacity-70'}>2026-28-04</p>
                        </div>
                        <div>
                            <Rating readOnly
                                    value={4.5}
                                    name="half-rating"
                                    defaultValue={4.5}
                                    precision={0.5}/>
                        </div>
                        <p>This salon is provide great service</p>
                    </Grid>
                </Grid>
            </div>

            <IconButton>
                <Delete sx={{color: red[700]}}/>
            </IconButton>
        </div>
    );
};

export default ReviewCard;