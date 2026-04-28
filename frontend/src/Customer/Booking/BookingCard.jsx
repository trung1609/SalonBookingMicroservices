import React from 'react';
import {ArrowRightAlt} from "@mui/icons-material";
import {Button} from "@mui/material";

const BookingCard = () => {
    return (
        <div className={'p-5 rounded-md bg-slate-100 md:flex items-center justify-between'}>
            <div className={'space-y-2'}>
                <h1 className={'text-2xl font-bold'}>Trung Salon</h1>
                <div>
                    <li>Hair Cut</li>
                    <li>Massage Therapy</li>
                    <li>Hair Color</li>
                </div>
                <div>
                    <p>Time & Date <ArrowRightAlt/> 2026-28-04</p>
                    <p>12:00:00 To 12:45:00</p>
                </div>
            </div>

            <div className={'space-y-2'}>
                <img className={'h-28 w-28 object-cover rounded-md'}
                     src="https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777381496/10_Beauty_Shop_Interior_Design_Ideas_That_Wow_Clients_x7wsuj.jpg"
                     alt=""/>
                <p className={'text-center'}>₹249</p>
                <Button variant={'outlined'}
                        color={'error'}>Cancelled</Button>
            </div>
        </div>
    );
};

export default BookingCard;