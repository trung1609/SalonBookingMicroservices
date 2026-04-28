import React from 'react'
import StarIcon from '@mui/icons-material/Star';
import {useNavigate} from "react-router-dom";

const SalonCard = () => {
    const navigate = useNavigate()
    return (
        <div onClick={() => navigate("/salon/2")} className='cursor-pointer'>
            <div className='w-56 md:w-80 rounded-md bg-slate-100'>
                <img
                    className='w-full h-[15rem] object-cover rounded-md'
                    src='https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777127418/Mens_hair_in_general_iw83bs.jpg'
                    alt=''/>
                <div className='p-5 space-y-2'>
                    <h1>Pablo Salon</h1>
                    <div
                        className='text-white text-sm p-1 bg-green-700 rounded-full w-14 flex items-center justify-center gap-1'>
                        4.5 <StarIcon sx={{fontSize: "16px"}}/>
                    </div>
                    <p>Professional haircut and ...</p>
                    <p>Adani Hours, Mumbai</p>
                </div>
            </div>
        </div>
    )
}

export default SalonCard
