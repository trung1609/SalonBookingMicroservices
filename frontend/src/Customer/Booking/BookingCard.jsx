import React from 'react';
import {ArrowRightAlt} from "@mui/icons-material";
import {Button} from "@mui/material";
import {FiberManualRecord} from "@mui/icons-material";

const BookingCard = ({item}) => {
    return (
        <div className={'p-5 rounded-md bg-slate-100 md:flex items-center justify-between'}>
            <div className={'space-y-2'}>
                <h1 className={'text-2xl font-bold text-gray-800'}>{item.salon.name}</h1>
                <ul className={'list-none p-0 m-0 space-y-1'}>
                    {item.services.map((service) => (
                        <li key={service.id} className={'text-gray-800 flex items-center gap-2'}>
                            <FiberManualRecord sx={{ fontSize: '8px', color: 'gray' }} />
                            {service.name}
                        </li>
                    ))}
                </ul>
                <div className={'text-gray-800'}>
                    <p className={'flex items-center gap-2 font-medium '}>
                        Time & Date <ArrowRightAlt className={'text-green-600'} /> {item.startTime?.split("T")[0]}
                    </p>
                    <p className={'mt-1'}>
                        {item.startTime?.split("T")[1]} To {item.endTime?.split("T")[1]}
                    </p>
                </div>
            </div>

            <div className={'space-y-2'}>
                <img className={'h-28 w-28 object-cover rounded-md'}
                     src={item.services[0].image}
                     alt=""/>
                <p className={'text-center'}>₹{item.totalPrice}</p>
                <Button variant={'outlined'}
                        color={'error'}>Cancelled</Button>
            </div>
        </div>
    );
};

export default BookingCard;