import React from 'react'

const SalonDetail = () => {
    return (
        <div className='space-y-5 mb-20'>
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

            <section className='space-y-3'>
                <h1 className='text-3xl font-bold'>Trung Salon</h1>
                <p>123 Main Street, City, State 12345</p>
                <p><strong>Timing:</strong> 9:00 AM - 8:00 PM</p>
            </section>
        </div>
    )
}

export default SalonDetail