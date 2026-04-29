import React from 'react';
import SalonDrawerList from "./components/SalonDrawerList";
import Navbar from "../Admin Salon/Navbar";

const SalonDashboard = () => {
    return (
        <div className={'min-h-screen'}>
            <Navbar DrawerList={SalonDrawerList}/>
            <section className={'lg:flex lg:h-[90vh]'}>
                <div className={'hidden lg:block h-full'}>
                    <SalonDrawerList/>
                </div>
            </section>
        </div>
    );
};

export default SalonDashboard;