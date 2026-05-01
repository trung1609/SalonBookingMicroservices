import React, {useEffect} from 'react'
import {useParams} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {fetchSalonById} from "../../../Redux/Salon/action";
import {getCategoriesBySalon} from "../../../Redux/Category/action";

const SalonDetail = () => {
    const {id} = useParams();
    const dispatch = useDispatch();
    const {salon} = useSelector(store => store);
    useEffect(() => {
        if (id) {
            dispatch(fetchSalonById(id));
            dispatch(getCategoriesBySalon({
                jwt: localStorage.getItem("jwt"),
                salonId: id,
            }))
        }
    }, [id]);
    return (
        <div className='space-y-5 mb-20'>
            <section className='grid grid-cols-2 gap-3'>
                <div className='col-span-2'>
                    <img className='w-full rounded-md h-[15rem] object-cover'
                         src={salon.salon?.images[0]}
                         alt=''/>
                </div>

                <div className='col-span-1'>
                    <img className='w-full rounded-md h-[15rem] object-cover'
                         src={salon.salon?.images[1]}
                         alt=''/>
                </div>

                <div className='col-span-1'>
                    <img className='w-full rounded-md h-[15rem] object-cover'
                         src={salon.salon?.images[2]}
                         alt=''/>
                </div>
            </section>

            <section className='space-y-3'>
                <h1 className='text-3xl font-bold'>{salon.salon?.name}</h1>
                <p>{salon.salon?.address}</p>
                <p><strong>Timing:</strong> {salon.salon?.openTime} - {salon.salon?.closeTime}</p>
            </section>
        </div>
    )
}

export default SalonDetail