import React from 'react'
import Banner from './Banner'
import HomeServiceCard from './HomeServiceCard'
import {services} from '../../Data/services'
import SalonList from '../../Salon/SalonList'

const Home = () => {
    return (
        <div className='space-y-20'>
            <section>
                <Banner/>
            </section>
            <section className='space-y-10 lg:space-y-0 lg: flex items-center gap-5 px-20'>
                <div className='w-full lg:w-1/2'>
                    <h1 className='text-2xl font-semibold pb-9'>
                        What are you looking for, Bestie? 👀
                    </h1>
                    <div className='flex flex-wrap justify-center items-center gap-5'>
                        {
                            services.map((item) => <HomeServiceCard key={item.id} item={item}/>)
                        }
                    </div>
                </div>
                <div className='w-full lg:w-1/2 border grid gap-3 grid-cols-2 grid-rows-12
                h-[45vh] md:h-[90vh]'>
                    <div className='row-span-7'>
                        <img className='h-full w-full rounded-md'
                             src='https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777126890/Google_Image_Result_for_https___lalafo_az__next_image_url_https_3A_2F_2Fimg5.lalafo.com_2Fi_2Fposters_2Fapi_2Fc3_2F7d_2Fc9_2Fpeskar-kisi-sac-ksimi-xidmti-id-109680607-870177316_grppdc.jpg'
                             alt=''/>
                    </div>

                    <div className='row-span-5'>
                        <img className='h-full w-full rounded-md'
                             src='https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777126936/The_3_Best_DIY_Masks_For_Every_Skin_Woe_hb7kdx.jpg'
                             alt=''/>
                    </div>

                    <div className='row-span-7'>
                        <img className='h-full w-full rounded-md'
                             src='https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777126977/Elegant_Mumbai_Wedding_With_The_Bride_In_A_Beautiful_Pastel_Pink_Lehenga_cdimtq.jpg'
                             alt=''/>
                    </div>

                    <div className='row-span-5'>
                        <img className='h-full w-full rounded-md'
                             src='https://res.cloudinary.com/dq6f7y2tu/image/upload/v1777127006/BeBeautiful__Your_Go-To_Beauty_Insider_for_Hair_Skin_Makeup_Wellness_hxjlin.jpg'
                             alt=''/>
                    </div>
                </div>
            </section>

            <section className='px-20'>
                <h1 className='text-3xl font-bold pb-10'>Book your favorite salon</h1>
                <SalonList/>
            </section>
        </div>
    )
}

export default Home
