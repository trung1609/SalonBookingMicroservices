import React from 'react';
import {Route, Routes} from "react-router-dom";
import HomePage from "../Salon/Home/HomePage";
import ServiceTable from "../Salon/Service/ServiceTable";
import CreateServiceForm from "../Salon/Service/CreateServiceForm";
import BookingTable from "../Salon/Booking/BookingTable";
import Category from "../Salon/Category/Category";
import TransactionTable from "../Salon/Transaction/TransactionTable";
import Notifications from "../Customer/Notification/Notifications";
import Payment from "../Salon/Payment/Payment";

const SalonRoutes = () => {
    return (
        <Routes>
            <Route path={'/'} element={<HomePage/>}/>
            <Route path={'/services'} element={<ServiceTable/>}/>
            <Route path={'/add-services'} element={<CreateServiceForm/>}/>
            <Route path={'/bookings'} element={<BookingTable/>}/>
            <Route path={'/category'} element={<Category/>}/>
            <Route path={'/transaction'} element={<TransactionTable/>}/>
            <Route path={'/notifications'} element={<Notifications/>}/>
            <Route path={'/payment'} element={<Payment/>}/>
        </Routes>
    );
};

export default SalonRoutes;