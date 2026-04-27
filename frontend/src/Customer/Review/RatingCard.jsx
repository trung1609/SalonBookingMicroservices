import React from 'react';
import {Box, Grid, LinearProgress, Rating} from "@mui/material";

const RatingCard = () => {
    return (
        <div className={'border p-5 rounded-md'}>
            <div className={'flex items-center space-x-3 pb-10'}>
                <Rating
                    readOnly
                    value={4.5}
                    name="half-rating"
                    defaultValue={4.5}
                    precision={0.5}/>

                <p className={'opacity-60'}>4568</p>
            </div>
            <Box className={'flex flex-col gap-2'}>
                <Grid container alignItems="center" columnSpacing={2} wrap="nowrap">
                    <Grid>
                        <p className="m-0 text-sm w-[65px] leading-none relative top-[-4px]">Excellent</p>
                    </Grid>
                    <Grid sx={{flexGrow: 1}}>
                        <LinearProgress
                            sx={{height: 7, borderRadius: 4}}
                            variant="determinate"
                            value={40}
                            color="success"
                        />
                    </Grid>
                    <Grid>
                        <p className="opacity-50 m-0 text-sm text-right w-[45px] leading-none relative top-[-4px]">12993</p>
                    </Grid>
                </Grid>

                <Grid container alignItems="center" columnSpacing={2} wrap="nowrap">
                    <Grid>
                        <p className="m-0 text-sm w-[65px] leading-none relative top-[-4px]">Very Good</p>
                    </Grid>
                    <Grid sx={{flexGrow: 1}}>
                        <LinearProgress
                            sx={{height: 7, borderRadius: 4}}
                            variant="determinate"
                            value={50}
                            color="success"
                        />
                    </Grid>
                    <Grid>
                        <p className="opacity-50 m-0 text-sm text-right w-[45px] leading-none relative top-[-4px]">12993</p>
                    </Grid>
                </Grid>

                <Grid container alignItems="center" columnSpacing={2} wrap="nowrap">
                    <Grid>
                        <p className="m-0 text-sm w-[65px] leading-none relative top-[-4px]">Good</p>
                    </Grid>
                    <Grid sx={{flexGrow: 1}}>
                        <LinearProgress
                            sx={{height: 7, borderRadius: 4}}
                            variant="determinate"
                            value={30}
                            color="warning"
                        />
                    </Grid>
                    <Grid>
                        <p className="opacity-50 m-0 text-sm text-right w-[45px] leading-none relative top-[-4px]">12993</p>
                    </Grid>
                </Grid>

                <Grid container alignItems="center" columnSpacing={2} wrap="nowrap">
                    <Grid>
                        <p className="m-0 text-sm w-[65px] leading-none relative top-[-4px]">Average</p>
                    </Grid>
                    <Grid sx={{flexGrow: 1}}>
                        <LinearProgress
                            sx={{height: 7, borderRadius: 4}}
                            variant="determinate"
                            value={20}
                            color={"primary"}
                        />
                    </Grid>
                    <Grid>
                        <p className="opacity-50 m-0 text-sm text-right w-[45px] leading-none relative top-[-4px]">12993</p>
                    </Grid>
                </Grid>

                <Grid container alignItems="center" columnSpacing={2} wrap="nowrap">
                    <Grid>
                        <p className="m-0 text-sm w-[65px] leading-none relative top-[-4px]">Poor</p>
                    </Grid>
                    <Grid sx={{flexGrow: 1}}>
                        <LinearProgress
                            sx={{height: 7, borderRadius: 4}}
                            variant="determinate"
                            value={10}
                            color="error"
                        />
                    </Grid>
                    <Grid>
                        <p className="opacity-50 m-0 text-sm text-right w-[45px] leading-none relative top-[-4px]">12993</p>
                    </Grid>
                </Grid>
            </Box>
        </div>
    );
};

export default RatingCard;