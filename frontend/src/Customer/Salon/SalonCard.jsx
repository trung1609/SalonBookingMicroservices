import React from 'react'
import StarIcon from '@mui/icons-material/Star';
import {useNavigate} from "react-router-dom";

const SalonCard = ({item}) => {
    const navigate = useNavigate()
    return (
        <div onClick={() => navigate(`/salon/${item.id}`)} className='cursor-pointer'>
            <div className='w-56 md:w-80 rounded-md bg-slate-100'>
                <img
                    className='w-full h-[15rem] object-cover rounded-md'
                    src={item.images[0]}
                    alt=''/>
                <div className='p-5 space-y-2'>
                    <h1>{item.name}</h1>
                    <div
                        className='text-white text-sm p-1 bg-green-700 rounded-full w-14 flex items-center justify-center gap-1'>
                        4.5 <StarIcon sx={{fontSize: "16px"}}/>
                    </div>
                    <p>Professional haircut and ...</p>
                    <p>{item.address}</p>
                </div>
            </div>
        </div>
    )
}

export default SalonCard
