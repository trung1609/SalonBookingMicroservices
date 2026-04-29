import React from 'react';
import ProfileFieldCard from "./ProfileFieldCard";
import {Divider} from "@mui/material";

const Profile = () => {
    return (
        <div className={'lg:px-20 lg:bottom-20 space-y-20'}>
            <div className={'w-full lg:w-[70%]'}>
                <h1 className={'text-5xl font-bold pb-5'}>Trung Salon</h1>
                <section className='grid grid-cols-2 gap-3'>
                    <div className='col-span-2'>
                        <img className='w-full rounded-md h-[15rem] object-cover'
                             src="https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777193100/download_2_jjiwkk.jpg"
                             alt=''/>
                    </div>

                    <div className='col-span-1'>
                        <img className='w-full rounded-md h-[15rem] object-cover'
                             src="https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777193168/Men_s_Hair_Salons__Everything_You_Need_to_Know_qnox2c.jpg"
                             alt=''/>
                    </div>

                    <div className='col-span-1'>
                        <img className='w-full rounded-md h-[15rem] object-cover'
                             src="https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777193230/download_3_a8p6un.jpg"
                             alt=''/>
                    </div>
                </section>
            </div>
            <div className={'mt-10 lg:w-[70%]'}>
                <div className={'flex items-center pb-3 justify-between'}>
                    <h1 className={'text-2xl font-bold text-gray-600'}>Salon Details</h1>
                </div>
                <div>
                    <ProfileFieldCard keys={'Salon Name'} value={'Trung Salon'}/>
                    <Divider/>
                    <ProfileFieldCard keys={'Salon Address'} value={'123 Main Street, City, State 12345'}/>
                    <Divider/>
                    <ProfileFieldCard keys={'Open Time'} value={'9:00 AM'}/>
                    <Divider/>
                    <ProfileFieldCard keys={'Close Time'} value={'8:00 PM'}/>
                </div>
            </div>

            <div className={'mt-10 lg:w-[70%]'}>
                <div className={'flex items-center pb-3 justify-between'}>
                    <h1 className={'text-2xl font-bold text-gray-600'}>Owner Details</h1>
                </div>
                <div>
                    <ProfileFieldCard keys={'Owner Name'} value={'Trung'}/>
                    <Divider/>
                    <ProfileFieldCard keys={'Email'} value={'trung8d2005@gmail.com'}/>
                    <Divider/>
                    <ProfileFieldCard keys={'Role'} value={'Salon_Owner'}/>
                </div>
            </div>
        </div>
    );
};

export default Profile;